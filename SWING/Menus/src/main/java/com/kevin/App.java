package com.kevin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class App implements ActionListener {
    JLabel jlab;
    String imagePath = "C:\\Users\\Kevin Kimaru Chege\\Desktop\\IMAGES\\";

    DebugAction setAct;
    DebugAction clearAct;
    DebugAction resumeAct;


    App() {
        //create a new jframe container
        JFrame jF = new JFrame("Menu Demo");

        jF.setSize(220, 200);

        jF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jlab = new JLabel("Navigate Menu");
        jF.add(jlab);

        //Load the images
        ImageIcon set = new ImageIcon(imagePath + "waaardicon.png");
        ImageIcon clear = new ImageIcon(imagePath + "hospitalimageicon.jpg");
        ImageIcon resume = new ImageIcon(imagePath + "editicon.jpg");

        setAct = new DebugAction("Set Breakpoint", set, KeyEvent.VK_S, KeyEvent.VK_B, "Set a break point");
        clearAct = new DebugAction("Clear Breakpoint", clear, KeyEvent.VK_C, KeyEvent.VK_L, "Clear a break point.");
        resumeAct = new DebugAction("Resume", resume, KeyEvent.VK_R, KeyEvent.VK_R, "Resume execution after breakpoint.");

        // Initially disable the Clear Breakpoint option.
        clearAct.setEnabled(false);

        //Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        //Create the file menu
        JMenu menuFile = new JMenu("File");
        JMenuItem menuItemFileOpen = new JMenuItem("Open", KeyEvent.VK_O);
        menuItemFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        JMenuItem menuItemFileClose = new JMenuItem("Close", KeyEvent.VK_C);
        menuItemFileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        JMenuItem menuItemFileSave = new JMenuItem("Save", KeyEvent.VK_S);
        menuItemFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        JMenuItem menuItemFileExit = new JMenuItem("Exit", KeyEvent.VK_E);
        menuItemFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        menuFile.add(menuItemFileOpen);
        menuFile.add(menuItemFileClose);
        menuFile.add(menuItemFileSave);
        menuFile.addSeparator();
        menuFile.add(menuItemFileExit);
        menuBar.add(menuFile);

        // Create the Options menu.
        JMenu jmOptions = new JMenu("Options");

        //create the colors submenu
        JMenu jmColors = new JMenu("Colors");
        JCheckBoxMenuItem jmiRed = new JCheckBoxMenuItem("Red");
        JCheckBoxMenuItem jmiGreen = new JCheckBoxMenuItem("Green");
        JCheckBoxMenuItem jmiBlue = new JCheckBoxMenuItem("Blue");
        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);
        jmOptions.add(jmColors);

        //create the priority submenu
        JMenu jmPriority = new JMenu("Priority");
        JRadioButtonMenuItem jmiHigh = new JRadioButtonMenuItem("High", true);
        JRadioButtonMenuItem jmiLow = new JRadioButtonMenuItem("Low");
        jmPriority.add(jmiHigh);
        jmPriority.add(jmiLow);
        jmOptions.add(jmPriority);

        ButtonGroup bg = new ButtonGroup();
        bg.add(jmiHigh);
        bg.add(jmiLow);

        // Create the Reset menu item.
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);

        //create the debug sub-menu
        JMenu jmDebug = new JMenu("Debug");
        JMenuItem jmiSetBP = new JMenuItem(setAct);
        JMenuItem jmiClearBP = new JMenuItem(clearAct);
        JMenuItem jmiResume = new JMenuItem(resumeAct);
        jmDebug.add(jmiSetBP);
        jmDebug.add(jmiClearBP);
        jmDebug.add(jmiResume);
        jmOptions.add(jmDebug);

        // Finally, add the entire options menu to
        // the menu bar
        menuBar.add(jmOptions);

        // Create the Help menu.
        JMenu jmHelp = new JMenu("Help");
        ImageIcon icon = new ImageIcon(imagePath + "ic_launcher.png");
        JMenuItem jmiAbout = new JMenuItem("About", icon);
        jmiAbout.setToolTipText("Info about the menu demo program");
        jmHelp.add(jmiAbout);
        menuBar.add(jmHelp);

        //create an edit popup menu
        JPopupMenu jpu = new JPopupMenu();

        //Create the pop up menuitems
        JMenuItem itemCopy = new JMenuItem("Copy");
        JMenuItem itemCut = new JMenuItem("Cut");
        JMenuItem itemPaste = new JMenuItem("Paste");

        //Add the menu items to the popup menu
        jpu.add(itemCopy);
        jpu.add(itemCut);
        jpu.add(itemPaste);

        // Add action listeners for the menu items.
        menuItemFileOpen.addActionListener(this);
        menuItemFileSave.addActionListener(this);
        menuItemFileClose.addActionListener(this);
        menuItemFileExit.addActionListener(this);
        jmiRed.addActionListener(this);
        jmiGreen.addActionListener(this);
        jmiBlue.addActionListener(this);
        jmiHigh.addActionListener(this);
        jmiLow.addActionListener(this);
        jmiReset.addActionListener(this);
        jmiAbout.addActionListener(this);
        itemCopy.addActionListener(this);
        itemPaste.addActionListener(this);
        itemCut.addActionListener(this);


        //Add a listener for the popup trigger
        jF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) jpu.show(e.getComponent(), e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) jpu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        jF.setJMenuBar(menuBar);

        //Create a debug toolbar
        JToolBar jtb = new JToolBar("Debug");


        // Create the toolbar buttons. //method1
//        JButton jbtnset = new JButton(set);
//        jbtnset.setActionCommand("Set BreakPoint");
//        jbtnset.setToolTipText("Set BreakPoint");
//
//        JButton jbtnClear = new JButton(clear);
//        jbtnClear.setActionCommand("Clear Breakpoint");
//        jbtnClear.setToolTipText("Clear Breakpoint");
//
//        JButton jbtnResume = new JButton(resume);
//        jbtnResume.setActionCommand("Resume");
//        jbtnResume.setToolTipText("Resume");

        // Create the toolbar buttons. //method2 using action
        JButton jbtnset = new JButton(setAct);
        JButton jbtnClear = new JButton(clearAct);
        JButton jbtnResume = new JButton(resumeAct);

        // Add the buttons to the toolbar.
        jtb.add(jbtnset);
        jtb.add(jbtnClear);
        jtb.add(jbtnResume);

        jbtnset.addActionListener(this);
        jbtnClear.addActionListener(this);
        jbtnResume.addActionListener(this);


        //add the buttons to the north position of the content pane
        jF.add(jtb, BorderLayout.NORTH);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the action command from the menu selection.
        String command = e.getActionCommand();
        // If user chooses Exit, then exit the program.
        if (command.equals("Exit")) System.exit(0);
        jlab.setText(command + " Selected");
    }

    class DebugAction extends AbstractAction {
        public DebugAction(String name, Icon icon, int mnem, int accel, String tTip) {
            super(name, icon);

            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel, InputEvent.CTRL_DOWN_MASK));
            putValue(MNEMONIC_KEY, mnem);
            putValue(SHORT_DESCRIPTION, tTip);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            jlab.setText(command + " selected");
            // Toggle the enabled status of the
            // Set and Clear Breakpoint options.
            if (command.equals("Set Breakpoint")) {
                clearAct.setEnabled(true);
                setAct.setEnabled(false);
            } else if (command.equals("Clear Breakpoint")) {
                clearAct.setEnabled(false);
                setAct.setEnabled(true);
            }
        }
    }
}
