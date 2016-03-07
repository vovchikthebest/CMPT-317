/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PawnGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 *
 * @author Vladimir
 */
public class MouseHandler implements MouseListener {
    int numClicks;
    Square[] clicked;
    GameImp curGame;
    Board curBoard;
    PlayGame GUI;
    
    public MouseHandler(GameImp inGame, Board inBoard, PlayGame inGUI) {
        numClicks = 0;
        curGame = inGame;
        curBoard = inBoard;
        GUI = inGUI;
        clicked = new Square[2];
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clicked[numClicks] = (Square) e.getComponent();
        numClicks++;
        boolean found = false;
        
        if (numClicks == 2) {
            Board cloned = curBoard.clone();
            int previousVal = cloned.gameBoard[clicked[0].y][clicked[0].x];
            cloned.gameBoard[clicked[0].y][clicked[0].x] = 0;
            cloned.gameBoard[clicked[1].y][clicked[1].x] = previousVal;
            
            ArrayList<GameState> successors = curGame.Successors(curBoard);
            for (int i = 0; i < successors.size(); i++) {
                if (successors.get(i).equals(cloned)) {
                    found = true;
                    curBoard = cloned;
                    GUI.createPiecesFromBoard();
                }
            }
            
            if (found) {
                System.out.println("successs");
                
            } else {
                System.out.println("fail");
            }
            numClicks = 0;
        } 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println(e.getComponent());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println(e.getComponent());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println(e.getComponent());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println(e.getComponent());
    }

}
