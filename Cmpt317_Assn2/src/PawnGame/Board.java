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
    int size = 5;
    Square[][] gameBoard;
    
    public Board () {
        gameBoard = new Square[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameBoard[i][j] = new Square();
            }
        }
    }
    
    public Square getSquare (int x, int y) {
        return gameBoard[x][y];
    }
}
