package ca.bcit.comp2526.assign2;

import java.io.Serializable;

/**
 * Board.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class ChessBoard extends Board implements Serializable {
    
    /**
     * Array of squares.
     */
    Square[][] squares;
    
    /**
     * Player one.
     */
    Player one;
    
    /**
     * Player two.
     */
    Player two;
    
    /**
     * First square clicked on, square that contains the piece to move.
     */
    transient static Square active;
    
    /**
     * True if active piece set.
     */
    transient static boolean secondClick;
    
    /**
     * Current direction of pieces movement.
     */
    transient static EnumMoves enums;
    
    boolean playerTurn = true;
    
    /**
     * 
     * Constructs an object of type Board.
     * @param one details about player one
     * @param two details about player two
     */
    public ChessBoard(Player one, Player two) {
        squares = new Square[8][8];
        this.one = one;
        this.two = two;
        active = null;
        secondClick = false;
        create();
    }
    
    /**
     * Sets all the Pieces on the board in their starting positions.
     */
    public void create() {
        createBoard();
        
        squares[0][0].setPiece(new Rook(0, 0, "black"));
        squares[7][0].setPiece(new Rook(7, 0, "black"));
        for (int i = 0; i < 8; i++) {
            squares[i][1].setPiece(new Pawn(i, 1, "black"));
        }
        squares[1][0].setPiece(new Knight(1, 0, "black"));
        squares[6][0].setPiece(new Knight(6, 0, "black"));
        squares[2][0].setPiece(new Bishop(2, 0, "black"));
        squares[5][0].setPiece(new Bishop(5, 0, "black"));
        squares[3][0].setPiece(new Queen(3, 0, "black"));
        squares[4][0].setPiece(new King(4, 0, "black"));

        squares[0][7].setPiece(new Rook(0, 7, "white"));
        squares[7][7].setPiece(new Rook(7, 7, "white"));
        for (int i = 0; i < 8; i++) {
            squares[i][6].setPiece(new Pawn(i, 6, "white"));
        }
        squares[1][7].setPiece(new Knight(1, 7, "white"));
        squares[6][7].setPiece(new Knight(6, 7, "white"));
        squares[2][7].setPiece(new Bishop(2, 7, "white"));
        squares[5][7].setPiece(new Bishop(5, 7, "white"));
        squares[3][7].setPiece(new Queen(3, 7, "white"));
        squares[4][7].setPiece(new King(4, 7, "white"));
    }
    
    /**
     * Creates the board by instantiating each square and setting it either
     * black or white.
     */
    public void createBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (((i + j) % 2) == 0 ) {
                     squares[i][j] = new Square(i, j, "#FFFFE0");
                    } else {
                         squares[i][j] = new Square(i, j, "#525252");
                    }
                final int x = i;
                final int y = j;
                squares[i][j].setOnAction( e -> onSpaceClick(x, y));
                squares[i][j].setPiece(new Empty(i, j));
                this.add(squares[i][j], i, j);
            }
        }
    }
    
    /**
     * Sets the first square pressed as the current active piece.
     * @param s as the square pressed.
     */
    public void setActive(Square s) {

        if (active != null) {
            active.setStyle(
                    "-fx-min-height:  50px;"
                        +"-fx-min-width:   50px;"
                        +"-fx-pref-height: 50px;"
                        +"-fx-pref-width:  50px;"
                        +"-fx-max-height:  50px;"
                        +"-fx-max-width:   50px;"
                        +"-fx-background-radius: 0em;"
                        + "-fx-background-color: "+active.getColor()+";"
            );
        }
        
        active = s;
        
        if (active != null) {
            s.setStyle(
                    "-fx-min-height:  50px;"
                        +"-fx-min-width:   50px;"
                        +"-fx-pref-height: 50px;"
                        +"-fx-pref-width:  50px;"
                        +"-fx-max-height:  50px;"
                        +"-fx-max-width:   50px;"
                        +"-fx-background-radius: 0em;"
                        + "-fx-background-color: red;"
            );
        }
    }
    
    public void setAttackable(Square s) 
    {
        s.setStyle(
                "-fx-min-height:  50px;"
                        +"-fx-min-width:   50px;"
                        +"-fx-pref-height: 50px;"
                        +"-fx-pref-width:  50px;"
                        +"-fx-max-height:  50px;"
                        +"-fx-max-width:   50px;"
                        +"-fx-background-radius: 0em;"
                        + "-fx-background-color: #f3f3f3;"
                );
    }

    /**
     * Sets the active piece and the square that user wants to move piece to. 
     * Determines if move is valid by calling on the pieces move method
     * and the pathClear() method. 
     * If yes, calls on the swap() method.
     * @param x as x value of square clicked
     * @param y as y value of square clicked
     */
    public void onSpaceClick(int x, int y) 
    {
        Square clicked = squares[x][y];

        if ((secondClick) || (checksTurn(clicked.getPiece()))) 
        {
            if (active != null && 
                !active.getPiece().getColor().equalsIgnoreCase(clicked.getPiece().getColor()) 
                && pathClear(clicked.getPiece())) 
            {
                Piece temp = active.getPiece();
                if (temp.move(new Move(temp, clicked.getPiece()).path(), clicked.getPiece())) 
                {
                    this.swap(clicked, temp);
                    one.setTurn();
                    playerTurn = one.getTurn();
                    secondClick = false;
                } else 
                {
                    this.setActive(null);
                    secondClick = false;
                }
                this.setActive(null);
            } else if (active != null 
                        && !active.getPiece().getColor().equalsIgnoreCase(clicked.getPiece().getColor())) 
            {
                this.setActive(null);
                secondClick = false;
            } else 
            {
                if (clicked.getIsOccupied()) 
                {
                    this.setActive(squares[x][y]);
//                    showPath(squares[x][y]);
                    secondClick = true;
                } 
            }
        }
    }
    
