package ca.bcit.comp2526.assign2;

import java.io.Serializable;

import javafx.scene.layout.GridPane;

/**
 * Board.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public abstract class Board extends GridPane implements Serializable {
    Player one;
    Player two;
    transient boolean pressed;
    transient Square active;
    
    /**
     * Sets all the Pieces on the board in their starting positions.
     */
    abstract void create();
    
    /**
     * Creates the board by instantiating each square and setting it either
     * black or white.
     */
    abstract void createBoard();
    
    /**
     * Sets the first square pressed as the current active piece.
     * @param s as the square pressed.
     */
    abstract void setActive(Square s);
    
    /**
     * returns the array of squares.
     * @return Sqaure[][] of board
     */
    abstract Square[][] getState();
    
    /**
     * Returns what players turn it is.
     * @return true if player one turn, else false for player two turn.
     */
    abstract boolean getPlayerTurn();
    
    /**
     * Loads the saved board from the previous game. 
     * @param board as previous saved board.
     */
    abstract void setAgain(Board board);

}
