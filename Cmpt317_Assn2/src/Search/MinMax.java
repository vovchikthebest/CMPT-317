/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import PawnGame.Board;
import PawnGame.Game;
import Tree.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Vladimir
 */
public class MinMax {
    
    Game curGame;
    
    public MinMax () {
       curGame = new Game();
    }
    
    public TreeNode search (Board curBoard) {
        TreeNode searchNode = new TreeNode(curBoard);
        return this.MinMaxValue(searchNode);
    }
    
    public TreeNode MinMaxValue (TreeNode curNode) {
        if (curNode.data.getTurn()) { // Black turn
            return MinValue(curNode);
        } else {
            return MaxValue(curNode);
        }
    }
    
    public TreeNode MaxValue (TreeNode curNode) {
        if (curNode.data.getFinished()) {
            curNode.value = curGame.getUtility(curNode.data); // Save utility as min/max value
            return curNode;
        }
        
        ArrayList<Board> successors = curGame.boardSuccessor(curNode.data);
        TreeNode best = null;
        int bestValue = Integer.MIN_VALUE;
        Iterator<Board> it = successors.iterator();
        
        while (it.hasNext()) {
            Board curBoard = it.next();
            TreeNode workingNode = new TreeNode(curBoard);
            TreeNode n = MinValue(workingNode);
            if (n.value > bestValue) {
                bestValue = n.value;
                workingNode.value = bestValue;
                best = workingNode;
            }
        }
        
        return best;
    }
    
    public TreeNode MinValue (TreeNode curNode) {
        if (curNode.data.getFinished()) {
            curNode.value = curGame.getUtility(curNode.data); // Save utility as min/max value
            return curNode;
        }
        
        ArrayList<Board> successors = curGame.boardSuccessor(curNode.data);
        TreeNode best = null;
        int bestValue = Integer.MAX_VALUE;
        Iterator<Board> it = successors.iterator();
        
        while (it.hasNext()) {
            Board curBoard = it.next();
            TreeNode workingNode = new TreeNode(curBoard);
            TreeNode n = MaxValue(workingNode);
            if (n.value < bestValue) {
                bestValue = n.value;
                workingNode.value = bestValue;
                best = workingNode;
            }
        }
        
        return best;
    }
}
