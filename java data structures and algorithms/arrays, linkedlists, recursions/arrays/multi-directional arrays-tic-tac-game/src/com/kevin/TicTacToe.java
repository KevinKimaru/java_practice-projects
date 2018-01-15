package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 10/12/2017.
 */

/**
 * Simulation of a tic tac toe game
 */
public class TicTacToe {
    protected static final int X = 1, O = -1;       //players
    protected static final int EMPTY = 0;       //empty cell
    protected int board[][] = new int[3][3];        //game board
    protected int player;       //current player

    /**
     * constructor
     */
    public TicTacToe() {
        clearBoard();
    }

    /**
     * clears the board
     */
    public void clearBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = EMPTY;    //every cell should be empty
        player = X;     //the first player is X
    }

    /**
     * puts an X or O mark at position i, j
     */
    public void putMark(int i, int j) {
        if ((i < 0) || (i > 2) || (j < 0) || (j > 2))
            throw new IllegalArgumentException("Invalid board position");
        if (board[i][j] != EMPTY)
            throw new IllegalArgumentException("Board position occupied");
        board[i][j] = player;   //place the mark for the current player
        player = -player;   //switch players (uses fact thet  O =- X)
    }

    //checks whether the board configuration is a win for the given player
    public boolean isWin(int mark) {
        return (
                (board[0][0] + board[0][1] + board[0][2] == mark * 3)     //row 1
                        || (board[1][0] + board[1][1] + board[1][2] == mark * 3)  //row 2
                        || (board[2][0] + board[2][1] + board[2][2] == mark * 3)  //row 3
                        || (board[0][0] + board[1][0] + board[2][0] == mark * 3)  //column 1
                        || (board[0][1] + board[1][1] + board[2][1] == mark * 3)  //column 2
                        || (board[0][2] + board[1][2] + board[2][2] == mark * 3)  //column 3
                        || (board[0][0] + board[1][1] + board[2][2] == mark * 3)  //diagonal
                        || (board[2][0] + board[1][1] + board[0][2] == mark * 3)  //diagonal
        );
    }

    /** returns a winning player or 0 to indicate a tie */
    public int winner() {
        if (isWin(X))
            return X;
        else if (isWin(O))
            return O;
        else
            return 0;
    }

    //returns a simple character string showing the current board
    public String toString() {
        String s = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case X: s += "X"; break;
                    case O: s += "O"; break;
                    case EMPTY: s += " "; break;
                }
                if (j < 2) s += "|";
            }
            if (i < 2) s += "\n-----\n";
        }
        return s;
    }

}
