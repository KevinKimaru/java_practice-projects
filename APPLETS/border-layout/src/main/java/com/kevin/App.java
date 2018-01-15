package com.kevin;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by Kevin Kimaru Chege on 8/23/2017.
 */
public class App extends Applet {
    @Override
    public void init() {
        setBackground(Color.CYAN);
        setLayout(new BorderLayout());

        add(new Button("This is across the top."), BorderLayout.NORTH);
        add(new Label("The footer message might go here."), BorderLayout.SOUTH);
        add(new Button("Right"), BorderLayout.EAST);
        add(new Button("Left"), BorderLayout.WEST);

        String msg = "The reasonable man adapts " +
                "himself to the world;\n" +
                "the unreasonable one persists in " +
                "trying to adapt the world to himself.\n" +
                "Therefore all progress depends " + "on the unreasonable man.\n\n" +
                "        - George Bernard Shaw\n\n";
        add(new TextArea(msg), BorderLayout.CENTER);

    }

    @Override
    public Insets getInsets() {
        return new Insets(10, 10, 10, 10);
    }
}
