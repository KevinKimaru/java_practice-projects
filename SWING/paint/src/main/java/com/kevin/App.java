package com.kevin;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

// This class extends JPanel. It overrides
// the paintComponent() method so that random
// lines are plotted in the panel.
class PaintPanel extends JPanel {
    Insets ins;//holds the panel's insets

    Random rand;//used to generate random numbers

    //Construct a panel
    PaintPanel() {
        // Put a border around the panel.
        setBorder(BorderFactory.createLineBorder(Color.red, 5));

        rand = new Random();
    }

    // Override the paintComponent() method.
    @Override
    protected void paintComponent(Graphics g) {
        // Always call the superclass method first.
        super.paintComponent(g);

        int x, y, x2, y2;

        //Get the insets.
        ins = getInsets();

        //Get the width and height of the component
        int height = getHeight();
        int width = getWidth();

        // Draw ten lines whose endpoints are randomly generated.
        for (int i = 0; i < 10; i++) {
            // Obtain random coordinates that define
            // the endpoints of each line.
            x = rand.nextInt(width - ins.left);
            y = rand.nextInt(height - ins.bottom);
            x2 = rand.nextInt(width - ins.left);
            y2 = rand.nextInt(width - ins.bottom);

            //draw the line
            g.drawLine(x, y, x2, y2);
        }
    }
}

public class App {
    JLabel jlab;
    PaintPanel pp;

    public App() {
        JFrame jF = new JFrame("Paint demo");
        jF.setSize(200, 150);
        jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create the panel that will be painted
        pp = new PaintPanel();
        // Add the panel to the content pane. Because the default
        // border layout is used, the panel will automatically be
        // sized to fit the center region.
        jF.add(pp);

        jF.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}




















