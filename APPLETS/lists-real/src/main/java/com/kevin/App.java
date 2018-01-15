package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin Kimaru Chege on 8/23/2017.
 */
public class App extends Applet implements ActionListener {
    List os, browser;
    String msg = "";

    @Override
    public void init() {
        os = new List(4, true);
        browser = new List(4, false);

        os.add("Windows");
        os.add("Android");
        os.add("Solaris");
        os.add("Mac OS");

        browser.add("Internet Explorer");
        browser.add("Firefox");
        browser.add("Chrome");

        browser.select(1);

        // add lists to window
        add(os);
        add(browser);

        // register to receive action events
        os.addActionListener(this);
        browser.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        int idx[];
        msg = "Current OS: ";
        idx = os.getSelectedIndexes();
        for (int i = 0; i < idx.length; i++)
            msg += os.getItem(idx[i]) + "  ";
        g.drawString(msg, 6, 120);

        msg = "Current Browser: ";
        msg += browser.getSelectedItem();
        g.drawString(msg, 6, 140);
    }
}
