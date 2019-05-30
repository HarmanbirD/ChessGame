package ca.bcit.comp2526.assign2;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * Rook.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class Rook implements Piece, Serializable {

    int x;
    int y;
    String color;
    transient Image image;
    
    public Rook(int x, int y, String color) {
      this.x = x;
      this.y = y;
      this.color = color;
      shape();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean move(EnumMoves path, Piece test) {
        if (path.equals(EnumMoves.UP)) {
            return true;
        } else if (path.equals(EnumMoves.DOWN)) {
            return true;
        } else if (path.equals(EnumMoves.LEFT)) {
            return true;
        } else if (path.equals(EnumMoves.RIGHT)) {
            return true;
        } else {
            return false;
        }
    }  

    @Override
    public void shape() {
        String location = "Images/";
        String filename = this.color + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
    }
    
    @Override
    public String getName() {
        return "rook";
    }
    
    @Override
    public Image getImage() {
        return this.image;
    }
    
    @Override
    public String getColor() {
        return color;
    }
    
    @Override
    public EnumMoves[] getMovement() 
    {
        EnumMoves[] moves = new EnumMoves[4];
        moves[0] = EnumMoves.DOWN;
        moves[1] = EnumMoves.LEFT;
        moves[2] = EnumMoves.RIGHT;
        moves[3] = EnumMoves.UP;
        return moves;
    }
   
}
