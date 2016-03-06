/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import PawnGame.Board;
import PawnGame.Game;
import PawnGame.GameState;
import Tree.AlphaBetaNode;
import Tree.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Vladimir
 */
public class AlphaBeta {
    
    Game curGame;
    
    public AlphaBeta (Game inGame) {
       curGame = inGame;
    }
    
    public GameState search (GameState curBoard, boolean MaxGoesFirst) {
        AlphaBetaNode searchNode = new AlphaBetaNode(curBoard, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.AlphaBetaValue(searchNode, MaxGoesFirst).data;
    }
    
    public AlphaBetaNode AlphaBetaValue (AlphaBetaNode curNode, boolean MaxGoesFirst) {
        if (!MaxGoesFirst) { // Black turn
            return MinValue(curNode);
        } else {
            return MaxValue(curNode);
        }
    }
    
    public AlphaBetaNode MaxValue (AlphaBetaNode curNode) {
        ArrayList<GameState> successors = curGame.Successors(curNode.data);
        
        if (curGame.TerminalState(curNode.data)) {
            curNode.value = curGame.Utility(curNode.data); // Save utility as min/max value
            return curNode;
        } else if (curNode.depth == 10) {
            curNode.value = curGame.Evaluate(curNode.data);
            return curNode;
        }
        
        AlphaBetaNode best = new AlphaBetaNode(successors.get(0), curNode.depth + 1, curNode.alpha, curNode.beta);
        int bestValue = Integer.MIN_VALUE;
        Iterator<GameState> it = successors.iterator();
        
        while (it.hasNext()) {
            GameState curBoard = it.next();
            AlphaBetaNode workingNode = new AlphaBetaNode(curBoard, curNode.depth + 1, curNode.alpha, curNode.beta);
            AlphaBetaNode n = MinValue(workingNode);
            if (n.value > bestValue) {
                bestValue = n.value;
                workingNode.value = bestValue;
                best = workingNode;
            }
            
            curNode.alpha = bestValue;
            if (n.value >= curNode.beta) {
                break;
            }
        }
        
        return best;
    }
    
    public AlphaBetaNode MinValue (AlphaBetaNode curNode) {
        ArrayList<GameState> successors = curGame.Successors(curNode.data);
        
        if (curGame.TerminalState(curNode.data)) {
            curNode.value = curGame.Utility(curNode.data); // Save utility as min/max value
            return curNode;
        } else if (curNode.depth == 10) {
            curNode.value = curGame.Evaluate(curNode.data);
            return curNode;
        }
        
        AlphaBetaNode best = new AlphaBetaNode(successors.get(0), curNode.depth + 1, curNode.alpha, curNode.beta);
        int bestValue = Integer.MAX_VALUE;
        Iterator<GameState> it = successors.iterator();
        
        while (it.hasNext()) {
            GameState curBoard = it.next();
            AlphaBetaNode workingNode = new AlphaBetaNode(curBoard, curNode.depth + 1, curNode.alpha, curNode.beta);
            AlphaBetaNode n = MaxValue(workingNode);
            if (n.value < bestValue) {
                bestValue = n.value;
                workingNode.value = bestValue;
                best = workingNode;
            }
            
            curNode.beta = bestValue;
            if (n.value <= curNode.alpha) {
                break;
            }
        }
        
        return best;
    }
}
