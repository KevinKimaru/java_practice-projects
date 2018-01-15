package com.kevin;

import java.applet.Applet;
import java.awt.*;

/* <applet code="Sample" width=600 height=50> </applet> */

public class App extends Applet {
    String msg;

    //set the foreground and background colors

    @Override
    public void init() {
        setBackground(Color.cyan);
        setForeground(Color.red);
        msg = "Inside init( ) --";
    }

    // Initialize the string to be displayed.

    @Override
    public void start() {
        msg+=" Inside start( ) --";
    }

    // Display msg in applet window.
    @Override
    public void paint(Graphics g) {
        g.drawString(msg, 10, 30);
    }
}
