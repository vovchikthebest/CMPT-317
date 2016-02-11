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
public class Package {
    Node currentNode;
    Node destinationNode;
    
    public Package (Node inCur, Node inDest) {
        currentNode = inCur;
        destinationNode = inDest;
    }
}
