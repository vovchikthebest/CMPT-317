import java.util.ArrayList;

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
				graphNodes[j+4*i] = new Node(j, i, j+4*i);
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
	
	public ArrayList<Node> getNeighboors (Node v1) {
		return nodeConnections[v1.index];
	}
	
	public boolean edgeExists (Node v1, Node v2) {
		return nodeConnections[v1.index].contains(v2);
	}
	
	public void constructCityGraph () {
		for (int i = 0; i < nodesPerSide-1; i++) {
			for (int j = 0; j < nodesPerSide-1; j++) {
				// j - x-coordinate, i - y-coordinate
				this.addEdge(graphNodes[j+4*i], graphNodes[j+1+4*i]);
				this.addEdge(graphNodes[j+4*i], graphNodes[j+4*(i+1)]);
			}
		}
	}
}
