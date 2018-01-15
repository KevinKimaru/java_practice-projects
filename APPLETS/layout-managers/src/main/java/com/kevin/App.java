package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin Kimaru Chege on 8/23/2017.
 */
public class App extends Applet implements ActionListener {
    String msg = "";
    Button bList[] = new Button[3];

    @Override
    public void init() {
        Label one = new Label("One");
        Label two = new Label("Two");
        Label three = new Label("Three");

        add(one);
        add(two);
        add(three);

        Button yes = new Button("yes");
        Button no = new Button("no");
        Button maybe = new Button("maybe");

        add(yes);
        add(no);
        add(maybe);

        yes.addActionListener(e -> {
                    String s = e.getActionCommand();

                    if (s.equals("yes")) {
                        msg = "You pressed yes";
                    }
                    repaint();
                }
        );

        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();

                if (s.equals("no")) {
                    msg = "You pressed no";
                }
                repaint();

            }
        });

        maybe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();

                if (s.equals("maybe")) {
                    msg = "You pressed maybe";
                }
                repaint();
            }
        });

        Button a = new Button("a");
        Button b = new Button("b");
        Button c = new Button("c");

        bList[0] = (Button) add(a);
        bList[1] = (Button) add(b);
        bList[2] = (Button) add(c);

        for (int i = 0; i < 3; i++) bList[i].addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg, 0, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            if (e.getSource() == bList[i]) {
                msg = "You pressed " + bList[i].getLabel();
            }
        }
        repaint();
    }
}
