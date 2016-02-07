/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn1;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author Vladimir
 */
public class DrawGraph extends JFrame{
    
    Graph toDraw;
    
    public DrawGraph (Graph inGraph) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toDraw = inGraph;
    }
    
    @Override
    public void paint (Graphics g) {
	g.setColor(Color.black);
        int scalingFactor = 20;
        int moveX = 30;
        int moveY = 40;
        
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
            /*g.drawString(n.name, n.x-f.stringWidth(n.name)/2,
            n.y+f.getHeight()/2);*/
        }
    }
    
    public void paintAgain() {
        this.repaint();
    }
    
}
