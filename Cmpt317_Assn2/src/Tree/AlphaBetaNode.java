/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import PawnGame.GameState;

/**
 *
 * @author Vladimir
 */
public class AlphaBetaNode extends TreeNode{
    public int alpha, beta;

    public AlphaBetaNode(GameState inData, int inDepth, int inAlpha, int inBeta) {
        super(inData, inDepth);
        alpha = inAlpha;
        beta = inBeta;
    }
}
