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
public class DepthFirstSearch {
    
    SearchStack stack;
    StackNode current;
    
    /**
     * Constructor
     */
    DepthFirstSearch(GraphNode start){
        stack = new SearchStack();
        stack.push(start); 
    }
    
    /**
     * Begins the search process.
     */
    public void Search (){
        while (!stack.isEmpty()){
            current = stack.pop();
            if (!current.isVisited()){
                //check for completion condition.
            }
            else{
                current.setVisited();
                //Push all the connected nodes to the stack.
            }
        }
    }
    
}
