/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn1;

import java.util.Comparator;

/**
 *
 * @author Vladimir
 */
public class DistanceComparator implements Comparator<Node>{
    Node destination;
    
    public DistanceComparator (Node inDes) {
        destination = inDes;
    }
    
    @Override
    public int compare(Node v1, Node v2) {
        int difXv1, difYv1, difXv2, difYv2, resultv1, resultv2;
        difXv1 = Math.abs(v1.xPos - destination.xPos);
        difYv1 = Math.abs(v1.yPos - destination.yPos);
        difXv2 = Math.abs(v2.xPos - destination.xPos);
        difYv2 = Math.abs(v2.yPos - destination.yPos);
        
        resultv1 = difXv1 + difYv1;
        resultv2 = difXv2 + difYv2;
        
        if (resultv1 > resultv2) {
            return 1;
        }
        
        if (resultv1 < resultv2) {
            return -1;
        }
        
        return 0;
    }
    
}
