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
        
        int size = 24;
    
        GameState[] move = new GameState[size];
    
        boolean alternate = true;
    
        move[0] = testMinMax.search(testBoard,alternate);
    
    
        for (int i = 1; i < move.length; i++) {
            alternate = !alternate;
            move[i] = testMinMax.search(move[i-1],alternate);
        }

        System.out.println("next state: ");
    
        for (int i = 0; i < move.length; i++) {
            move[i].display();
            System.out.printf("\n");
        }
    }
    
}
