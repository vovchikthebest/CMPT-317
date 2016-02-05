package cmpt317_assn1;


public class Node{
	int xPos, yPos, index;
	
	public Node (int inX, int inY, int inIndex) {
		xPos = inX;
		yPos = inY;
                index = inIndex;
	}
        
        public String toString () {
            return "'" + String.valueOf(this.index) + ":" + String.valueOf(this.xPos) + ", " + String.valueOf(this.yPos) + "'";
        }
}
