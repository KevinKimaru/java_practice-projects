package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Kevin Kimaru Chege on 8/23/2017.
 */
public class App extends Applet implements ItemListener {
    Choice os, browser;
    String msg = "";

    @Override
    public void init() {
        os = new Choice();
        browser = new Choice();

        os.add("Windows");
        os.add("Linux");
        os.add("Mac OS");
        os.add("Android");

        browser.add("Chrome");
        browser.add("Opera");
        browser.add("Firefox");

        add(os);
        add(browser);

        os.addItemListener(this);
        browser.addItemListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        msg = "Current OS: ";
        msg += os.getSelectedItem();
        g.drawString(msg, 6, 120);

        msg = "Current Browser: ";
        msg += browser.getSelectedItem();
        g.drawString(msg, 6, 140);
    }
}
