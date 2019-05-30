package ca.bcit.comp2526.assign2;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * Knight.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class Knight implements Piece, Serializable {
    
    int x;
    int y;
    String color;
    transient Image image;
    
    public Knight(int x, int y, String color) {
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
    public boolean move(EnumMoves path, Piece two) {
        if (path.equals(EnumMoves.LLEFTUP)) {
            return true;
        } else if (path.equals(EnumMoves.LLEFTDOWN)) {
            return true;
        } else if (path.equals(EnumMoves.LRIGHTUP)) {
            return true;
        } else if (path.equals(EnumMoves.LRIGHTDOWN)) {
            return true;
        } else if (path.equals(EnumMoves.LUPLEFT)) {
            return true;
        } else if (path.equals(EnumMoves.LUPRIGHT)) {
            return true;
        } else if (path.equals(EnumMoves.LDOWNLEFT)) {
            return true;
        } else if (path.equals(EnumMoves.LDOWNRIGHT)) {
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
        return "knight";
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
	public EnumMoves[] getMovement() {
		EnumMoves[] moves = new EnumMoves[8];
        moves[0] = EnumMoves.LLEFTUP;
        moves[1] = EnumMoves.LLEFTDOWN;
        moves[2] = EnumMoves.LRIGHTUP;
        moves[3] = EnumMoves.LRIGHTDOWN;
        moves[4] = EnumMoves.LUPLEFT;
        moves[5] = EnumMoves.LUPRIGHT;
        moves[6] = EnumMoves.LDOWNLEFT;
        moves[7] = EnumMoves.LDOWNRIGHT;
        return moves;
	} 
}
