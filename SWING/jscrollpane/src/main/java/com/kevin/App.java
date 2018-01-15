package com.kevin;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Kevin Kimaru Chege on 8/27/2017.
 */
public class App extends JApplet {
    private void stuff() {
        JPanel jP = new JPanel();
        jP.setLayout(new GridLayout(20, 20));
        int b = 0;

        for (int i = 0; i<20; i++) {
            for (int j = 0; j<20; j++) {
                jP.add(new JButton("Button" + b));
                ++b;
            }
        }

        JScrollPane jSP = new JScrollPane(jP);
        add(jSP, BorderLayout.CENTER);
    }

    @Override
    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    stuff();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
