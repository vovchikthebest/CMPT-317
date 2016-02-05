package cmpt317_assn1;

import java.util.ArrayList;
import java.util.Stack;

public class Graph {
	Node[] graphNodes;
	int numNodes;
	int nodesPerSide;
	ArrayList<Node>[] nodeConnections;
	
	/**
	 * Creates an inNumNodes * inNumNodes graph
	 * @param inNumNodes length of graph side
	 */
	public Graph (int inNumNodes) {
		nodesPerSide = inNumNodes;
		numNodes = inNumNodes * inNumNodes;
		graphNodes = new Node[numNodes];
		for (int i = 0; i < nodesPerSide; i++) {
			for (int j = 0; j < nodesPerSide; j++) {
				// j - x-coordinate, i - y-coordinate
				graphNodes[j+(nodesPerSide)*i] = new Node(j, i, j+(nodesPerSide)*i);
			}
		}
		
		nodeConnections = new ArrayList[numNodes];
		for (int i = 0; i < numNodes; i++) {
			nodeConnections[i] = new ArrayList<Node>();
		}
	}
	
	public boolean addEdge (Node v1, Node v2) {
		if (this.edgeExists(v1, v2)){
			return false;
		}
		
		nodeConnections[v1.index].add(v2);
		nodeConnections[v2.index].add(v1);
		
		return true;
	}
	
	public ArrayList<Node> getNeighbours (Node v1) {
		return nodeConnections[v1.index];
	}
	
	public boolean edgeExists (Node v1, Node v2) {
		return nodeConnections[v1.index].contains(v2);
	}
	
	public void constructCityGraph () {
		for (int i = 0; i < nodesPerSide; i++) {
			for (int j = 0; j < nodesPerSide; j++) {
				// j - x-coordinate, i - y-coordinate
                                if ((i == (nodesPerSide-1)) && (j != (nodesPerSide-1))) {   // Top row - only add horizontal connections
                                    this.addEdge(graphNodes[j+(nodesPerSide)*i], graphNodes[j+1+(nodesPerSide)*i]);
                                } else if ((i != (nodesPerSide-1)) && (j == (nodesPerSide-1))) {    // Right most column - only add vertical connections
                                    this.addEdge(graphNodes[j+(nodesPerSide)*i], graphNodes[j+(nodesPerSide)*(i+1)]);
                                } else if ((i != (nodesPerSide-1)) && (j != (nodesPerSide-1))) {    // Otherwise add both
                                    this.addEdge(graphNodes[j+(nodesPerSide)*i], graphNodes[j+1+(nodesPerSide)*i]);
                                    this.addEdge(graphNodes[j+(nodesPerSide)*i], graphNodes[j+(nodesPerSide)*(i+1)]);
                                }
			}
		}
	}
        
        public int getNumNodes() {
            return this.numNodes;
        }
        
        public static void main (String[] args) {
            Graph test = new Graph(2);
            
            test.constructCityGraph();
            
            System.out.println(test.getNeighbours(test.graphNodes[2]));
            
            Stack<Node> test2 = Search.DepthFirst(test.graphNodes[0], test.graphNodes[2], test);
            
            System.out.println(test2);
        }
}
