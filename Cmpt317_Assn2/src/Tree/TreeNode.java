/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import PawnGame.Board;
import java.util.ArrayList;

/**
 *
 * @author Vladimir
 */
public class TreeNode {
    public Board data;
    public int value; // Stores the min max value of the node
    public ArrayList<TreeNode> childNodes;
    
    public TreeNode(Board inData) {
        this.data = inData;
        value = 0;
    }
    
    public void addChild(Board inChild) {
        TreeNode childTree = new TreeNode(inChild);
        this.childNodes.add(childTree);
    }
    
    public void addBulk(ArrayList<Board> children) {
        for (int i = 0; i < children.size(); i++) {
            this.addChild(children.get(i));
        }
    }
}
