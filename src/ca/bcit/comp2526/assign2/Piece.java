package ca.bcit.comp2526.assign2;

import javafx.scene.image.Image;

/**
 * Piece.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public interface Piece {
    
    /**
     * @return x coordinate of Piece
     */
    int getX();
    
    /**
     * @return y coordinate of Piece
     */
    int getY();
        
    /**
     * Sets new coordinates of piece
     * @param x as new x coordinate
     * @param y as new y coordinate
     */
    void setPosition(int x, int y);
        
    /**
     * Checks to see if Piece can move to the desired square
     * @param path as direction of piece movement
     * @param test as piece on the board to move to
     * @return true if move is valid, else false
     */
    boolean move(EnumMoves path, Piece test);
    
    /**
     * Sets the image for each Piece
     */
    void shape();

    /**
     * @return image of Piece.
     */
    Image getImage();
    
    /**
     * @return type of piece as a string
     */
    String getName();
    
    /**
     * @return the color of Piece
     */
    String getColor();
    
    /**
     * @return what movement piece can have
     */
    EnumMoves[] getMovement();
}
