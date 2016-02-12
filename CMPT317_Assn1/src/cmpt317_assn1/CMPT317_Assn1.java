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
        int size = 10;
        int numPackages = 3;
        int courierCapacity = 1;
        int numCouriers = 1;
        
        Graph testGraph = new Graph(size);
        DrawGraph testDraw = new DrawGraph(testGraph);
        
        testGraph.constructCityGraph();
        
        Courier testCourier = new Courier (testGraph.graphNodes[0], courierCapacity);
        
        Package testPackage1 = new Package (testGraph.graphNodes[2], testGraph.graphNodes[8]);
        Package testPackage2 = new Package (testGraph.graphNodes[6], testGraph.graphNodes[3]);
        Package testPackage3 = new Package (testGraph.graphNodes[4], testGraph.graphNodes[7]);
        
        ArrayList<Package> packages = new ArrayList<Package>(numPackages);
        packages.add(testPackage1);
        packages.add(testPackage2);
        packages.add(testPackage3);
        
        long startTime = System.nanoTime();
        Stack<Node> resultNodes = Search.CompleteSearch(testCourier, packages, testGraph);
        long endTime = System.nanoTime();
        
        long duration = (endTime - startTime)/1000000;

        
        System.out.println("Path: " + resultNodes);
        System.out.println("Time (ms): " + String.valueOf(duration));
        
        testDraw.setVisible(true);
        testDraw.setSize(400,400);
        testDraw.paintAgain();
        
        // NEED FOR SPEED
    }
    
}
