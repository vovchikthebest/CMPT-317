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
public class Board implements GameState{
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
            result += i + " | ";
            for (int j = 0; j < this.size; j++) {
                if (this.gameBoard[j][i] == 1) {
                    result += "X" + " ";
                } else if (this.gameBoard[j][i] == 2) {
                    result += "O" + " ";
                } else {
                    result += "-" + " ";
                }
            }
            result += "\n";
        }
        for (int i = 0; i < this.size; i++) {
            result += "---";
        }
        result += "\n    ";
        for (int i = 0; i < this.size; i++) {
            result += i + " ";
        }
        result += "\n";
        
        return result;
    }
    
    public boolean getTurn () {
        return this.turn;
    }
    
    public boolean getFinished() {
        return this.finished;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public void display () {
        System.out.printf(this.toString());
    }
    
    public boolean equals(GameState o) {
        Board compare = (Board) o;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.gameBoard[i][j] != compare.gameBoard[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
