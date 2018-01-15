package com.kevin;

import java.applet.Applet;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kevin Kimaru Chege on 8/20/2017.
 */
public class App extends Applet {
    @Override
    public void start() {
        AppletContext ac = getAppletContext();

        URL url = getCodeBase();

        try {
            ac.showDocument(new URL(url + "test.html"));
        } catch (MalformedURLException e) {
            showStatus("Url not found");
        }
    }
}
