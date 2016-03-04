/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PawnGame;

import Tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Vladimir
 */
public class Game {

    public Board board;
    ArrayList<Pawn> teamWhite, teamBlack;
    boolean turn; // false - white, true - black

    public Game(int size) {
        board = new Board(size);

        // Initialize white
        for (int i = 0; i < board.size; i++) {
            board.gameBoard[i][0] = 1;
        }

        // Initialize black
        for (int i = 0; i < board.size; i++) {
            board.gameBoard[i][board.size - 1] = 2;
        }

        this.createPawnArray(this.board);

        turn = false; // white starts the game
    }

    public ArrayList<Board> pawnSuccessor(Pawn inPawn, Board curBoard) {
        ArrayList<Board> possibleMoves = new ArrayList<Board>();
        // Check if the pawn can move forward
        if (inPawn.team == false) { // white team
            int temp = board.gameBoard[inPawn.x][inPawn.y + 1];
            if (temp == 0) {
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x][inPawn.y + 1] = 1;
                // Check if white has reached endzone
                if (inPawn.y + 1 == board.size - 1) {
                    cloned.winner = 1;
                }
                possibleMoves.add(cloned);
            }
        } else {
            int temp = board.gameBoard[inPawn.x][inPawn.y - 1];
            if (temp == 0) {
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x][inPawn.y - 1] = 2;
                // Check if black has reached endzone
                if (inPawn.y - 1 == 0) {
                    cloned.winner = -1;
                }
                possibleMoves.add(cloned);
            }
        }

        // Check if the pawn can make a diagnoal move
        if (inPawn.team == false) {
            int diagonal1 = 0, diagonal2 = 0;
            if (inPawn.x - 1 >= 0) { // check left diagonal
                diagonal1 = board.gameBoard[inPawn.x - 1][inPawn.y + 1];
            }

            if (inPawn.x + 1 <= board.size - 1) { // check right diagonal
                diagonal2 = board.gameBoard[inPawn.x + 1][inPawn.y + 1];
            }

            if (diagonal1 == 2) { // check if the left diagonal has an enemy pawn
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x - 1][inPawn.y + 1] = 1;
                if (teamBlack.size() == 1) {    // check if the board has no more black pieces
                    cloned.winner = 1;
                    cloned.finished = true;
                }
                possibleMoves.add(cloned);
            }

            if (diagonal2 == 2) {   // check if the right diagonal has an enemy pawn
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x + 1][inPawn.y + 1] = 1;
                if (teamBlack.size() == 1) {    // check if the board has no more black pieces
                    cloned.winner = 1;
                    cloned.finished = true;
                }
                possibleMoves.add(cloned);
            }
        } else {    // Diagnoal moves for black
            int diagonal1 = 0, diagonal2 = 0;
            if (inPawn.x - 1 >= 0) { // check right diagonal
                diagonal1 = board.gameBoard[inPawn.x - 1][inPawn.y - 1];
            }

            if (inPawn.x + 1 <= board.size - 1) { // check left diagonal
                diagonal2 = board.gameBoard[inPawn.x + 1][inPawn.y - 1];
            }

            if (diagonal1 == 1) { // check if the right diagonal has an enemy pawn
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x - 1][inPawn.y - 1] = 1;
                if (teamWhite.size() == 1) {    // check if the board has no more white pieces
                    cloned.winner = -1;
                    cloned.finished = true;
                }
                possibleMoves.add(cloned);
            }

            if (diagonal2 == 1) {   // check if the left diagonal has an enemy pawn
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x + 1][inPawn.y - 1] = 1;
                if (teamWhite.size() == 1) {    // check if the board has no more white pieces
                    cloned.winner = -1;
                    cloned.finished = true;
                }
                possibleMoves.add(cloned);
            }
        }
        if (possibleMoves.size() == 0) {
            curBoard.finished = true;
        }
        return possibleMoves;
    }

    /**
     * Calls pawnSuccessor for all pawns on the board. Puts all moves into the
     * tree.
     */
    public ArrayList<Board> boardSuccessor(Board curBoard) {
        this.createPawnArray(curBoard);
        ArrayList<Board> result = new ArrayList<Board>();

        if (turn == false) {
            // Get successors for white
            for (int i = 0; i < teamWhite.size(); i++) {
                result.addAll(this.pawnSuccessor(teamWhite.get(i), curBoard));
            }
        } else {
            // Get successors for black
            for (int i = 0; i < teamBlack.size(); i++) {
                result.addAll(this.pawnSuccessor(teamBlack.get(i), curBoard));
            }
        }

        return result;
    }

    /**
     * Ultimate successor function that returns the tree of possible moves
     */
    public TreeNode<Board> Successor() {
        TreeNode<Board> resultTree = new TreeNode<Board>(this.board);
        ArrayList<Board> successors;
        turn = true;
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.add(resultTree);
        
        //Breadth first search
        while (!list.isEmpty()) {
            TreeNode curEl = list.remove();
            successors = this.boardSuccessor((Board)curEl.data);
            curEl.addBulk(successors);
            list.addAll(curEl.childNodes);
            turn = false;
        }
        return resultTree;
    }

    // Creates the arrays of pawns from the board given

    public void createPawnArray(Board curBoard) {
        teamWhite = new ArrayList<Pawn>();
        teamBlack = new ArrayList<Pawn>();

        for (int i = 0; i < curBoard.size; i++) {
            for (int j = 0; j < curBoard.size; j++) {
                if (curBoard.gameBoard[i][j] == 1) {
                    Pawn temp = new Pawn(i, j, false);
                    teamWhite.add(temp);
                } else if (curBoard.gameBoard[i][j] == 2) {
                    Pawn temp = new Pawn(i, j, true);
                    teamBlack.add(temp);
                }
            }
        }
    }
}
