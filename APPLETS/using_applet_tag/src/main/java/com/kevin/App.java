package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.net.URL;

/*
<applet code="App" width=300 height=80>
<param name=fontName value=Courier>
<param name=fontSize value=14>
<param name=leading value=2>
<param name=accountEnabled value=true>
</applet>
*/
public class App extends Applet {
    String fontName;
    int fontSize;
    float leading;
    boolean active;

    @Override
    public void start() {
        String param;

        fontName = getParameter("fontName");
        if (fontName == null) fontName = "Not Found";

        try {
            param = getParameter("fontSize");
            if (param != null) leading = Float.valueOf(param);
            else leading = 0;
        } catch (NumberFormatException e) {
            leading = -1;
        }

        param = getParameter("leading");
        try {
            if (param != null) leading = Float.valueOf(param);
            else leading = 0;
        } catch (NumberFormatException e) {
            leading = -1;
        }

        param = getParameter("accountEnabled");
        if (param != null) active = Boolean.valueOf(param);
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("Font name: " + fontName, 0, 10);
        g.drawString("Font size: " + fontSize, 0, 26);
        g.drawString("Leading: " + leading, 0, 42);
        g.drawString("Account Active: " + active, 0, 58);

        String str;
        URL url;

        url = getCodeBase();
        str = "Code base: " + url.toString();
        g.drawString(str, 0, 80);

        url = getDocumentBase();
        str = "Document base: " + url.toString();
        g.drawString(str, 0, 100);
    }
}
