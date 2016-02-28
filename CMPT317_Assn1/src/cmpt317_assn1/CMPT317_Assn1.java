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
public class CMPT317_Assn1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int size = 100;
        int numPackages = 10000;
        int courierCapacity = 5;
        int numCouriers = 1;

        Graph testGraph = new Graph(size);

        testGraph.constructCityGraph();

        // Randomize Courier Position
        int courierPos = randInt(0, (size * size) - 1);
        Courier testCourier = new Courier(testGraph.graphNodes[courierPos], courierCapacity);

        // Randomize Package Position
        ArrayList<Package> packages = new ArrayList<Package>(numPackages);
        Package testPackage = null;
        for (int i = 0; i < numPackages; i++) {
            int randomStart = randInt(0, (size * size) - 1);
            int randomFinish = randInt(0, (size * size) - 1);
            testPackage = new Package(testGraph.graphNodes[randomStart], testGraph.graphNodes[randomFinish]);
            packages.add(testPackage);
        }

        // DFS Search with single package single carrier
        long startTime = System.nanoTime();
        Stack<Node> resultNodes = Search.DepthFirst(testCourier.startPos, testPackage.currentNode, testGraph);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("--- DFS search ---");
        if (size < 20) {
            System.out.println("Path: " + resultNodes);
        }
        System.out.println("Time (ms): " + String.valueOf(duration));

        ArrayList<Package> holder = new ArrayList<Package>();
        holder.add(testPackage);
        if (size < 20) {
            DrawGraph dfsDraw = new DrawGraph(testGraph, resultNodes, testCourier, holder, "DFS Draw");

            dfsDraw.setVisible(true);
            dfsDraw.setSize(400, 400);
            dfsDraw.paintAgain();
        }

        // A* Search with sinple package single carrier
        startTime = System.nanoTime();
        resultNodes = Search.CompleteSearch(testCourier, testPackage, testGraph);
        endTime = System.nanoTime();

        duration = (endTime - startTime) / 1000000;

        System.out.println("--- A* Search with sinple package single carrier ---");
        if (size < 20) {
            System.out.println("Path: " + resultNodes);
        }
        System.out.println("Time (ms): " + String.valueOf(duration));

        if (size < 20) {
            DrawGraph aStarDraw = new DrawGraph(testGraph, resultNodes, testCourier, holder, "A* Single Package");

            aStarDraw.setVisible(true);
            aStarDraw.setSize(400, 400);
            aStarDraw.paintAgain();
        }

        // A* Search with multiple packages
        holder = new ArrayList(packages);

        startTime = System.nanoTime();
        resultNodes = Search.CompleteSearch(testCourier, packages, testGraph);
        endTime = System.nanoTime();

        duration = (endTime - startTime) / 1000000;

        System.out.println("--- A* search with multiple packages single carrier ---");
        if (size < 20) {
            System.out.println("Path: " + resultNodes);
        }
        System.out.println("Time (ms): " + String.valueOf(duration));

        if (size < 20) {
            DrawGraph aStarDraw2 = new DrawGraph(testGraph, resultNodes, testCourier, holder, "A* Multiple Package");

            aStarDraw2.setVisible(true);
            aStarDraw2.setSize(400, 400);
            aStarDraw2.paintAgain();
        }

        System.out.println("BLUE = Courier Start, RED = Package Start, GREEN = Package Dest");
        // NEED FOR SPEED
    }

    public static int randInt(int min, int max) {
        return (min + (int) (Math.random() * ((max - min) + 1)));
    }

}
