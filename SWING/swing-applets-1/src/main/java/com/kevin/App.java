package com.kevin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class App extends JApplet {
    JButton jbtnAlpha;
    JButton jbtnBeta;

    JLabel jlab;

    @Override
    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    new App();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // This applet does not need to override start(), stop(),
    // or destroy().
    // Set up and initialize the GUI.
    public App() {
        setLayout(new FlowLayout());

        jbtnAlpha = new JButton("Alpha");
        jbtnBeta = new JButton("Beta");

        jbtnAlpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlab.setText("You clicked Alpha");
            }
        });
        jbtnBeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlab.setText("You clicked Beta");
            }
        });

        add(jbtnAlpha);
        add(jbtnBeta);

        jlab = new JLabel("Press a button");

        add(jlab);
    }
}
