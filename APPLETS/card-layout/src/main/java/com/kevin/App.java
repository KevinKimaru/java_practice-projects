package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Kevin Kimaru Chege on 8/23/2017.
 */
public class App extends Applet {
    Checkbox windowsXP, windows7, windows8, android, solaris, mac;
    Panel osCards;
    CardLayout cardLO;
    Button win, other;

    @Override
    public void init() {
        win = new Button("Windows");
        other = new Button("Other");
        add(win);
        add(other);

        cardLO = new CardLayout();
        osCards = new Panel();
        osCards.setLayout(cardLO);

        windowsXP = new Checkbox("Windows XP", null, true);
        windows7 = new Checkbox("Windows 7", null, false);
        windows8 = new Checkbox("Windows 8", null, false);
        android = new Checkbox("Android");
        solaris = new Checkbox("Solaris");
        mac = new Checkbox("Mac OS");

        // add Windows check boxes to a panel
        Panel winPan = new Panel();
        winPan.add(windowsXP);
        winPan.add(windows7);
        winPan.add(windows8);

        // Add other OS check boxes to a panel
        Panel otherPan = new Panel();
        otherPan.add(android);
        otherPan.add(solaris);
        otherPan.add(mac);

        osCards.add(winPan, "Windows");
        osCards.add(otherPan, "Other");

        add(osCards);

        win.addActionListener(e -> {
            cardLO.show(osCards, "Windows");
        });

        other.addActionListener(e -> {
            cardLO.show(osCards, "Other");
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cardLO.next(osCards);
            }
        });
    }
}
