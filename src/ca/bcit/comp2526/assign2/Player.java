package ca.bcit.comp2526.assign2;

import java.io.Serializable;

/**
 * Player.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class Player implements Serializable {
    
    static boolean firstPlayerTurn = true;
    String color;
    
    /**
     * Constructs an object of type Player.
     * @param color as color for player
     */
    public Player(String color) {
        this.color = color;
    }
    
    /**
     * @return the color of player
     */
    String getColor() {
        return color;
    }
    
    /**
     * Returns whos turn it is
     * @return true if player one turn, else false
     */
    public boolean getTurn() {
        return firstPlayerTurn;
    }
    
    /**
     * Sets the turn of player.
     */
    public void setTurn() {
        if (firstPlayerTurn == true) {
            firstPlayerTurn = false;
        } else {
            firstPlayerTurn = true;
        }
    }
}
