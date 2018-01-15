package com.kevin;

import java.util.Random;

/** Simulation of duck duck goose using circularly linked list */
public class MainDuckDuckGooseGame {

    public static void main(String[] args) {
	    CircleList c = new CircleList();
	    int N = 3; //Number of iterarions of the game
        Node it;  //the player who is  "it"
        Node goose; //the player who is goose
        Random rand  = new Random();
        rand.setSeed(System.currentTimeMillis());//use current time as seeds
        //The players
        String[] names = {"Bob", "Jen", "Pam", "Tom", "Ron", "Vic", "Sue", "Joe"};
        for (int i = 0; i < names.length; i++) {
            c.add(new Node(names[i], null));
            c.advance();
        }
        for (int i = 0; i < N; i++) {  //play duck duck goose N times
            System.out.println("Playing Duck, Duck, Goose for " + c.toString() );
            it = c.remove();
            System.out.println(it.getElement() + " is it.");
            while (rand.nextBoolean() || rand.nextBoolean()) { //match around circle
                c.advance(); //advance with probability of 3/4
                System.out.println(c.getCursor().getElement() + " is a Duck.");
            }
            goose = c.remove();
            System.out.println(goose.getElement() + " is the goose.");
            if (rand.nextBoolean()) {
                System.out.println("The goose won");
                c.add(goose); //add the goose back in its old place
                c.advance(); //now the cursor is on the goose
                c.add(it); //The It person will be it again in the next round
            } else {
                System.out.println("The goose Lost!");
                c.add(it); // add who's it  back at the goose's place
                c.advance(); //now the cursor is on the "It" person
                c.add(goose); //The goose will be it in the next round

            }
        }
        System.out.println("Final circle is " + c.toString());
    }
}
