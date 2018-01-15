package com.kevin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Kevin Kimaru Chege on 8/27/2017.
 */
public class App extends JApplet {
    String[] cars = {"car1", "car2", "car3", "car5"};
    String path = "C:\\Users\\Kevin Kimaru Chege\\Desktop\\PROJECTS\\Kevo\\Resources\\images\\";
    JLabel jLab;

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
        setLayout(new FlowLayout());

        JComboBox<String> jCB = new JComboBox<>(cars);
        add(jCB);

        jCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) jCB.getSelectedItem();
                jLab.setIcon(new ImageIcon(path + s + ".jpg"));
            }
        });

        jLab = new JLabel(new ImageIcon(path + "car1.jpg"));
        add(jLab);
    }
}
