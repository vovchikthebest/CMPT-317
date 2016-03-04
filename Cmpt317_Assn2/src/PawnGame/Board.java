/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PawnGame;

/**
 *
 * @author Vladimir
 */
public class Board {
    int size;
    int[][] gameBoard; // 0 - empty, 1 - white, 2 - black
    
    public Board (int inSize) {
        size = inSize;
        gameBoard = new int[size][size];
    }
    
    public int getSquare (int x, int y) {
        return gameBoard[x][y];
    }
    
    public Board clone () {
        Board temp = new Board(this.size);
        
        temp.gameBoard = this.gameBoard.clone();
        
        return temp;
    }
    
    /**
     * Determine if there is a winner on the current board
     * @return 1 - white wins, -1 - black wins, 0 - none/tie
     */
    public int determineWinner() {
        int countWhite = 0, countBlack = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                // Count the white and black pieces to determine winner
                switch(this.gameBoard[i][j]) {
                    case(1):
                        countWhite++;
                        break;
                    case(2):
                        countBlack++;
                        break;
                }
                
                // Check if any of the white/black pieces have reached their endzones
                if (i == 0) {
                    if (this.gameBoard[i][j] == 2) {    // if a black piece reached endzone
                        return -1;
                    }
                } else if (i == this.size - 1) {
                    if (this.gameBoard[i][j] == 1) {    // if a white piece reached endzone
                        return 1;
                    }
                }
            }
        }
        
        if (countWhite == 0) {
            return -1;  // Black wins
        } else if (countBlack == 0) {
            return 1;   // White wins
        }
        
        return 0;
    }
}
