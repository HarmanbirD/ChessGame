package ca.bcit.comp2526.assign2;

import java.io.Serializable;

/**
 * Move.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class Move implements Serializable {
    transient Piece x;
    transient Piece y;
    transient int xTest;
    transient int yTest;
    transient EnumMoves test;
    
    /**
     * Constructs an object of type Move.
     * @param x as piece to move.
     * @param y as piece to move to.
     */
    public Move(Piece x, Piece y) {
        this.x = x;
        this.y = y;
        this.movements();
    }
    
    /**
     * Calculates the pieces movements.
     */
    public void movements() {
        xTest = x.getX() - y.getX();
        yTest = x.getY() - y.getY();
    }
    
    /**
     * Returns what direction the piece moved in.
     * @return direction of movement of piece
     */
    public EnumMoves path() {
        if (xTest == 0 && yTest == 0) {
            test = EnumMoves.NULL;
        } else if (xTest == 2 && yTest == 1) {
            test = EnumMoves.LLEFTUP;
        } else if (xTest == -2 && yTest == 1) {
            test = EnumMoves.LRIGHTUP;
        } else if (xTest == 2 && yTest == -1) {
            test = EnumMoves.LLEFTDOWN;
        } else if (xTest == -2 && yTest == -1) {
            test = EnumMoves.LRIGHTDOWN;
        } else if (xTest == -1 && yTest == 2) {
            test = EnumMoves.LUPRIGHT;
        } else if (xTest == -1 && yTest == -2) {
            test = EnumMoves.LDOWNRIGHT;
        } else if (xTest == 1 && yTest == -2) {
            test = EnumMoves.LDOWNLEFT;
        } else if (xTest == 1 && yTest == 2) {
            test = EnumMoves.LUPLEFT;
        } else if (xTest > 0 && yTest == 0) {
            test = EnumMoves.LEFT;
        } else if (xTest < 0 && yTest == 0) {
            test = EnumMoves.RIGHT;
        } else if (xTest == 0 && yTest > 0) {
            test = EnumMoves.UP;
        } else if (xTest == 0 && yTest < 0) {
            test = EnumMoves.DOWN;
        } else if (xTest / yTest == 1 && xTest > 0 && yTest > 0) {
            test = EnumMoves.UPLEFT;
        } else if (xTest / yTest == 1 && xTest < 0 && yTest < 0) {
            test = EnumMoves.DOWNRIGHT;
        } else if (xTest / yTest == -1 && xTest > 0 && yTest < 0) {
            test = EnumMoves.DOWNLEFT;
        } else if (xTest / yTest == -1 && xTest < 0 && yTest > 0) {
            test = EnumMoves.UPRIGHT;
        } else {
            test = EnumMoves.NULL;
        }
        return test;
    }
    
    

}
