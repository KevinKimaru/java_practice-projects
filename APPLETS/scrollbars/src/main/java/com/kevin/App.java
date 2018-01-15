package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by Kevin Kimaru Chege on 8/23/2017.
 */
public class App extends Applet implements AdjustmentListener {
    String msg;
    Scrollbar vertSB, horzSB;

    @Override
    public void init() {
        int width = 300;
        int height = 200;

        vertSB = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, height);
        vertSB.setPreferredSize(new Dimension(20, 200));

        horzSB = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, width);
        horzSB.setPreferredSize(new Dimension(200, 20));

        add(vertSB);
        add(horzSB);

        vertSB.addAdjustmentListener(this);
        horzSB.addAdjustmentListener(this);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                vertSB.setValue(y);
                horzSB.setValue(x);
                repaint();
            }
        });

    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        msg = "Vertical: " + vertSB.getValue();
        msg += ",  Horizontal: " + horzSB.getValue();
        g.drawString(msg, 6, 160);

        g.drawString("*", horzSB.getValue(), vertSB.getValue());
    }
}
