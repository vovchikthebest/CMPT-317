/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn2;

import PawnGame.Board;
import PawnGame.Game;
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
        Game test = new Game();
        Board testBoard = new Board(6, false);
        //ArrayList<Board> tester = test.boardSuccessor(testBoard);
        MinMax testMinMax = new MinMax();
        
        System.out.println(testMinMax.search(testBoard));
    }
    
}
