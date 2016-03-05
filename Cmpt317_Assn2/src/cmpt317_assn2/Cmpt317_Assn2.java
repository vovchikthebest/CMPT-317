/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn2;

import PawnGame.Board;
import PawnGame.GameImp;
import PawnGame.GameState;
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
        ArrayList<GameState> tester = test.Successors(testBoard);
        //MinMax testMinMax = new MinMax();
        
        System.out.println(tester);
    }
    
}
