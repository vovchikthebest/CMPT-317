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
        AlphaBeta testMinMax = new AlphaBeta(test);
    
        ArrayList<GameState> move = new ArrayList();
    
        boolean alternate = true;
    
        move.add(testMinMax.search(testBoard,alternate));
        int i = 1;
        
        while (!((Board)move.get(i-1)).getFinished()) {
            alternate = !alternate;
            move.add(testMinMax.search(move.get(i-1),alternate));
            i++;
        }

        System.out.println("next state: ");
    
        System.out.println(move);
        System.out.println("Amount of turns: " + move.size());
    }
    
}
