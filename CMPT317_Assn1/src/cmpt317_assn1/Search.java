/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn1;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author jared
 */
public class Search {

    /**
     * Begins the search process.
     */
    public static Stack DepthFirst(Node Start, Node Finish, Graph searchGraph) {
        Stack<Node> searchStack = new Stack<Node>();
        searchStack.push(Start);
        boolean[] visited = new boolean[searchGraph.getNumNodes()];
        Stack<Node> path = new Stack<Node>();
        int toDelete = 0;
        while (!searchStack.isEmpty()) {
            Node current = searchStack.pop();
            //Node not visited
            if (!visited[current.index]) {
                //check for completion condition.
                if (current == Finish) {
                    path.push(current);
                    return path;
                } else {
                    path.push(current);
                }
                visited[current.index] = true;

                //Get the univisited neighbours and add them to the stack.
                ArrayList<Node> Neighbours = searchGraph.getNeighbours(current);
                //ArrayList<Node> Neighbours = searchGraph.getBetterNeighbours(current, Finish);
                int numNeighbours = 0;
                for (int i = 0; i < Neighbours.size(); i++) {
                    if (!visited[Neighbours.get(i).index]) {
                        searchStack.push(Neighbours.get(i));
                        numNeighbours++;
                    }
                }

                if (numNeighbours == 1) {
                    toDelete++;
                } else if (numNeighbours == 0) {
                    for (int i = 0; i < toDelete; i++) {
                        path.pop();
                    }
                }
            }
        }
        return null;
    }

    // ******* Distance gone portion of heuristic changes in comments!
    public static Stack AStarSearch(Node start, Node finish, Graph g) {
        DistanceComparator disComp = new DistanceComparator(finish);
        PriorityQueue<Node> q = new PriorityQueue(disComp);
        //PriorityQueue<QueueNode> q = new PriorityQueue(disComp);
        Stack<Node> result = new Stack();
        //QueueNode start = new QueueNode(instart);
        //q.add(start);
        q.add(start);
        //int distGonePrev = 0;     

        while (!q.isEmpty()) {
            Node current = q.poll();
            //QueueNode current = q.poll();
            //distGonePrev = current.getDistGone();
            //ArrayList neighbours = g.getNeighbours(current.graphNode);
            //  Iterates through the neighbours, initializes a queue node with the
            //  previous distance + this node, and adds it to the queue.
            //for (int i = 0; i<neighbours.size(); i++){
            //    QueueNode toAdd = new QueueNode(neighbours.get(i), distGonePrev + 1);
            //    q.add(toAdd);
            //}
            q.addAll(g.getNeighbours(current));
            result.push(current);
            //result.push(current.graphNode);

            if (current == finish) {
                return result;
            }
            //if(current.graphNode == finish){
            //  return result;
            //}
        }

        return null;
    }

    /**
     * Complete search for 1 courier and 1 package
     *
     * @param inCourier
     * @param inPackage
     * @param g
     * @return
     */
    public static Stack CompleteSearch(Courier inCourier, Package inPackage, Graph g) {
        Stack<Node> result = new Stack<Node>();

        result.addAll(Search.AStarSearch(inCourier.startPos, inPackage.currentNode, g));
        result.pop();
        result.addAll(Search.AStarSearch(inPackage.currentNode, inPackage.destinationNode, g));
        result.pop();
        result.addAll(Search.AStarSearch(inPackage.destinationNode, inCourier.startPos, g));

        return result;
    }

    public static Stack CompleteSearch(Courier inCourier, ArrayList<Package> inPackage, Graph g) {
        Stack<Node> result = new Stack<Node>();
        Package bestPackage = inPackage.get(0);
        boolean delivering = false;

        while (!inCourier.packages.isEmpty() || !inPackage.isEmpty()) {

            int shortestDistance = 999;
            
            for (int i = 0; i < (inCourier.packages.size()); i++) {
                int curDistance = Math.abs(inCourier.packages.get(i).destinationNode.xPos - inCourier.currentPosition.xPos)
                        + Math.abs(inCourier.packages.get(i).destinationNode.yPos - inCourier.currentPosition.yPos);
                if (curDistance < shortestDistance) {
                    shortestDistance = curDistance;
                    bestPackage = inCourier.packages.get(i);
                    delivering = true;
                }
            }
            
            if (inCourier.capacity != 0) {
                for (int i = 0; i < (inPackage.size()); i++) {
                    int curDistance = Math.abs(inPackage.get(i).currentNode.xPos - inCourier.currentPosition.xPos)
                            + Math.abs(inPackage.get(i).currentNode.yPos - inCourier.currentPosition.yPos);
                    if (curDistance < shortestDistance) {
                        shortestDistance = curDistance;
                        bestPackage = inPackage.get(i);
                        delivering = false;
                    }
                }
            }

            if (delivering == true) {
                result.addAll(Search.AStarSearch(bestPackage.currentNode, bestPackage.destinationNode, g));
                result.pop();
                inCourier.deliverPackage(bestPackage);
                inCourier.moveCourier(bestPackage.destinationNode);
            } else {
                result.addAll(Search.AStarSearch(inCourier.currentPosition, bestPackage.currentNode, g));
                result.pop();
                inPackage.remove(bestPackage);
                inCourier.addPackage(bestPackage);
                inCourier.moveCourier(bestPackage.currentNode);
            }

        }
        
        result.addAll(Search.AStarSearch(inCourier.currentPosition, inCourier.startPos, g));

        return result;
    }
}
