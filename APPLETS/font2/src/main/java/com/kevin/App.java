package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Kevin Kimaru Chege on 8/22/2017.
 */
public class App extends Applet {
    int next = 0;
    Font f;
    String msg;

    @Override
    public void init() {
        f = new Font("Dialog", Font.PLAIN, 20);
        msg = "Dialog";
        setFont(f);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                next++;
                switch (next) {
                    case 0:
                        f = new Font("Dialog", Font.PLAIN, 20);
                        break;
                    case 1:
                        f = new Font("DialogInput", Font.PLAIN, 16);
                        break;
                    case 3:
                        f = new Font("SansSerif", Font.BOLD, 11);
                        break;
                    case 4:
                        f = new Font("Serif", Font.ITALIC, 34);
                        break;
                    case 5:
                        f = new Font("Monospaced", Font.BOLD, 18);
                        break;
                }
                if (next  == 4) next = -1;
                setFont(f);
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg, 4, 20);
        g.drawString(g.getFont().toString(), 4, 40);
    }
}
