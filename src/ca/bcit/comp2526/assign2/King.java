package ca.bcit.comp2526.assign2;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * King.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class King implements Piece, Serializable {
    
    int x;
    int y;
    String color;
    transient Image image;
    
    public King(int x, int y, String color) {
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
//        if (validSingleMove(two)) {
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }

    @Override
    public void shape() {
        String location = "Images/";
        String filename = this.color + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
    }
    
    @Override
    public String getName() {
        return "king";
    }

    @Override
    public Image getImage() {
        return this.image;
    }
    
    @Override
    public String getColor() {
        return color;
    }
    

    public boolean validSingleMove(Piece test) {
        int xTest = this.getX() - test.getX();
        int yTest = this.getY() - test.getY();
        return xTest >= -1 && xTest <= 1 && yTest >= -1 && yTest <= 1;
    }

	@Override
	public EnumMoves[] getMovement() {
		EnumMoves[] moves = new EnumMoves[8];

        return moves;
	}

}
