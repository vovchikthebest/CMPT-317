/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JFrame;

/**
 *
 * @author Vladimir
 */
public class DrawGraph extends JFrame{
    
    Graph toDraw;
    ArrayList<Node> path;
    Courier courier;
    ArrayList<Package> packages;
    int scalingFactor = 30;
    int moveX = 40;
    int moveY = 50;
    
    public DrawGraph (Graph inGraph, Stack<Node> inPath, Courier inCourier, 
            ArrayList<Package> inPackage, String Name) {
        super(Name);
        path = new ArrayList(inPath);
        courier = inCourier;
        packages = new ArrayList(inPackage);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toDraw = inGraph;
    }
    
    @Override
    public void paint (Graphics g) {
	g.setColor(Color.lightGray);
        
        // Draw a line for every edge in the graph
	for (int i = 0; i < toDraw.nodeConnections.length; i++) {
            for (int j = 0; j < toDraw.nodeConnections[i].size(); j++) {
                g.drawLine(toDraw.graphNodes[i].xPos * scalingFactor + moveX, toDraw.graphNodes[i].yPos * scalingFactor + moveY,
                        toDraw.nodeConnections[i].get(j).destination.xPos * scalingFactor + moveX, 
                        toDraw.nodeConnections[i].get(j).destination.yPos * scalingFactor + moveY);
            }
	}
        
        // Draw a line for every path traveled in the graph
        for (int i = 1; i < (path.size()); i++) {
            g.setColor(Color.black);
            g.drawLine(path.get(i-1).xPos * scalingFactor + moveX, path.get(i-1).yPos * scalingFactor + moveY,
                    path.get(i).xPos * scalingFactor + moveX, path.get(i).yPos * scalingFactor + moveY);
        }

        // Draw a dot for every node in the graph
        for (Node graphNode : toDraw.graphNodes) {
            //int nodeWidth = Math.max(width, f.stringWidth(n.name)+width/2);
            g.setColor(Color.black);
            g.fillOval(graphNode.xPos * scalingFactor - 10/2 + moveX, graphNode.yPos * scalingFactor - 10/2 + moveY, 10, 10);
            g.setColor(Color.red);
            g.drawString(String.valueOf(graphNode.index), graphNode.xPos * scalingFactor - 10/2 + moveX, graphNode.yPos * scalingFactor - 8/2 + moveY);
        }
        
        // Draw a dot for every courier node and every package node
        g.setColor(Color.blue);
        g.fillOval(courier.startPos.xPos * scalingFactor - 10/2 + moveX, courier.startPos.yPos * scalingFactor - 10/2 + moveY, 10, 10);
        
        for (int i = 0; i < packages.size(); i++ ) {
            g.setColor(Color.red);
            g.fillOval(packages.get(i).currentNode.xPos * scalingFactor - 10/2 + moveX, packages.get(i).currentNode.yPos * scalingFactor - 10/2 + moveY, 10, 10);
            g.setColor(Color.green);
            g.fillOval(packages.get(i).destinationNode.xPos * scalingFactor - 10/2 + moveX, packages.get(i).destinationNode.yPos * scalingFactor - 10/2 + moveY, 10, 10);
        }
    }
    
    public void paintAgain() {
        this.repaint();
    }
}
