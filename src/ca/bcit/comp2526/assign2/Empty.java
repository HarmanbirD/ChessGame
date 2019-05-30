package ca.bcit.comp2526.assign2;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * Empty.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class Empty implements Piece, Serializable {
    int x;
    int y;
    transient Image image;
    
    public Empty(int x, int y) {
        this.x = x;
        this.y = y;
        image = null;
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
        return true;
    }

    @Override
    public void shape() {
    }
    
    @Override
    public String getName() {
        return "empty";
    }
    
    @Override
    public Image getImage() {
        return this.image;
    }
    
    @Override
    public String getColor() {
        return null;
    }

	@Override
	public EnumMoves[] getMovement() {
		// TODO Auto-generated method stub
		return null;
	}
   
}
