package com.kevin;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by Kevin Kimaru Chege on 8/21/2017.
 */
public class App extends Applet {
    @Override
    public void paint(Graphics g) {
        g.drawLine(10, 10, 200, 10);

        g.drawRect(10, 150, 60, 50);
        g.fillRect(100, 150, 60, 50);
        g.drawRoundRect(190, 150, 60, 50,15, 15);
        g.fillRoundRect(280, 150, 60, 50,15, 15);

        g.drawOval(10, 250, 50, 50);
        g.fillOval(100, 250, 50, 50);
        g.drawOval(200, 250, 150, 50);

        g.drawArc(10, 350, 50, 50, 90, 180);
        g.fillArc(100, 350, 50, 50, 90, 180);

        int xpoints[] = {10, 200, 10, 200, 10};
        int ypoints[] = {450, 450, 650, 650, 450};
        g.drawPolygon(xpoints, ypoints, 5);
    }
}
