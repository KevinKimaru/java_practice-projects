package com.kevin;

import java.applet.Applet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class MyMouseAdapter extends MouseAdapter {
    App app;
    MyMouseAdapter(App app) {
        this.app = app;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        app.showStatus("Mouse clicked.");
    }
}

class MyMouseMotionAdapter extends MouseMotionAdapter {
    App app;
    MyMouseMotionAdapter(App app) {
        this.app = app;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        app.showStatus("Mouse dragged.");
    }
}
public class App extends Applet {
    @Override
    public void init() {
        addMouseListener(new MyMouseAdapter(this));
        addMouseMotionListener(new MyMouseMotionAdapter(this));
    }
}
