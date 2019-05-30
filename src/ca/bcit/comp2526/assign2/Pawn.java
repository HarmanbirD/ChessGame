package ca.bcit.comp2526.assign2;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * Pawn.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class Pawn implements Piece, Serializable {
    
    int x;
    int y;
    String color;
    boolean firstMove;
    transient Image image;
    
    public Pawn(int x, int y, String color) {
      this.x = x;
      this.y = y;
      this.color = color;
      firstMove = true;
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
        if (path.equals(EnumMoves.UP) || path.equals(EnumMoves.DOWN)) {
            if (two instanceof Empty) {
                    if (firstMove) {
                        if (validFirstMove(two)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        if (validSingleMove(two)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
            } else {
                return false;
            }
        } else if (path.equals(EnumMoves.UPLEFT) || path.equals(EnumMoves.UPRIGHT)
                ||path.equals(EnumMoves.DOWNLEFT) || path.equals(EnumMoves.DOWNRIGHT)) {
            if (!(two instanceof Empty)) {
                if (attack(two)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean validSingleMove(Piece test) {
        if (checkColor(this)) {
            if ((this.getY() + 1) == test.getY() && (this.getX()) == test.getX()) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((this.getY() - 1) == test.getY() && (this.getX()) == test.getX()) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public boolean validFirstMove(Piece test) {
        if (validSingleMove(test)) {
            firstMove = false;
            return true;
        } else if (checkColor(this) 
                && (this.getY() + 2) == test.getY() && (this.getX()) == test.getX()) {
            firstMove = false;
            return true;
        } else if (!checkColor(this) 
                && (this.getY() - 2) == test.getY() && (this.getX()) == test.getX()) {
            firstMove = false;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean attack(Piece test) {
        if (checkColor(this)) {
            if (((this.getX() + 1) == test.getX()) && ((this.getY() + 1) == test.getY()) 
                    || (((this.getX() - 1) == test.getX()) && ((this.getY() + 1) == test.getY()))) {
                return true;
            } else {
                return false;
            }
        } else {
            if (((this.getX() + 1) == test.getX()) && ((this.getY() - 1) == test.getY()) 
                    || (((this.getX() - 1) == test.getX()) && ((this.getY() - 1) == test.getY()))) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public boolean checkColor(Piece test) {
        if (this.getColor().equalsIgnoreCase("black")) {
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
        return "pawn";
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
		EnumMoves[] moves = new EnumMoves[2];
        moves[0] = EnumMoves.DOWN;
        moves[1] = EnumMoves.UP;
        return moves;
	}

}
