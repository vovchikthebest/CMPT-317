/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PawnGame;

import Search.AlphaBeta;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vladimir
 *
 */
public class PlayGame {

    GameImp curGame;
    Board curBoard;
    boolean playAsBlack;    // true - play as black, false - play as white
    int maxDepth;

    public PlayGame(GameImp inGame, Board inBoard, boolean play, int inMaxDepth) {
        curGame = inGame;
        curBoard = inBoard;
        playAsBlack = play;
        maxDepth = inMaxDepth;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        if (playAsBlack == false) {
            System.out.println("Playing as X (white)");
        } else {
            System.out.println("Playing as O (black)");
        }

        while (!curGame.TerminalState(curBoard)) {
            curBoard.display();
            if (curBoard.turn == playAsBlack) {
                boolean found = false;
                System.out.print("X - coordinate of piece to move: ");
                int fromX = scanner.nextInt();
                System.out.print("Y - coordinate of piece to move: ");
                int fromY = scanner.nextInt();

                System.out.print("X - coordinate to move to: ");
                int toX = scanner.nextInt();
                System.out.print("Y - coordinate to move to: ");
                int toY = scanner.nextInt();

                Board cloned = curBoard.clone();

                
                if (fromX < 0 || fromX > 5 || fromY < 0 || fromY > 5
                        || toX < 0 || toX > 5 || toY < 0 || toY > 5) {
                    found = false;
                } else {
                    int previousVal = cloned.gameBoard[fromX][fromY];
                    cloned.gameBoard[fromX][fromY] = 0;
                    cloned.gameBoard[toX][toY] = previousVal;

                    ArrayList<GameState> successors = curGame.Successors(curBoard);

                    found = false;

                    for (int i = 0; i < successors.size(); i++) {
                        if (cloned.equals(successors.get(i))) {
                            found = true;
                            break;
                        }
                    }

                }
                if (found) {
                    curBoard = cloned;
                    System.out.println("Valid move");
                } else {
                    System.out.println("Not a valid move");
                }
            } else {
                AlphaBeta alphaBeta = new AlphaBeta(curGame, maxDepth);
                curBoard = (Board) alphaBeta.search(curBoard, false);
            }
        }

        curBoard.display();
        if (curBoard.winner < 0) {
            System.out.println("Black wins");
        } else if (curBoard.winner > 0) {
            System.out.println("White wins");
        } else {
            System.out.println("Its a tie");
        }
    }
}
