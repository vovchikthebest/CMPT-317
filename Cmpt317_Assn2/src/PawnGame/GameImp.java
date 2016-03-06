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
public class GameImp implements Game {

    ArrayList<Pawn> teamWhite, teamBlack;
    
    private static final int MAX = 30;
    private static final int MIN = -30;

    public ArrayList<Board> pawnSuccessor(Pawn inPawn, Board curBoard) {
        ArrayList<Board> possibleMoves = new ArrayList<Board>();
        // Check if the pawn can move forward
        if (inPawn.team == false) { // white team
            int temp = curBoard.gameBoard[inPawn.x][inPawn.y + 1];
            if (temp == 0) {
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x][inPawn.y + 1] = 1;
                // Check if white has reached endzone
                if (inPawn.y + 1 == curBoard.size - 1) {
                    cloned.winner = MAX;
                    cloned.finished = true;
                }
                possibleMoves.add(cloned);
            }
        } else {
            int temp = curBoard.gameBoard[inPawn.x][inPawn.y - 1];
            if (temp == 0) {
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x][inPawn.y - 1] = 2;
                // Check if black has reached endzone
                if (inPawn.y - 1 == 0) {
                    cloned.winner = MIN;
                    cloned.finished = true;
                }
                possibleMoves.add(cloned);
            }
        }

        // Check if the pawn can make a diagnoal move
        if (inPawn.team == false) {
            int diagonal1 = 0, diagonal2 = 0;
            if (inPawn.x - 1 >= 0) { // check left diagonal
                diagonal1 = curBoard.gameBoard[inPawn.x - 1][inPawn.y + 1];
            }

            if (inPawn.x + 1 <= curBoard.size - 1) { // check right diagonal
                diagonal2 = curBoard.gameBoard[inPawn.x + 1][inPawn.y + 1];
            }

            if (diagonal1 == 2) { // check if the left diagonal has an enemy pawn
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x - 1][inPawn.y + 1] = 1;
                if (inPawn.y + 1 == curBoard.size - 1) {    // Moves into endzone while eating a piece
                    cloned.winner = MAX;
                    cloned.finished = true;
                }
                if (teamBlack.size() == 1) {    // check if the board has no more black pieces
                    cloned.winner = MAX;
                    cloned.finished = true;
                }
                possibleMoves.add(cloned);
            }

            if (diagonal2 == 2) {   // check if the right diagonal has an enemy pawn
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x + 1][inPawn.y + 1] = 1;
                if (inPawn.y + 1 == curBoard.size - 1) {    // Moves into endzone while eating a piece
                    cloned.winner = MAX;
                    cloned.finished = true;
                }
                if (teamBlack.size() == 1) {    // check if the board has no more black pieces
                    cloned.winner = MAX;
                    cloned.finished = true;
                }
                possibleMoves.add(cloned);
            }
        } else {    // Diagnoal moves for black
            int diagonal1 = 0, diagonal2 = 0;
            if (inPawn.x - 1 >= 0) { // check right diagonal
                diagonal1 = curBoard.gameBoard[inPawn.x - 1][inPawn.y - 1];
            }

            if (inPawn.x + 1 <= curBoard.size - 1) { // check left diagonal
                diagonal2 = curBoard.gameBoard[inPawn.x + 1][inPawn.y - 1];
            }

            if (diagonal1 == 1) { // check if the right diagonal has an enemy pawn
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x - 1][inPawn.y - 1] = 2;
                if (inPawn.y - 1 == 0) {    // Moves into endzone while eating a piece
                    cloned.winner = MIN;
                    cloned.finished = true;
                }
                if (teamWhite.size() == 1) {    // check if the board has no more white pieces
                    cloned.winner = MIN;
                    cloned.finished = true;
                }
                possibleMoves.add(cloned);
            }

            if (diagonal2 == 1) {   // check if the left diagonal has an enemy pawn
                Board cloned = curBoard.clone();
                cloned.gameBoard[inPawn.x][inPawn.y] = 0;
                cloned.gameBoard[inPawn.x + 1][inPawn.y - 1] = 2;
                if (inPawn.y - 1 == 0) {    // Moves into endzone while eating a piece
                    cloned.winner = MIN;
                    cloned.finished = true;
                }
                if (teamWhite.size() == 1) {    // check if the board has no more white pieces
                    cloned.winner = MIN;
                    cloned.finished = true;
                }
                possibleMoves.add(cloned);
            }
        }
        return possibleMoves;
    }

    /**
     * Calls pawnSuccessor for all pawns on the board. Puts all moves into the
     * tree.
     */
    public ArrayList<GameState> Successors(GameState p) {
        Board curBoard = (Board) p;
        this.createPawnArray(curBoard);
        ArrayList<GameState> result = new ArrayList<GameState>();

        if (curBoard.turn == false) {
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
        
        if (result.isEmpty()) {
            curBoard.finished = true;
        }

        return result;
    }
    
    @Override
    public int Utility (GameState s) {
        Board curBoard = (Board) s; 
        return curBoard.winner;
    }
    
    public int Evaluate (GameState s) {
        Board curBoard = (Board) s;
        this.createPawnArray(curBoard);
        
        // Amount of pawns left on each team
        int sizeDif = teamWhite.size() - teamBlack.size();
        
        // Amount of distance left to the end zones
        int whiteDis = 0, blackDis = 0;
        for (int i = 0; i < teamWhite.size(); i++) {    // White pawns.
            whiteDis += (curBoard.size - 1) - teamWhite.get(i).y;
        }
        // For every member not on the board add the max amount to the distance
        for (int i = teamWhite.size(); i < curBoard.size; i++) {
            whiteDis += 6;
        }
        
        for (int i = 0; i < teamBlack.size(); i++) {    // Black pawns.
            blackDis += teamBlack.get(i).y - 1;
        }
        // For every member not on the board add the max amount to the distance
        for (int i = teamBlack.size(); i < curBoard.size; i++) {
            blackDis += 6;
        }
        
        // If black has more distance to go then the result is more positive
        sizeDif += (blackDis - whiteDis);
        
        
        // TODO: take into account pawns that have dirrect access to the end zone on the opposing team
        // TODO: add better evaluation
        return sizeDif;
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

    public boolean TerminalState(GameState s) {
        Board curBoard = (Board) s;
        return curBoard.finished;
    }
}
