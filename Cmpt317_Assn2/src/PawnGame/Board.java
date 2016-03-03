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
}
