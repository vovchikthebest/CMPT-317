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
public class Square {
    Pawn occupant;
    
    public Square (Pawn inPawn) {
        this.occupant = inPawn;
    }
    
    public Square () {
        this.occupant = null;
    }
    
    public void setOccupant (Pawn inPawn) {
        this.occupant = inPawn;
    }
    
    public Pawn getOccupant () {
        return this.occupant;
    }
}
