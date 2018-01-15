package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/**
 * Created by Kevin Kimaru Chege on 8/24/2017.
 */
public class App extends Applet implements Runnable{
    MediaTracker tracker;
    int tracked;
    int frame_rate = 5;
    int current_img = 0;
    Thread motor;
    static final int MAXIMAGES = 10;
    Image img[] = new Image[MAXIMAGES];
    String name[] = new String[MAXIMAGES];
    volatile boolean stopFlag;
    URL url;

    @Override
    public void init() {
        tracker = new MediaTracker(this);
        StringTokenizer st = new StringTokenizer(getParameter("img"), "+");

        try {
            Path p = Paths.get("C:\\Users\\Kevin Kimaru Chege\\Downloads\\IMAGES\\");
            url = p.toUri().toURL();
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        while (st.hasMoreTokens() && tracked <= MAXIMAGES) {
            name[tracked] = st.nextToken();
            img[tracked] = getImage(url, name[tracked] + ".jpg");
            tracker.addImage(img[tracked], tracked);
            tracked++;
        }
    }

    @Override
    public void paint(Graphics g) {
        String loaded = "";
        int donecount = 0;

        for (int i = 0; i < tracked; i++) {
            if (tracker.checkID(i, true)) {
                donecount++;
                loaded += name[i] + " ";
            }
        }

        Dimension d = getSize();
        int w = d.width;
        int h = d.height;

        if (donecount == tracked) {
            frame_rate = 1;
            Image i = img[current_img++];
            int iw = i.getWidth(null);
            int ih = i.getHeight(null);
            g.drawImage(i, (w - iw) / 2, (h - ih) / 2, null);
            if (current_img >= tracked) {
                current_img = 0;
            }
        } else {
            int x = w * donecount / tracked;
            g.setColor(Color.black);
            g.fillRect(0, h / 3, x, 16);
            g.setColor(Color.white);
            g.fillRect(x, h / 3, w - x, 16);
            g.setColor(Color.black);
            g.drawString(loaded, 10, h / 2);
        }
    }

    @Override
    public void start() {
        motor = new Thread(this);
        stopFlag = false;
        motor.start();
    }

    @Override
    public void stop() {
        stopFlag = true;
    }

    @Override
    public void run() {
        motor.setPriority(Thread.MIN_PRIORITY);
        while(true) {
            repaint();
            try {
                Thread.sleep(1000/frame_rate);
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            }
            if (stopFlag) return;
        }
    }
}
