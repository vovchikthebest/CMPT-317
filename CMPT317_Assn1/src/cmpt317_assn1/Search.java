/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn1;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author jared
 */
public class Search {
    
    
    /**
     * Begins the search process.
     */
    public ArrayList DepthFirst (Node Start, Node Finish, Graph searchGraph){
        Stack searchStack = new Stack<Node>();
        searchStack.push(start); 
        Boolean visited[] = new Boolean[searchGraph.getNumNodes];
        ArrayList<Node> path = new ArrayList <Node> ();
        while (!stack.isEmpty()){
            Node current = searchStack.pop();
            //Node not visited
            if (!visited[current.index()]){
                //check for completion condition.
                if(current == Finish){
                    return path;
                }
                else{
                    path.add (current);
                }
                visited[current.index()] = true;
                
                //Get the univisited neighbours and add them to the stack.
                ArrayList<Node> Neighbours = searchGraph.getNeighbours();
                for (int i =0; i < Neighbours.size(); i++){
                    if(!visited [Neighbours.get(i).index()])
                    searchStack.push(Neighbours.get(i));
                }
            }
            else{
               
            }
        }
    }
    
}
