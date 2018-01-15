package com.kevin;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Kevin Kimaru Chege on 8/27/2017.
 */
public class App extends JApplet {
    @Override
    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    makeGUI();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void makeGUI() {
        // Initialize column headings.
        String[] colHeads = {"Name", "Extension", "ID"};

        //Initialize data
        Object[][] data = {
                {"Gail", "4567", "865"},
                {"Ken", "7566", "555"},
                {"Viviane", "5634", "587"},
                {"Melanie", "7345", "922"},
                {"Anne", "1237", "333"},
                {"John", "5656", "314"},
                {"Matt", "5672", "217"},
                {"Claire", "6741", "444"},
                {"Erwin", "9023", "519"},
                {"Ellen", "1134", "532"},
                {"Jennifer", "5689", "112"},
                {"Ed", "9030", "133"},
                {"Helen", "6751", "145"}
        };

        //Create the table
        JTable jT = new JTable(data, colHeads);

        //Add the table to a scroll pane
        JScrollPane jSP = new JScrollPane(jT);

        add(jSP);
    }
}
