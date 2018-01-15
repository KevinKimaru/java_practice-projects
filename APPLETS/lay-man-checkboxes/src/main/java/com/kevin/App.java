package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Kevin Kimaru Chege on 8/23/2017.
 */
public class App extends Applet implements ItemListener {
    String msg = "";
    String msg2 = "";
    Checkbox windows, android, solaris, mac;
    Checkbox a, b, c;
    CheckboxGroup cbg;

    @Override
    public void init() {
        windows = new Checkbox("Windows", null, true);
        android = new Checkbox("Android");
        solaris = new Checkbox("Solaris");
        mac = new Checkbox("Mac OS");

        add(windows);
        add(android);
        add(solaris);
        add(mac);

        windows.addItemListener(this);
        android.addItemListener(this);
        solaris.addItemListener(this);
        mac.addItemListener(this);


        cbg = new CheckboxGroup();
        a = new Checkbox("a", cbg, true);
        b = new Checkbox("b", cbg, false);
        c = new Checkbox("c", cbg, false);

        add(a);
        add(b);
        add(c);

        a.addItemListener(this);
        b.addItemListener(this);
        c.addItemListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        msg = "Current state: ";
        g.drawString(msg, 6, 80);
        msg = "  Windows: " + windows.getState();
        g.drawString(msg, 6, 100);
        msg = "  Android: " + android.getState();
        g.drawString(msg, 6, 120);
        msg = "  Solaris: " + solaris.getState();
        g.drawString(msg, 6, 140);
        msg = "  Mac OS: " + mac.getState();
        g.drawString(msg, 6, 160);

        msg2 = "Current Selection: ";
        msg2 += cbg.getSelectedCheckbox().getLabel();
        g.drawString(msg2, 6, 210);
    }
}
