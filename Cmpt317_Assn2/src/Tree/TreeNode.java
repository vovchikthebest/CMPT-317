/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import PawnGame.Board;
import PawnGame.GameState;
import java.util.ArrayList;

/**
 *
 * @author Vladimir
 */
public class TreeNode {
    public GameState data;
    public int value; // Stores the min max value of the node
    public ArrayList<TreeNode> childNodes;
    public int depth;
    
    public TreeNode(GameState inData, int inDepth) {
        this.data = inData;
        value = 0;
        depth = inDepth;
    }
    
    public void addChild(GameState inChild, int inDepth) {
        TreeNode childTree = new TreeNode(inChild, inDepth);
        this.childNodes.add(childTree);
    }
    
    public void addBulk(ArrayList<GameState> children, int inDepth) {
        for (int i = 0; i < children.size(); i++) {
            this.addChild(children.get(i), inDepth);
        }
    }
}
