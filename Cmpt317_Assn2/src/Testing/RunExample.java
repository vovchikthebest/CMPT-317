////////////////////////////////////////////////////////
//
// file:	RunExample.java
// Author: 	Michael C. Horsch
//		Department of Computer Science
// 		University of Saskatchewan
//
// History:	
// 		Created:	2007/09/19
//
// Notice:      This code is provided without warranty
//              for any purpose except to explore
//              search techniques in the study of AI.
//              Please do not distribute outside the class!
// 
////////////////////////////////////////////////////////



import PawnGame.Game;
import PawnGame.GameState;
import Search.*;

import java.util.Random;

public class RunExample {

  public static void main(String[] args) {

    int argc = 0;
    int ex = 2;

    Game game;

    switch (ex) {
      case 1:
      default:
	game = Example1.createExample();
	break;
      case 2:
	game = Example2.createExample();
	break;
      case 3:
	game = Example3.createExample();
	break;
      case 4:
	game = Example4.createExample();
	break;
    }
    
    AlphaBeta searcher = new AlphaBeta(game);
    
    int size = 4;
    
    GameState[] move = new GameState[size];
    
    boolean alternate = true;
    
    move[0] = searcher.search(((TrivGame)game).Initialize(),alternate);
    
    
    for (int i = 1; i < move.length; i++) {
        alternate = !alternate;
        move[i] = searcher.search(move[i-1],alternate);
    }

    System.out.println("next state: ");
    
    for (int i = 0; i < move.length; i++) {
        move[i].display();
    }
  }
}
// eof
