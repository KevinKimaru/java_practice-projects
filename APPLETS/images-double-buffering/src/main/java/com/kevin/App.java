package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by Kevin Kimaru Chege on 8/24/2017.
 */
public class App extends Applet {
    int gap = 3;
    int x, y;
    boolean flicker = true;
    Image buffer = null;
    int w, h;

    @Override
    public void init() {
        Dimension d = getSize();
        w = d.width;
        h = d.height;

        buffer = createImage(w, h);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                flicker = false;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                flicker = true;
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics screengc = null;

        if (!flicker) {
            screengc = g;
            g = buffer.getGraphics();
        }

        g.setColor(Color.blue);
        g.fillRect(0, 0, w, h);

        g.setColor(Color.red);
        for (int i = 0; i < h; i+=gap)g.drawLine(i, 0, w-i, h);
        for (int i = 0; i < w; i+=gap)g.drawLine(0, i, w, h-i);

        g.setColor(Color.black);
        g.drawString("Press mouse button to double buffer", 10, h/2);

        g.setColor(Color.green);
        g.fillOval(x-gap, y-gap, gap*2+1, gap*2+1);

        if (!flicker) {
            screengc.drawImage(buffer, 0, 0, null);
        }
    }
}
