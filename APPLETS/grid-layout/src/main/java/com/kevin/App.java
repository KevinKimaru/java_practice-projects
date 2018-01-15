package com.kevin;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by Kevin Kimaru Chege on 8/23/2017.
 */
public class App extends Applet {
    final static int n = 4;
    @Override
    public void init() {
        setLayout(new GridLayout(n, n));

        setFont(new Font("SansSer0if", Font.BOLD, 24));

        for (int i = 0; i< n; i++) {
            for (int j = 0; j<n; j++) {
                int k = i * n + j;
                if (k> 0) add(new Button("" + k));
            }
        }
    }
}
