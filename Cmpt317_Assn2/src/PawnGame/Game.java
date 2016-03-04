/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PawnGame;

import java.util.ArrayList;

/**
 *
 * @author Vladimir
 */
public class Game {
    public Board board;
    ArrayList<Pawn> teamWhite, teamBlack;
    
    public Game (int size) {
        board = new Board(size);
        
        // Initialize white
        for (int i = 0; i < board.size; i++) {
            board.gameBoard[i][0] = 1;
        }
        
        // Initialize black
        for (int i = 0; i < board.size; i++) {
            board.gameBoard[i][board.size-1] = 2;
        }
      
        
        this.createPawnArray(this.board);
    }
    
    public ArrayList<Board> pawnSuccessor(Pawn inPawn) {
        ArrayList<Board> possibleMoves = new ArrayList<Board>();
        // Check if the pawn can move forward
        if (inPawn.team == false) { // white team
            int temp = board.gameBoard[inPawn.x][inPawn.y+1];
            if (temp == 0) {
                Board cloned = this.board.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x][inPawn.y+1] = 1;
                possibleMoves.add(cloned);
            }
        } else { 
            int temp = board.gameBoard[inPawn.x][inPawn.y-1];
            if (temp == 0) {
                Board cloned = this.board.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x][inPawn.y-1] = 2;
                possibleMoves.add(cloned);
            }
        }
        
        // Check if the pawn can make a diagnoal move
        if (inPawn.team == false) {
            int diagonal1 = 0, diagonal2 = 0;
            if (inPawn.x-1 >= 0) { // check left diagonal
                diagonal1 = board.gameBoard[inPawn.x-1][inPawn.y+1];
            }
            
            if (inPawn.x+1 <= board.size-1) { // check right diagonal
                diagonal2 = board.gameBoard[inPawn.x+1][inPawn.y+1];
            }
            
            if (diagonal1 == 2) { // check if the left diagonal has an enemy pawn
                Board cloned = this.board.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x-1][inPawn.y+1] = 1;
                possibleMoves.add(cloned);
            }
            
            if (diagonal2 == 2) {   // check if the right diagonal has an enemy pawn
                Board cloned = this.board.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x+1][inPawn.y+1] = 1;
                possibleMoves.add(cloned);
            }
        } else {    // Diagnoal moves for black
            int diagonal1 = 0, diagonal2 = 0;
            if (inPawn.x-1 >= 0) { // check right diagonal
                diagonal1 = board.gameBoard[inPawn.x-1][inPawn.y-1];
            }
            
            if (inPawn.x+1 <= board.size-1) { // check left diagonal
                diagonal2 = board.gameBoard[inPawn.x+1][inPawn.y-1];
            }
            
            if (diagonal1 == 1) { // check if the right diagonal has an enemy pawn
                Board cloned = this.board.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x-1][inPawn.y-1] = 1;
                possibleMoves.add(cloned);
            }
            
            if (diagonal2 == 1) {   // check if the left diagonal has an enemy pawn
                Board cloned = this.board.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x+1][inPawn.y-1] = 1;
                possibleMoves.add(cloned);
            }
        }
        return possibleMoves;
    }
    
    /**
     * Calls pawnSuccessor for all pawns on the board. Puts all moves into the tree.
     */
    public ArrayList<Board> boardSuccessor (Board curBoard){
        this.createPawnArray(curBoard);
        ArrayList<Board> result = new ArrayList<Board>();
        
        // Get successors for white
        for (int i = 0; i < teamWhite.size(); i++) {
            result.addAll(this.pawnSuccessor(teamWhite.get(i)));
        }
        
        // Get successors for black
        for (int i = 0; i < teamBlack.size(); i++) {
            result.addAll(this.pawnSuccessor(teamBlack.get(i)));
        }
        
        return result;
    }
    
    // Creates the arrays of pawns from the board given
    public void createPawnArray (Board curBoard) {
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
