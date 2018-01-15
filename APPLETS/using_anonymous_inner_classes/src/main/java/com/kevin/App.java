package com.kevin;

import java.applet.Applet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Kevin Kimaru Chege on 8/21/2017.
 */
public class App extends Applet {
    @Override
    public void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showStatus("Mouse clicked");
            }
        });
    }
}
