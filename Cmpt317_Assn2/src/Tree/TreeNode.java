/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.util.ArrayList;

/**
 *
 * @author Vladimir
 */
public class TreeNode<T> {
    public T data;
    public ArrayList<TreeNode> childNodes;
    
    public TreeNode(T inData) {
        this.data = inData;
    }
    
    public void addChild(T inChild) {
        TreeNode<T> childTree = new TreeNode<T>(inChild);
        this.childNodes.add(childTree);
    }
    
    public void addBulk(ArrayList<T> children) {
        for (int i = 0; i < children.size(); i++) {
            this.addChild(children.get(i));
        }
    }
}
