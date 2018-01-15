package com.kevin;

import java.applet.Applet;
import java.awt.*;

/* <applet code="App" width=700 height=50> </applet> */
public class App extends Applet implements Runnable {
    String msg;
    Thread t = null;
    int state;
    volatile boolean stopFlag;

    //set colors and initialize thread
    @Override
    public void init() {
        setBackground(Color.cyan);
        setForeground(Color.red);
    }

    //start thread
    @Override
    public void start() {
        msg = getParameter("message");
        if (msg == null) msg = "Message not found.";
        msg = " " + msg;
        t = new Thread(this);
        stopFlag = false;
        t.start();
    }

    // Entry point for the thread that runs the banner.
    @Override
    public void run() {
        //redisplay banner
        for (; ; ) {
            try {
                repaint();
                Thread.sleep(250);
                if (stopFlag) break;
            } catch (InterruptedException e) {

            }
        }
    }

    //pause the banner
    @Override
    public void stop() {
        stopFlag = true;
        t = null;
    }

    //display the banner

    @Override
    public void paint(Graphics g) {
        char ch;

        ch = msg.charAt(0);
        msg = msg.substring(1, msg.length());
        msg += ch;

        g.drawString(msg, 50, 30);
        showStatus("This is shown in the status window.");
    }
}
