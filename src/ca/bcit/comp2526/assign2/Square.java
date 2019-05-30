package ca.bcit.comp2526.assign2;

import java.io.Serializable;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Square.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class Square extends Button implements Serializable {
    
    Piece piece;
    transient ImageView imageView;
    String color;
    
    /**
     * Constructs an object of type Square.
     * @param x as x coordinate of square
     * @param y as y coordinate of square
     * @param color as color of square
     */
    public Square(final int x, final int y, String color) {
        super();
        super.setStyle(
                "-fx-min-height:  50px;"
                    +"-fx-min-width:   50px;"
                    +"-fx-pref-height: 50px;"
                    +"-fx-pref-width:  50px;"
                    +"-fx-max-height:  50px;"
                    +"-fx-max-width:   50px;"
                    +"-fx-background-radius: 0em;"
                    + "-fx-background-color: "+color+";"
        );
        this.color = color;
        }

    /**
     * @return false if Square has an Empty Piece on it, else true
     */
    public boolean getIsOccupied() {
        return !(this.piece instanceof Empty);
    }
    
    /**
     * Sets the current Piece on the square and sets the graphic for the square
     * depending on the piece on the square
     * @param piece as Piece to set onto the square
     */
    public void setPiece(Piece piece) {
        this.piece = piece;

        if (this.piece != null && (this.getIsOccupied())) {
            this.setGraphic( new ImageView ( piece.getImage() ) );
        } else {
            this.setGraphic( new ImageView() );
        }
    }
    
    /**
     * @return current Piece on the Square
     */
    public Piece getPiece() {
        return piece;
    }
    
    /**
     * @return color of Square
     */
    public String getColor() {
        return color;
    }
}
