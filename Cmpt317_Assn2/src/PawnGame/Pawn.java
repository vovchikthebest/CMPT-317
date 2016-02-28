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
public class Pawn {
    int x, y;
    boolean team;   // false - white, true - black
    
    public Pawn (int inX, int inY, boolean inTeam) {
        this.x = inX;
        this.y = inY;
        this.team = inTeam;
    }
}
