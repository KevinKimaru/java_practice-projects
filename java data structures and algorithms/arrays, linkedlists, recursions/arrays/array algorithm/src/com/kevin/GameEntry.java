package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 10/5/2017.
 */
public class GameEntry {
    protected String name;
    protected int score;

    public GameEntry(String n, int s) {
        name = n;
        score = s;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return "(" + name + ", " + score + ")";
    }
}
