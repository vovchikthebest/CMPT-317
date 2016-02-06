/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn1;

/**
 *
 * @author Vladimir
 */
public class Edge {
    Node destination;
    int weight;
    
    public Edge (Node inDes) {
        destination = inDes;
        weight = 1;
    }
    
    public Edge (Node inDes, int inWeight) {
        destination = inDes;
        weight = inWeight;
    }
    
}
