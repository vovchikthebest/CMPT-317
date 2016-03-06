/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn2;

import PawnGame.Board;
import PawnGame.GameImp;
import PawnGame.GameState;
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
        long startTime1 = System.nanoTime();
        boolean alternate = true;
        moveAlpha.add(testAlphaBeta.search(testBoard,alternate));
        int i = 1;
        
        while (!((Board)moveAlpha.get(i-1)).getFinished()) {
            alternate = !alternate;
            moveAlpha.add(testAlphaBeta.search(moveAlpha.get(i-1),alternate));
            i++;
        }
        long endTime1 = System.nanoTime();
        System.out.println("Moves made with alpha beta pruning: ");
    
        System.out.println(moveAlpha);
        System.out.println("Amount of turns: " + moveAlpha.size());
        long duration1 = (endTime1 - startTime1)/1000000000;
        
        // Testing for min and max
        long startTime2 = System.nanoTime();
        alternate = true;
        moveMinMax.add(testMinMax.search(testBoard,alternate));
        i = 1;
        
        while (!((Board)moveMinMax.get(i-1)).getFinished()) {
            alternate = !alternate;
            moveMinMax.add(testMinMax.search(moveMinMax.get(i-1),alternate));
            i++;
        }
        long endTime2 = System.nanoTime();
        System.out.println("Moves made with minimax: ");
    
        System.out.println(moveMinMax);
        System.out.println("Amount of turns: " + moveMinMax.size());
        long duration2 = (endTime2 - startTime2)/1000000000;
        
        System.out.println("MinMax time: " + duration2 + " ; Alpha-Beta time:" + duration1);
    }
    
}
