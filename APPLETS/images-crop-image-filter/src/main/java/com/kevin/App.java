package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by Kevin Kimaru Chege on 8/24/2017.
 */
public class App extends Applet {
    Image img;
    Image cell[] = new Image[4 * 4];
    int iw, ih;
    int tw, th;
    URL url;

    @Override
    public void init() {
        try {
            Path p = Paths.get("C:\\Users\\Kevin Kimaru Chege\\Downloads\\IMAGES\\");
            url = p.toUri().toURL();
            img = getImage(url, "wow.png");

            MediaTracker t = new MediaTracker(this);
            t.addImage(img, 0);
            t.waitForID(0);
            iw = img.getWidth(null);
            ih = img.getHeight(null);
            tw = iw / 4;
            th = ih / 4;
            CropImageFilter f;
            FilteredImageSource fis;
            t = new MediaTracker(this);
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    f = new CropImageFilter(tw * x, tw * y, tw, th);
                    fis = new FilteredImageSource(img.getSource(), f);
                    int i = y * 4 + x;
                    cell[i] = createImage(fis);
                    t.addImage(cell[i], i);
                }
            }
            t.waitForAll();
            for (int i = 0; i < 32; i++) {
                int si = (int) (Math.random() * 16);
                int di = (int) (Math.random() * 16);
                Image tmp = cell[si];
                cell[si] = cell[di];
                cell[di] = tmp;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    public void paint(Graphics g) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                g.drawImage(cell[y * 4 + x], x * tw, y * th, null);
            }
        }
    }
}
