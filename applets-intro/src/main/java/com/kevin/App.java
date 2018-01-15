package com.kevin;

import java.applet.Applet;
import java.awt.*;

/*
<applet code="App" width="700" height="700"></applet>
 */
public class App extends Applet {
    @Override
    public void paint(Graphics g) {
        g.drawString("A Simple Applet", 20, 20);
    }
}
