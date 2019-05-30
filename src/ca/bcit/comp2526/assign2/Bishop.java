package ca.bcit.comp2526.assign2;


import java.io.Serializable;
import javafx.scene.image.Image;

/**
 * Bishop.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class Bishop implements Piece, Serializable {

    int x;
    int y;
    String color;
    transient Image image;
    
    public Bishop(int x, int y, String color) {
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
        if (path.equals(EnumMoves.UPLEFT)) {
            return true;
        } else if (path.equals(EnumMoves.UPRIGHT)) {
            return true;
        } else if (path.equals(EnumMoves.DOWNLEFT)) {
            return true;
        } else if (path.equals(EnumMoves.DOWNRIGHT)) {
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
        return "bishop";
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
        moves[0] = EnumMoves.DOWNLEFT;
        moves[1] = EnumMoves.DOWNRIGHT;
        moves[2] = EnumMoves.UPRIGHT;
        moves[3] = EnumMoves.UPLEFT;
        return moves;
    }
   
}
