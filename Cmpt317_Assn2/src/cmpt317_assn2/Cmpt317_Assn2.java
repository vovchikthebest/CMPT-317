/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn2;

import PawnGame.Board;
import PawnGame.GameImp;
import PawnGame.GameState;
import PawnGame.PlayGame;
import Search.AlphaBeta;
import Search.MinMax;
import Tree.TreeNode;
import java.util.ArrayList;

/**
 *
 * @author Vladimir
 */
public class Cmpt317_Assn2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameImp test = new GameImp();
        Board testBoard = new Board(6, false);
        AlphaBeta testAlphaBeta = new AlphaBeta(test);
        MinMax testMinMax = new MinMax(test);
    
        ArrayList<GameState> moveAlpha = new ArrayList();
        ArrayList<GameState> moveMinMax = new ArrayList();
    
        // Testing for Alpha Beta pruning
        boolean alternate = true;
        long moveStart = System.nanoTime();
        moveAlpha.add(testAlphaBeta.search(testBoard,alternate));
        long moveEnd = System.nanoTime();
        
        long totalAlphaBeta = (moveEnd - moveStart)/1000000;
                
        int i = 1;
        
        while (!((Board)moveAlpha.get(i-1)).getFinished()) {
            alternate = !alternate;
            moveStart = System.nanoTime();
            moveAlpha.add(testAlphaBeta.search(moveAlpha.get(i-1),alternate));
            moveEnd = System.nanoTime();
            
            totalAlphaBeta += (moveEnd - moveStart)/1000000;
            
            i++;
        }
        
        // Testing for min and max
        alternate = true;
        moveStart = System.nanoTime();
        moveMinMax.add(testMinMax.search(testBoard,alternate));
        moveEnd = System.nanoTime();
        
        long totalMinMax = (moveEnd - moveStart)/1000000;
        
        i = 1;
        
        while (!((Board)moveMinMax.get(i-1)).getFinished()) {
            alternate = !alternate;
            moveStart = System.nanoTime();
            moveMinMax.add(testMinMax.search(moveMinMax.get(i-1),alternate));
            moveEnd = System.nanoTime();
            
            totalMinMax += (moveEnd - moveStart)/1000000;
            
            i++;
        }
        
        System.out.println("MinMax time per turn: " + totalMinMax/moveMinMax.size() + " ; Alpha-Beta time per turn:" + totalAlphaBeta/moveAlpha.size());
        
        //PlayGame playTester = new PlayGame(test, testBoard, true);
        //playTester.play();
    }
    
}
