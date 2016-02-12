/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn1;

/**
 *
 * @author jared
 */
public class QueueNode {
    public Node graphNode; //access directly instead of creating methods to save time.
    int distGone;
    
    
    /**
     * Constructor - Takes in a graph node and previous distance gone in the search.
     * @param ingraphNode 
     */
    QueueNode(Node ingraphNode, int inDistGone){
        graphNode = ingraphNode;
        distGone = inDistGone;
    }
    
    /**
     * Increments the distance to this node by 1.
     */
    public void incDistance(){
        distGone++;
    }
    /**
     * Returns the distance gone to this node.
     * @return 
     */
    public int getDisGone(){
        return distGone;
    }
}
