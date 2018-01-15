package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Kevin Kimaru Chege on 8/20/2017.
 */
public class App extends Applet implements KeyListener {
    String msg = "";

    int x = 10, y = 20;

    @Override
    public void init() {
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        msg += e.getKeyChar();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        showStatus("Key Down");

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_F1:
                msg += "<F1>";
                break;
            case KeyEvent.VK_F2:
                msg += "<F2>";
                break;
            case KeyEvent.VK_F3:
                msg += "<F3>";
                break;
            case KeyEvent.VK_PAGE_DOWN:
                msg += "<PgDn>";
                break;
            case KeyEvent.VK_PAGE_UP:
                msg += "<PgUp>";
                break;
            case KeyEvent.VK_LEFT:
                msg += "<Left Arrow>";
                break;
            case KeyEvent.VK_RIGHT:
                msg += "<right Arrow>";
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        showStatus("Key Up");
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg, x, y);
    }
}
