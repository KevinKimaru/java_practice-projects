package com.kevin;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by Kevin Kimaru Chege on 8/22/2017.
 */
public class App extends Applet {
    @Override
    public void paint(Graphics g) {
        String msg = "";
        String fontList[];

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontList = ge.getAvailableFontFamilyNames();
        for (int i = 0; i < fontList.length; i++) {
            msg += fontList[i] + "    ";
        }
        g.drawString(msg, 10, 10);
    }
}
