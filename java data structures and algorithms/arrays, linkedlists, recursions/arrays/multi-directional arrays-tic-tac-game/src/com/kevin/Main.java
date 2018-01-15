package com.kevin;

public class Main {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        /** X moves */              /** O moves */
        game.putMark(1,1);    game.putMark(0,2);
        game.putMark(2, 2);             game.putMark(0, 0);
        game.putMark(0, 1);             game.putMark(2, 1);
        game.putMark(1,2);             game.putMark(1,0);
        game.putMark(2,0);
        System.out.println(game.toString());
        int winningPlayer = game.winner();
        if (winningPlayer != 0)
            System.out.println(winningPlayer + " wins");
        else
            System.out.println("Tie");
    }
}
