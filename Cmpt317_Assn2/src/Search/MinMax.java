/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import PawnGame.Board;
import PawnGame.Game;
import PawnGame.GameImp;
import PawnGame.GameState;
import Tree.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Vladimir
 */
public class MinMax {
    
    Game curGame;
    int maxDepth;
    
    public MinMax (Game inGame, int inMaxDepth) {
       curGame = inGame;
       maxDepth = inMaxDepth;
    }
    
    public GameState search (GameState curBoard, boolean MaxGoesFirst) {
        TreeNode searchNode = new TreeNode(curBoard, 0);
        return this.MinMaxValue(searchNode, MaxGoesFirst).data;
    }
    
    public TreeNode MinMaxValue (TreeNode curNode, boolean MaxGoesFirst) {
        if (!MaxGoesFirst) { // Black turn
            return MinValue(curNode);
        } else {
            return MaxValue(curNode);
        }
    }
    
    public TreeNode MaxValue (TreeNode curNode) {
        ArrayList<GameState> successors = curGame.Successors(curNode.data);
        
        if (curGame.TerminalState(curNode.data)) {
            curNode.value = curGame.Utility(curNode.data); // Save utility as min/max value
            return curNode;
        } else if (curNode.depth == maxDepth) {
            curNode.value = curGame.Evaluate(curNode.data);
            return curNode;
        }
        
        TreeNode best = null;
        int bestValue = Integer.MIN_VALUE;
        Iterator<GameState> it = successors.iterator();
        
        while (it.hasNext()) {
            GameState curBoard = it.next();
            TreeNode workingNode = new TreeNode(curBoard, curNode.depth + 1);
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
        ArrayList<GameState> successors = curGame.Successors(curNode.data);
        
        if (curGame.TerminalState(curNode.data)) {
            curNode.value = curGame.Utility(curNode.data); // Save utility as min/max value
            return curNode;
        } else if (curNode.depth == maxDepth) {
            curNode.value = curGame.Evaluate(curNode.data);
            return curNode;
        }
        
        TreeNode best = null;
        int bestValue = Integer.MAX_VALUE;
        Iterator<GameState> it = successors.iterator();
        
        while (it.hasNext()) {
            GameState curBoard = it.next();
            TreeNode workingNode = new TreeNode(curBoard, curNode.depth + 1);
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
