/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PawnGame;

import javax.swing.JPanel;

/**
 *
 * @author Vladimir
 */
public class Square extends JPanel{
    int x;
    int y;
    
    public Square(int inX, int inY) {
        super();
        x = inX;
        y = inY;
    }
    
    public String toString () {
        return String.valueOf(x) + ", " + String.valueOf(y);
    }
}
