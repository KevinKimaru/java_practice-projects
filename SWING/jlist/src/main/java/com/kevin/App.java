package com.kevin;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Kevin Kimaru Chege on 8/27/2017.
 */
public class App extends JApplet {
    JList<String> list;
    JLabel label;
    JScrollPane pane;

    String[] cities = {
            "New York", "Chicago", "Houston",
            "Denver", "Los Angeles", "Seattle",
            "London", "Paris", "New Delhi",
            "Hong Kong", "Tokyo", "Sydney"
    };

    @Override
    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    makeGui();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void makeGui() {
        setLayout(new FlowLayout());

        list = new JList<>(cities);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pane = new JScrollPane(list);

        pane.setPreferredSize(new Dimension(120, 90));

        label = new JLabel("Choose a city");

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //get the index of the changed item
                int index = list.getSelectedIndex();
                if (index != -1) label.setText("You selected " + cities[index]);
                else label.setText("Choose a city.");
            }
        });

        add(pane);
        add(label);
    }
}
