import java.util.ArrayList;

public class Graph {
	Node[] graphNodes;
	int numNodes;
	ArrayList<Node>[] nodeConnections;
	
	public Graph (int inNumNodes) {
		numNodes = inNumNodes;
		graphNodes = new Node[numNodes];
		nodeConnections = new ArrayList[numNodes];
	}
	
}
