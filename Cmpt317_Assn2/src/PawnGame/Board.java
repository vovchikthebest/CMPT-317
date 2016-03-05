/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PawnGame;

/**
 * Stores the current state of the GameBoard
 * 
 * @author Vladimir
 */
public class Board {
    int size;
    int[][] gameBoard; // 0 - empty, 1 - white, 2 - black
    int winner; // -1 - black, 0 - tie, 1 - white
    boolean finished; // true - game finished, false - game not finished
    boolean turn; // false - white, true - black
    
    public Board (int inSize, boolean inTurn) {
        size = inSize;
        gameBoard = new int[size][size];
        winner = 0;
        finished = false;
        turn = inTurn;
        
        // Initialize white
        for (int i = 0; i < this.size; i++) {
            this.gameBoard[i][0] = 1;
        }

        // Initialize black
        for (int i = 0; i < this.size; i++) {
            this.gameBoard[i][this.size - 1] = 2;
        }
    }
    
    public int getSquare (int x, int y) {
        return gameBoard[x][y];
    }
    
    public Board clone () {
        Board temp = new Board(this.size, !this.turn);
        
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                temp.gameBoard[i][j] = this.gameBoard[i][j];
            }
        }
        
        return temp;
    }
    
    /**
     * Converts the current board to a string
     * @return 
     */
    public String toString() {
        String result = "";
        for (int i = this.size-1; i >= 0; i--) {
            for (int j = 0; j < this.size; j++) {
                result += String.valueOf(this.gameBoard[j][i]) + " ";
            }
            result += "\n";
        }
        
        return result;
    }
    
    public boolean getTurn () {
        return this.turn;
    }
    
    public boolean getFinished() {
        return this.finished;
    }
}
