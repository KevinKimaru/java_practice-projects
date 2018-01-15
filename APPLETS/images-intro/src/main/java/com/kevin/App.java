package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Kevin Kimaru Chege on 8/24/2017.
 */
public class App extends Applet {
    Image img;
    Path p = Paths.get("C:\\Users\\Kevin Kimaru Chege\\Downloads\\IMAGES\\1.jpg");
    URL url;

    @Override
    public void init() {
        try {
            url = p.toUri().toURL();
            img = getImage(url);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
        if ((infoflags & ALLBITS) == 0) {
            showStatus("Processing image...");
//            return true;
        } else {
            showStatus("Done image processing");
//            return false;
        }
        return super.imageUpdate(img, infoflags, x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
}

