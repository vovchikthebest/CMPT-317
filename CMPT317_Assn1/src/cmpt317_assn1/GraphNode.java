/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn1;

/**
 * This class will be each node within the graph.
 * It must hold a list of all connected edges or use some other item like an adjacency list to keep track of edges.
 * @author jared
 */
class GraphNode {
    Boolean visited;
    
    GraphNode (){
        visited = false;
    }
    
    /**
     * Returns true if this node has been visited, false otherwise.
     * @return 
     */
    public Boolean isVisited(){
        return visited;
    }
    
    /**
     * Sets this node to have been visited during search.
     */
    public void setVisited(){
        visited = true;
    }
}
