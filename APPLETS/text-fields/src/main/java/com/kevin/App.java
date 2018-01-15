package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;

/**
 * Created by Kevin Kimaru Chege on 8/23/2017.
 */
public class App extends Applet implements ActionListener {
    TextField name, pass;

    @Override
    public void init() {
        Label nameL = new Label("Name: ", Label.RIGHT);
        Label passL = new Label("Password", Label.RIGHT);

        name = new TextField(12);
        pass = new TextField(8);
        pass.setEchoChar('*');

        add(nameL);
        add(name);
        add(passL);
        add(pass);

        name.addActionListener(this);
        pass.addActionListener(this);

        String val = "Java 8 is the latest version of the most\n" +
                "widely-used computer language for Internet programming.\n" +
                "Building on a rich heritage, Java has advanced both\n" +
                "the art and science of computer language design.\n\n" +
                "One of the reasons for Java's ongoing success is its\n" +
                "constant, steady rate of evolution. Java has never stood\n" +
                "still. Instead, Java has consistently adapted to the\n" +
                "rapidly changing landscape of the networked world.\n" +
                "Moreover, Java has often led the way, charting the\n" +
                "course for others to follow.";

        TextArea ta = new TextArea(val, 10, 50);
        add(ta);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("Name: " + name.getText(), 6, 60);
        g.drawString("Selected text in name: " + name.getSelectedText(), 6, 80);
        g.drawString("Password: " + pass.getText(), 6, 100);
    }
}
