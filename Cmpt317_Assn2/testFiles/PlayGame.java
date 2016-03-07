/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PawnGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Vladimir
 */
public class PlayGame extends JFrame {

    GameImp curGame;
    Board curBoard;
    Square[][] squares;
    Container container;
    ArrayList<JLabel> whitePiece;
    ArrayList<JLabel> blackPiece;

    public PlayGame(int inSize) {
        super("Pawned!");
        curGame = new GameImp();
        curBoard = new Board(inSize, false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setResizable(false);
        this.setVisible(true);
        this.createSquares();
        this.createPiecesFromBoard();
    }

    public void createSquares() {
        container = this.getContentPane();
        container.setLayout(new GridLayout(curBoard.size, curBoard.size));
        squares = new Square[curBoard.size][curBoard.size];
        MouseHandler handler = new MouseHandler(this.curGame, this.curBoard, this);
        for (int i = 0; i < curBoard.size; i++) {
            for (int j = 0; j < curBoard.size; j++) {
                Square panel = new Square(i, j);
                panel.setBackground(this.getColor(i, j));
                panel.addMouseListener(handler);
                container.add(panel);
                squares[i][j] = panel;
            }
        }
    }

    public Color getColor(int x, int y) {
        if ((x + y) % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    public void createArrays() {
        whitePiece = new ArrayList<JLabel>();
        blackPiece = new ArrayList<JLabel>();
        
        curGame.createPawnArray(curBoard);
        for (int i = 0; i < curGame.teamWhite.size(); i++) {
            JLabel piece = new JLabel("White piece");
            whitePiece.add(piece);
        }

        for (int i = 0; i < curGame.teamBlack.size(); i++) {
            JLabel piece = new JLabel("Black piece");
            blackPiece.add(piece);
        }
    }

    public void createPiecesFromBoard() {
        if (blackPiece == null && whitePiece == null) {
            this.createArrays();
            for (int i = 0; i < curGame.teamWhite.size(); i++) {
                squares[curGame.teamWhite.get(i).y][curGame.teamWhite.get(i).x].add(whitePiece.get(i));
            }

            for (int i = 0; i < curGame.teamBlack.size(); i++) {
                squares[curGame.teamBlack.get(i).y][curGame.teamBlack.get(i).x].add(blackPiece.get(i));
            }
        } else {
            for (int i = 0; i < curGame.teamWhite.size(); i++) {
                squares[curGame.teamWhite.get(i).y][curGame.teamWhite.get(i).x].remove(whitePiece.get(i));
            }

            for (int i = 0; i < curGame.teamBlack.size(); i++) {
                squares[curGame.teamBlack.get(i).y][curGame.teamBlack.get(i).x].remove(blackPiece.get(i));
            }
            
            this.createArrays();
            for (int i = 0; i < curGame.teamWhite.size(); i++) {
                squares[curGame.teamWhite.get(i).y][curGame.teamWhite.get(i).x].add(whitePiece.get(i));
            }

            for (int i = 0; i < curGame.teamBlack.size(); i++) {
                squares[curGame.teamBlack.get(i).y][curGame.teamBlack.get(i).x].add(blackPiece.get(i));
            }
            System.out.println("I am here");
        }
    }

}