//    public boolean showPath(Square s) {
//        int j = active.getPiece().getY();
//        enums = new Move(active.getPiece(), test).path();
//        
//        switch (enums) {
//            case UP: 
//                for (j = active.getPiece().getY() - 1; j > test.getY(); j--) {
//                    if (squares[active.getPiece().getX()][j].getIsOccupied()) {
//                        return false;
//                    }
//                }
//                break;
//            case DOWN:
//                for (j = active.getPiece().getY() + 1; j < test.getY(); j++) {
//                    if (squares[active.getPiece().getX()][j].getIsOccupied()) {
//                        return false;
//                    }
//                }
//                break;
//            case LEFT:
//                for (j = active.getPiece().getX() - 1; j > test.getX(); j--) {
//                    if (squares[j][active.getPiece().getY()].getIsOccupied()) {
//                        return false;
//                    }
//                }
//                break;
//            case RIGHT:
//                for (j = active.getPiece().getX() + 1; j < test.getX(); j++) {
//                    if (squares[j][active.getPiece().getY()].getIsOccupied()) {
//                        return false;
//                    }
//                }
//                break;
//            case UPLEFT:
//                for (int i = active.getPiece().getX() - 1; i > test.getX(); i--) {
//                    if (squares[i][j - 1].getIsOccupied()) {
//                        return false;
//                    }
//                    j--;
//                }
//                break;
//            case DOWNLEFT:
//                for (int i = active.getPiece().getX() - 1; i > test.getX(); i--) {
//                    if (squares[i][j + 1].getIsOccupied()) {
//                        return false;
//                    }
//                    j++;
//                }
//                break;
//            case UPRIGHT:
//                for (int i = active.getPiece().getX() + 1; i < test.getX(); i++) {
//                    if (squares[i][j - 1].getIsOccupied()) {
//                        return false;
//                    }
//                    j--;
//                } 
//                break;
//            case DOWNRIGHT:
//                for (int i = active.getPiece().getX() + 1; i < test.getX(); i++) {
//                    if (squares[i][j + 1].getIsOccupied()) {
//                        return false;
//                    }
//                    j++;
//                }
//                break;
//            case NULL:
//                return false;
//            default:
//                break;
//        
//        }
//        return true;
//    }
    
    /**
     * Checks to see if there is a piece between the active piece and 
     * the test piece.
     * @param test as pieces position to move to
     * @return true if path is clear, else false
     */
    public boolean pathClear(Piece test) {
        int j = active.getPiece().getY();
        enums = new Move(active.getPiece(), test).path();
        
        switch (enums) {
            case UP: 
                for (j = active.getPiece().getY() - 1; j > test.getY(); j--) {
                    if (squares[active.getPiece().getX()][j].getIsOccupied()) {
                        return false;
                    }
                }
                break;
            case DOWN:
                for (j = active.getPiece().getY() + 1; j < test.getY(); j++) {
                    if (squares[active.getPiece().getX()][j].getIsOccupied()) {
                        return false;
                    }
                }
                break;
            case LEFT:
                for (j = active.getPiece().getX() - 1; j > test.getX(); j--) {
                    if (squares[j][active.getPiece().getY()].getIsOccupied()) {
                        return false;
                    }
                }
                break;
            case RIGHT:
                for (j = active.getPiece().getX() + 1; j < test.getX(); j++) {
                    if (squares[j][active.getPiece().getY()].getIsOccupied()) {
                        return false;
                    }
                }
                break;
            case UPLEFT:
                for (int i = active.getPiece().getX() - 1; i > test.getX(); i--) {
                    if (squares[i][j - 1].getIsOccupied()) {
                        return false;
                    }
                    j--;
                }
                break;
            case DOWNLEFT:
                for (int i = active.getPiece().getX() - 1; i > test.getX(); i--) {
                    if (squares[i][j + 1].getIsOccupied()) {
                        return false;
                    }
                    j++;
                }
                break;
            case UPRIGHT:
                for (int i = active.getPiece().getX() + 1; i < test.getX(); i++) {
                    if (squares[i][j - 1].getIsOccupied()) {
                        return false;
                    }
                    j--;
                } 
                break;
            case DOWNRIGHT:
                for (int i = active.getPiece().getX() + 1; i < test.getX(); i++) {
                    if (squares[i][j + 1].getIsOccupied()) {
                        return false;
                    }
                    j++;
                }
                break;
            case NULL:
                return false;
            default:
                break;
        
        }
        return true;
    }
    
    /**
     * Checks to see if piece clicked belongs to the person whos turn it is
     * @param test
     * @return
     */
    public boolean checksTurn(Piece test) {
        if (!(test instanceof Empty)) {
            if (one.getTurn() && test.getColor().equalsIgnoreCase("white")) {
                return true;
            } else if (!one.getTurn() && test.getColor().equalsIgnoreCase("black")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * Moves Piece to desired position. If it is an attack, Removes killed Piece.
     * @param clicked as square to move to or, position of piece to kill
     * @param temp as piece to be moved
     */
    public void swap(Square clicked, Piece temp) {
        final int x1 = temp.getX();
        final int y1 = temp.getY();
        final int x2 = clicked.getPiece().getX();
        final int y2 = clicked.getPiece().getY();

            squares[x1][y1].setPiece(new Empty(x2, y2));
            squares[x1][y1].getPiece().setPosition(x1, y1);
            squares[x2][y2].setPiece(temp);
            squares[x2][y2].getPiece().setPosition(x2, y2);
    }
    
    /**
     * Returns the array of squares that contain the information of all the current 
     * pieces on the board.
     */
    public Square[][] getState() {
        return squares;
    }
    
    /**
     * returns true if first player turn, else false if player two turn.
     */
    public boolean getPlayerTurn() {
        return playerTurn;
    }
    
    /**
     * Sets the board to the loaded board.
     * @param board as the saved board from the previous game.
     */
    public void setAgain(Board board) {
        Square[][] temp = board.getState();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece tempP = temp[i][j].getPiece();
                String pieceType = tempP.getName();
                switch (pieceType) {
                case "pawn":
                    squares[i][j].setPiece(new Pawn(i, j, tempP.getColor()));
                    break;
                case "rook":
                    squares[i][j].setPiece(new Rook(i, j, tempP.getColor()));
                    break;
                case "bishop":
                    squares[i][j].setPiece(new Bishop(i, j, tempP.getColor()));
                    break;
                case "king":
                    squares[i][j].setPiece(new King(i, j, tempP.getColor()));
                    break;
                case "queen":
                    squares[i][j].setPiece(new Queen(i, j, tempP.getColor()));
                    break;
                case "knight":
                    squares[i][j].setPiece(new Knight(i, j, tempP.getColor()));
                    break;
                case "empty":
                    squares[i][j].setPiece(new Empty(i, j));
                    break;
                default: 
                    break;
                }
                
            }
        }
        if (!board.getPlayerTurn()) {
            one.setTurn();
        }
    }
}


