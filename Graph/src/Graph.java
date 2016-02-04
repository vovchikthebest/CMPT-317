import java.util.ArrayList;

public class Graph {
	Node[][] graphNodes;
	int nodesPerSide;
	ArrayList<Node>[][] nodeConnections;
	
	/**
	 * Creates an inNumNodes * inNumNodes graph
	 * @param inNumNodes length of graph side
	 */
	public Graph (int inNumNodes) {
		nodesPerSide = inNumNodes;
		graphNodes = new Node[nodesPerSide][nodesPerSide];
		for (int i = 0; i < nodesPerSide; i++) {
			for (int j = 0; j < nodesPerSide; j++) {
				graphNodes[i][j] = new Node(i, j);
			}
		}
		nodeConnections = new ArrayList[nodesPerSide][nodesPerSide];
	}
	
	public boolean addEdge (Node v1, Node v2) {
		return false;
	}
	
	public Node[] getNeighboors (Node v1) {
		return null;
	}
}
