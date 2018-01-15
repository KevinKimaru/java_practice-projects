package com.kevin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    App() {

        //create a new JFrame container
        JFrame jF = new JFrame("A simple swing application");

        jF.setLayout(new FlowLayout());

        //Give the frame an initial size
        jF.setSize(275, 100);

        //Terminate the program when the user closes the application
        jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create a text based label
        JLabel jL = new JLabel("SWING means powerful GUIs");
        JButton jB = new JButton("Alpha");
        JButton jB2 = new JButton("Beta");

        jB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jL.setText("You pressed Alpha");
            }
        });
        jB2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jL.setText("You pressed Beta");
            }
        });

        //Add the label to the content pane
        jF.add(jB);
        jF.add(jB2);
        jF.add(jL);

        //Duisplay the frame
        jF.setVisible(true);
    }

    public static void main(String[] args) {
        //crerate the frame on the event dispatching frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}
