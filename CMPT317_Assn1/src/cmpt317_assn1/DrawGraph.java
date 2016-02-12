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
    int scalingFactor = 20;
    int moveX = 40;
    int moveY = 50;
    
    public DrawGraph (Graph inGraph) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toDraw = inGraph;
    }
    
    @Override
    public void paint (Graphics g) {
	g.setColor(Color.black);
        
        // Draw a line for every edge in the graph
	for (int i = 0; i < toDraw.nodeConnections.length; i++) {
            for (int j = 0; j < toDraw.nodeConnections[i].size(); j++) {
                g.drawLine(toDraw.graphNodes[i].xPos * scalingFactor + moveX, toDraw.graphNodes[i].yPos * scalingFactor + moveY,
                        toDraw.nodeConnections[i].get(j).destination.xPos * scalingFactor + moveX, 
                        toDraw.nodeConnections[i].get(j).destination.yPos * scalingFactor + moveY);
            }
	}

        for (Node graphNode : toDraw.graphNodes) {
            //int nodeWidth = Math.max(width, f.stringWidth(n.name)+width/2);
            g.setColor(Color.green);
            g.fillOval(graphNode.xPos * scalingFactor - 10/2 + moveX, graphNode.yPos * scalingFactor - 10/2 + moveY, 10, 10);
            g.setColor(Color.red);
            g.drawString(String.valueOf(graphNode.index), graphNode.xPos * scalingFactor - 10/2 + moveX, graphNode.yPos * scalingFactor - 8/2 + moveY);
        }
    }
    
    public void paintAgain() {
        this.repaint();
    }
    
    public void drawPath(Stack<Node> path, Color inColor){
        ArrayList<Node> pathList = new ArrayList(path);
        Graphics g = this.getGraphics();
        
        for (int i = 1; i < (pathList.size()); i++) {
            g.setColor(inColor);
            g.drawLine(pathList.get(i-1).xPos * scalingFactor + moveX, pathList.get(i-1).yPos * scalingFactor + moveY,
                    pathList.get(i).xPos * scalingFactor + moveX, pathList.get(i).yPos * scalingFactor + moveY);
        }
        
    }
}
