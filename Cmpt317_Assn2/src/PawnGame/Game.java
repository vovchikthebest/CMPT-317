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
    Board board;
    ArrayList<Pawn> teamWhite, teamBlack;
    
    public Game () {
        board = new Board();
        
        // Initialize white
        for (int i = 0; i < board.size; i++) {
            Pawn temp = new Pawn(i, 0, false);
            teamWhite.add(temp);
            board.gameBoard[i][0].setOccupant(temp);
        }
        
        // Initialize black
        for (int i = 0; i < board.size; i++) {
            Pawn temp = new Pawn(i, board.size-1, true);
            teamBlack.add(temp); 
            board.gameBoard[i][board.size-1].setOccupant(temp);
        }
    }
    
    public ArrayList<Square> pawnSuccessor(Pawn inPawn) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        // Check if the pawn can move forward
        if (inPawn.team == false) { // white team
            Pawn temp = board.gameBoard[inPawn.x][inPawn.y+1].occupant;
            if (temp == null) {
                possibleMoves.add(board.gameBoard[inPawn.x][inPawn.y+1]);
            }
        } else { 
            Pawn temp = board.gameBoard[inPawn.x][inPawn.y-1].occupant;
            if (temp == null) {
                possibleMoves.add(board.gameBoard[inPawn.x][inPawn.y-1]);
            }
        }
        // Check if the pawn can make a diagnoal move
        if (inPawn.team == false) {
            Square diagonal1, diagonal2;
            diagonal1 = new Square();
            diagonal2 = new Square();
            if (inPawn.x-1 >= 0) { // check left diagonal
                diagonal1 = board.gameBoard[inPawn.x-1][inPawn.y+1];
            }
            
            if (inPawn.x+1 <= board.size-1) { // check right diagonal
                diagonal2 = board.gameBoard[inPawn.x+1][inPawn.y+1];
            }
            
            if (diagonal1.occupant != null) { // check if the left diagonal has an enemy pawn
                if (diagonal1.occupant.team = true) {
                    possibleMoves.add(diagonal1);
                }
            }
            
            if (diagonal2.occupant != null) {   // check if the right diagonal has an enemy pawn
                if (diagonal2.occupant.team = true) {
                    possibleMoves.add(diagonal2);
                }
            }
        } else {
            
        }
        return possibleMoves;
    }
}
