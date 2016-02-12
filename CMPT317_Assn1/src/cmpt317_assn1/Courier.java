/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpt317_assn1;

import java.util.ArrayList;

/**
 *
 * @author Vladimir
 */
public class Courier {

    Node startPos;
    Node currentPosition;
    int capacity;
    ArrayList<Package> packages;

    public Courier(Node inStart, int inCapacity) {
        startPos = inStart;
        currentPosition = inStart;
        capacity = inCapacity;
        packages = new ArrayList(capacity);
    }

    public boolean addPackage(Package inPackage) {
        if (capacity != 0) {
            packages.add(inPackage);
            capacity--;
            return true;
        }
        return false;
    }

    public boolean deliverPackage(Package inPackage) {
        if (packages.contains(inPackage)) {
            packages.remove(inPackage);
            inPackage.setDelivered();
            capacity++;
            return true;
        }
        return false;
    }

    public void moveCourier(Node inNode) {
        currentPosition = inNode;
    }
}
