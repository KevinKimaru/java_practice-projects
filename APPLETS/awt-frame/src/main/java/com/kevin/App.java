package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

class SampleFrame extends Frame {
    String msg = "";
    int mouseX = 10, mouseY = 40;
    int movX = 0, movY = 0;

    SampleFrame(String title) {
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                msg = "Down";
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                msg = "Up";
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseX = 10;
                mouseY = 54;
                msg = "Mouse just entered child.";
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseX = 10;
                mouseY = 54;
                msg = "Mouse just left child window.";
                repaint();
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }


        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                movX = e.getX();
                movY = e.getY();
                msg = "*";
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                movX = e.getX();
                movY = e.getY();
                repaint(0, 0, 100, 60);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg, mouseX, mouseY);
        g.drawString("Mouse at " + movX + ", " + movY, 10, 40);
    }


}

public class App extends Applet {
    Frame f;

    String msg = "";
    int mouseX = 0, mouseY = 10;
    int movX = 0, movY = 0;

    @Override
    public void init() {
        f = new SampleFrame("A frame window");

        f.setSize(250, 250);
        f.setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                msg = "Down";
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                msg = "Up";
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseX = 10;
                mouseY = 54;
                msg = "Mouse just entered applet";
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseX = 10;
                mouseY = 54;
                msg = "Mouse just left applet.";
                repaint();
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }


        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                movX = e.getX();
                movY = e.getY();
                msg = "*";
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                movX = e.getX();
                movY = e.getY();
                repaint(0, 0, 100, 60);
            }
        });
    }

    @Override
    public void start() {
        f.setVisible(true);
    }

    @Override
    public void stop() {
        f.setVisible(false);
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg, mouseX, mouseY);
        g.drawString("Mouse at " + movX + ", " + movY, 10, 40);
    }
}
