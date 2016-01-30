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
public class SearchStack {
    StackNode top; //Always holds the node at the top of the stack.
    
    /**
     * Constructor
     */
    SearchStack(){
       top = null; 
    }
    
    /**
     * Returns true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return top == null;
    }

   /**
     * Add an item to the stack.
     */
    public void push(GraphNode inItem) {
        StackNode oldNode = top;
        top = new StackNode();
        top.setItem(inItem);
        top.setNext(oldNode);
    }

    /**
     * Pops the top element off of the stack and returns the value in it. 
     * TODO: Decide if this returns a stack node or the graph node "item" the stack node stores.
     * @return 
     */
    public StackNode pop (){
        if (!this.isEmpty()){
            StackNode toReturn = top;
            top = top.getNext(); // Move the top of the stack down one node.
            return toReturn;
        }
        else{
            return null;
        }
    }
    


}
