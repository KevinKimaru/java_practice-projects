package com.kevin;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by Kevin Kimaru Chege on 8/21/2017.
 */
public class App extends Frame {
    String keyMsg = "This is a test.";
    String mouseMsg = "";
    int mouseX = 30, mouseY = 30;

    public App() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
               keyMsg += e.getKeyChar();
               repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                mouseMsg = "Mouse Down at " + mouseX + ", " + mouseY;
                repaint();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(keyMsg, 10, 40);
        g.drawString(mouseMsg, mouseX, mouseY);
    }

    // Create the window.
    public static void main(String[] args) {
        App a = new App();

        a.setSize(new Dimension(300, 200));
        a.setTitle("An AWT based application");
        a.setVisible(true);
    }
}
