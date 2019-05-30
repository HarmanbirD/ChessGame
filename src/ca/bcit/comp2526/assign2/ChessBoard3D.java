package ca.bcit.comp2526.assign2;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;

/**
 * ChessBoard3D.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class ChessBoard3D extends Board {
    
    Square[][][] squares;
    Board[] board = new ChessBoard[3];
    Player one;
    Player two;
    static boolean secondClick;
    Square active;
    Square clicked;
    int activeBoard;
    /**
     * Current direction of pieces movement.
     */
    transient static EnumMoves enums;
     
    /**
     * 
     * Constructs an object of type Board.
     * @param one details about player one
     * @param two details about player two
     */
    public ChessBoard3D(Player one, Player two) {
        board[0] = new ChessBoard(one, two);
        board[1] = new ChessBoard(one, two);
        board[2] = new ChessBoard(one, two);
        create();
        create3DArray();
        this.one = one;
        this.two = two;
        active = null;
        secondClick = false;
        clicked = null;
        addNewEvent();
    }

    void create3DArray() {
        Square[][] squArrayOne      = board[0].getState();
        Square[][] squArrayTwo      = board[1].getState();
        Square[][] squArrayThree    = board[2].getState();
        Square[][][] square = {squArrayOne, squArrayTwo, squArrayThree};
        this.squares = square;
    }
    
    @Override
    void create() {
        board[0].create();  
        this.add(board[0], 0, 0);
        this.add(board[1], 1, 0);
        this.add(board[2], 2, 0);
        board[0].setBorder(new Border(new BorderStroke(Color.AQUAMARINE, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        board[1].setBorder(new Border(new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        board[2].setBorder(new Border(new BorderStroke(Color.VIOLET, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        createBoard();
    }

    @Override
    void createBoard() {
        board[1].createBoard();
        board[2].createBoard();  
    }
    
    /**
     * Adds new event handler to the Squares previously created.
     */
    private void addNewEvent() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final int x = i;
                final int y = j;
                ((ChessBoard) board[0]).squares[i][j].setOnAction( e -> onSpaceClick(0, x, y));
                ((ChessBoard) board[1]).squares[i][j].setOnAction( e -> onSpaceClick(1, x, y));
                ((ChessBoard) board[2]).squares[i][j].setOnAction( e -> onSpaceClick(2, x, y));

            }
        }
    }
    
    void print() {
        int counter = 0;
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                for (int k = 0; k < squares[i][j].length; k++) {
                    System.out.println(++counter + " " + squares[i][j][k].getPiece().getName());
                }
            }
        }
    }

    @Override
    void setActive(Square s) {

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

    void onSpaceClick(int x, int y, int z) {
        Square clicked = squares[x][y][z];

        if ((secondClick) || (checksTurn(clicked.getPiece()))) {
            if (active != null && 
                    !active.getPiece().getColor().equalsIgnoreCase(clicked.getPiece().getColor()) 
                    && pathClear(clicked)) {
                Piece temp = active.getPiece();
                if (onSameBoard(active, clicked)) {
                    if (temp.move(new Move(temp, clicked.getPiece()).path(), clicked.getPiece())) {
                        System.out.println(onSameBoard(active, clicked));
                        this.swap(clicked, temp, activeBoard, x);
                        one.setTurn();
                        secondClick = false;
                    } else {
                        this.setActive(null);
                        secondClick = false;
                    }
                } else {
                    if (moveToNextBoard(active, clicked)) {
                        System.out.println(onSameBoard(active, clicked));
                        this.swap(clicked, temp, activeBoard, x);
                        one.setTurn();
                        secondClick = false;
                    } else {
                        this.setActive(null);
                        secondClick = false;
                    }
                }
                this.setActive(null);
            } else if (active != null 
                    && !active.getPiece().getColor().equalsIgnoreCase(clicked.getPiece().getColor())) {
                this.setActive(null);
                secondClick = false;
            } else {
                if (clicked.getIsOccupied()) {
                    this.setActive(squares[x][y][z]);
                    activeBoard = x;
                    secondClick = true;
                } 
            }
        }
    }
    
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
    public void swap(Square clicked, Piece temp, int x, int y) {
        final int x1 = temp.getX();
        final int y1 = temp.getY();
        final int x2 = clicked.getPiece().getX();
        final int y2 = clicked.getPiece().getY();

            squares[x][x1][y1].setPiece(new Empty(x2, y2));
            squares[x][x1][y1].getPiece().setPosition(x1, y1);
            squares[y][x2][y2].setPiece(temp);
            squares[y][x2][y2].getPiece().setPosition(x2, y2);
    }

    @Override
    Square[][] getState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    boolean getPlayerTurn() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    void setAgain(Board board) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Checks to see if there is a piece between the active piece and 
     * the test piece.
     * @param test as pieces position to move to
     * @return true if path is clear, else false
     */
    public boolean pathClear(Square test) {
        enums = new Move(active.getPiece(), test.getPiece()).path();
        ((ChessBoard) board[boardNumb(test)]).setActive(active);
        return (((ChessBoard) board[boardNumb(test)]).pathClear(test.getPiece()));
    }
    
    /**
     * Checks to see if move is valid while moving to a new board.
     * @param start as Square the piece is originally on
     * @param finish as Square to move to
     * @return true if move is valid, else false
     */
    boolean moveToNextBoard(Square start, Square finish) {
        int boardMove = Math.abs(boardNumb(start) - boardNumb(finish));
        if ((start.getPiece().getX() - finish.getPiece().getX() == 0) 
                && (start.getPiece().getY() - finish.getPiece().getY() == 0)) {
            return false;
        }
        if (boardMove == 1) {
            if ((Math.abs(start.getPiece().getX() - finish.getPiece().getX()) > 1) 
                    || (Math.abs(start.getPiece().getY() - finish.getPiece().getY()) > 1)) {
                return false;
            }
        } else if (boardMove == 2) {
            if ((Math.abs(start.getPiece().getX() - finish.getPiece().getX()) > 2) 
                    || (Math.abs(start.getPiece().getY() - finish.getPiece().getY()) > 2
                            || Math.abs(start.getPiece().getX() - finish.getPiece().getX()) < 2) 
                    && (Math.abs(start.getPiece().getY() - finish.getPiece().getY()) < 2)) {
                return false;
            }
        } else {
            return false;
        }
        return (start.getPiece().move(enums, finish.getPiece()));
    }
    
    /**
     * Checks to see if the Piece is moving to a new board.
     * @param start as Square the Piece is originally on
     * @param finish as Square to move to
     * @return true if one same board, else false
     */
    boolean onSameBoard(Square start, Square finish) {
        return (boardNumb(start) == boardNumb(finish));
    }
    
    /**
     * Checks to see what board the piece is on
     * @param test as the Square the piece is on
     * @return board number that the piece is on.
     */
    int boardNumb(Square test) {
        int numb = -1;
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                for (int k = 0; k < squares[i][j].length; k++) {
                    if (squares[i][j][k].equals(test)) {
                        return i;
                    }
                }
            }
        }
        return numb;
    }
}
