package com.kevin;

/*  * <applet code=ImageFilterDemo width=400 height=345>  * <param name=img value=Lilies.jpg>  * <param name=filters value="Grayscale+Invert+Contrast+Blur+Sharpen">  * </applet>  */

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class ImageFilterDemo extends Applet implements ActionListener {
    Image img;
    PlugInFilter pif;
    Image fimg;
    Image curImg;
    LoadedImage lim;
    Label lab;
    Button reset;

    URL url;

    public void init() {
        try {
            Path p = Paths.get("C:\\Users\\Kevin Kimaru Chege\\Downloads\\IMAGES\\");
            url = p.toUri().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        Panel p = new Panel();

        add(p, BorderLayout.SOUTH);
        reset = new Button("Reset");
        reset.addActionListener(this);
        p.add(reset);
        StringTokenizer st = new StringTokenizer(getParameter("filters"), "+");

        while (st.hasMoreTokens()) {
            Button b = new Button(st.nextToken());
            b.addActionListener(this);
            p.add(b);
        }

        lab = new Label("");
        add(lab, BorderLayout.NORTH);

        img = getImage(url, getParameter("img"));
        lim = new LoadedImage(img);
        add(lim, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent ae) {
        String a = "";

        try {
            a = ae.getActionCommand();
            if (a.equals("Reset")) {
                lim.set(img);
                lab.setText("Normal");
            } else {
                pif = (PlugInFilter) Class.forName("com.kevin." + a).newInstance();
                fimg = pif.filter(this, img);
                lim.set(fimg);
                lab.setText("Filtered: " + a);
            }
            repaint();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            lab.setText(a + " not found");
            lim.set(img);
            repaint();
        } catch (InstantiationException e) {
            lab.setText("couldn’t new " + a);
        } catch (IllegalAccessException e) {
            lab.setText("no access: " + a);
        }
    }
}

