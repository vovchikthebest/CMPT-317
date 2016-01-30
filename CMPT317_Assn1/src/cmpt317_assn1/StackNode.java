/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn1;

/**
 * Note: StackNode was refactored/renamed from "Node", so look for any "Node" errors.
 * @author jared
 */
public class StackNode {
    //Variables of node objects.
    private StackNode next;
    private GraphNode item;
    
    /**
     * Constructor
     */
    StackNode(){
        next = null;
    }
    
    /**
     * Sets this nodes next to the parameter newNext.
     * @param newNext 
     */
    public void setNext(StackNode newNext) {
        next = newNext;
    }

    /**
     * Returns the next value of this node.
     * @return 
     */
    public StackNode getNext (){
        return this.next;
    }
    /**
     * Sets the current item to the parameter inItem.
     * @param inItem 
     */
    public void setItem (GraphNode inItem){
        item = inItem;
    }
    
    /**
     * Returns the current item at this node.
     * @return 
     */
    public GraphNode getItem(){
        return this.item;
    }
    
    /**
     * Returns true if the current graph node has been visited, false otherwise.
     * @return 
     */
    public Boolean isVisited(){
        return item.isVisited();
    }
    
    /**
     * Sets the current graph node as visited.
     */
    public void setVisited(){
        item.setVisited();
    }
    
}
