package com.kevin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Kevin Kimaru Chege on 8/26/2017.
 */
public class App extends JApplet {
    @Override
    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    new App();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public App(){
        setLayout(new FlowLayout());
        ImageIcon i = new ImageIcon("C:\\Users\\Kevin Kimaru Chege\\Desktop\\PROJECTS\\Kevo\\Resources\\images\\car1.jpg");
        JLabel jL = new JLabel("Car", i, JLabel.CENTER);

        JTextField jTF = new JTextField(15);
        jTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show text when user presses ENTER.
                showStatus(jTF.getText());
            }
        });

        JButton jB = new JButton("BUtton", i);
        jB.addActionListener(e -> showStatus("You clicked me."));

        JToggleButton jTB = new JToggleButton("On/Off");
        jTB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                showStatus( e.getItem().toString());
                if (jTB.isSelected()) showStatus("Selected");else showStatus("Deselected");
            }
        });

        JCheckBox jCB = new JCheckBox("Java");
        JCheckBox jCB2 = new JCheckBox("C++");
        jCB.addItemListener(e -> {if (jCB.isSelected()) showStatus("Java selected");});

        JRadioButton jRB = new JRadioButton("Kevin");
        JRadioButton jRB1 = new JRadioButton("Kimaru");
        JRadioButton jRB2 = new JRadioButton("Chege");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jRB);
        bg.add(jRB1);
        bg.add(jRB2);

        jRB.addActionListener(e -> showStatus("Radio Button " + e.getActionCommand() + " selected"));

        add(jL);
        add(jTF);
        add(jB);
        add(jTB);
        add(jCB);
        add(jCB2);

        add(jRB);
        add(jRB1);
        add(jRB2);
    }
}
