package cmpt317_assn1;

import java.util.ArrayList;
import java.util.Stack;

public class Graph {

    Node[] graphNodes;
    int numNodes;
    int nodesPerSide;
    ArrayList<Edge>[] nodeConnections;

    /**
     * Creates an inNumNodes * inNumNodes graph
     *
     * @param inNumNodes length of graph side
     */
    public Graph(int inNumNodes) {
        nodesPerSide = inNumNodes;
        numNodes = inNumNodes * inNumNodes;
        graphNodes = new Node[numNodes];
        for (int i = 0; i < nodesPerSide; i++) {
            for (int j = 0; j < nodesPerSide; j++) {
                // j - x-coordinate, i - y-coordinate
                graphNodes[j + (nodesPerSide) * i] = new Node(j, i, j + (nodesPerSide) * i);
            }
        }

        nodeConnections = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            nodeConnections[i] = new ArrayList();
        }
    }

    /**
     * Adds an edge between v1 and v2
     * @param v1
     * @param v2
     * @return 
     */
    public boolean addEdge(Node v1, Node v2) {
        if (this.edgeExists(v1, v2)) {
            return false;
        }

        Edge edgeV1 = new Edge(v2);    // Destination v2
        Edge edgeV2 = new Edge(v1);    // Destination v1

        nodeConnections[v1.index].add(edgeV1);
        nodeConnections[v2.index].add(edgeV2);

        return true;
    }

    /**
     * Returns a list of nodes that are dirrect neighbors of v1
     * @param v1
     * @return 
     */
    public ArrayList<Node> getNeighbours(Node v1) {
        ArrayList<Node> neighbourList = new ArrayList<Node>();
        ArrayList<Edge> edgeList = this.nodeConnections[v1.index];
        
        for (int i = 0; i < edgeList.size(); i++){
            neighbourList.add(edgeList.get(i).destination);
        }
        return neighbourList;
    }
    
    /**
     * Returns an arrayList of neighboring nodes with the best destination being the first
     * @param v1
     * @param v2 destination node
     * @return 
     */
    public ArrayList<Node> getBetterNeighbours(Node v1, Node v2) {
        ArrayList<Node> neighbourList = new ArrayList<Node>();
        ArrayList<Edge> edgeList = this.nodeConnections[v1.index];
        int highestTotal = -1;
        
        // Need largest element at the begining of the list
        for (int i = 0; i < edgeList.size(); i++) {
            Node destination = edgeList.get(i).destination;
            int xPosDiff = Math.abs(v2.xPos - destination.xPos);
            int yPosDiff = Math.abs(v2.yPos - destination.yPos);
            int total = xPosDiff + yPosDiff;
            
            if (total >= highestTotal) {
                highestTotal = total;
                neighbourList.add(0, destination);
            } else {
                neighbourList.add(destination);
            }
        }
        
        return neighbourList;
    }

    /**
     * Checks if an edge exists between v1 and v2
     * @param v1
     * @param v2
     * @return 
     */
    public boolean edgeExists(Node v1, Node v2) {
        ArrayList<Edge> edgeList = this.nodeConnections[v1.index];
        
        for (int i = 0; i < edgeList.size(); i++) {
            if (edgeList.get(i).destination == v2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Constructs a node city, each node is connected to at most 4 neighboring nodes
     */
    public void constructCityGraph() {
        for (int i = 0; i < nodesPerSide; i++) {
            for (int j = 0; j < nodesPerSide; j++) {
                // j - x-coordinate, i - y-coordinate
                if ((i == (nodesPerSide - 1)) && (j != (nodesPerSide - 1))) {   // Top row - only add horizontal connections
                    this.addEdge(graphNodes[j + (nodesPerSide) * i], graphNodes[j + 1 + (nodesPerSide) * i]);
                } else if ((i != (nodesPerSide - 1)) && (j == (nodesPerSide - 1))) {    // Right most column - only add vertical connections
                    this.addEdge(graphNodes[j + (nodesPerSide) * i], graphNodes[j + (nodesPerSide) * (i + 1)]);
                } else if ((i != (nodesPerSide - 1)) && (j != (nodesPerSide - 1))) {    // Otherwise add both
                    this.addEdge(graphNodes[j + (nodesPerSide) * i], graphNodes[j + 1 + (nodesPerSide) * i]);
                    this.addEdge(graphNodes[j + (nodesPerSide) * i], graphNodes[j + (nodesPerSide) * (i + 1)]);
                }
            }
        }
    }

    /**
     * Returns the number of nodes in the current graph
     * @return 
     */
    public int getNumNodes() {
        return this.numNodes;
    }

    public static void main(String[] args) {
        Graph test = new Graph(10);

        test.constructCityGraph();

        System.out.println(test.getNeighbours(test.graphNodes[0]));
        
        System.out.println(test.edgeExists(test.graphNodes[0], test.graphNodes[1]));

        Stack<Node> test2 = Search.DepthFirst(test.graphNodes[0], test.graphNodes[2], test);

        System.out.println(test2);
        
        DrawGraph test3 = new DrawGraph(test);
        
        test3.setSize(400,300);
	
	test3.setVisible(true);
        
        
        test3.paintAgain();
    }
}
