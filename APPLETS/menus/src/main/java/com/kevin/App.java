package com.kevin;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

class SampleDialog extends Dialog {

    public SampleDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        setLayout(new FlowLayout());
        setSize(300, 200);

        add(new Label("Press this button"));
        Button b;
        add(b = new Button("Cancel"));
        b.addActionListener(e -> dispose());
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("This is in the dialog box", 10, 70);
    }
}

class MenuFrame extends Frame implements ActionListener, ItemListener {
    String msg = "";
    CheckboxMenuItem debug, test;

    MenuFrame(String title) {
        super(title);

        // create menu bar and add it to frame
        MenuBar mbar = new MenuBar();
        setMenuBar(mbar);

        // create the menu items
        Menu file = new Menu("File");
        MenuItem item1, item2, item3, item4, item5;
        file.add(item1 = new MenuItem("New..."));
        file.add(item2 = new MenuItem("Open..."));
        file.add(item3 = new MenuItem("Close"));
        file.add(item4 = new MenuItem("-"));
        file.add(item5 = new MenuItem("Quit..."));
        mbar.add(file);

        Menu edit = new Menu("Edit");
        MenuItem item6, item7, item8, item9;
        edit.add(item6 = new MenuItem("Cut"));
        edit.add(item7 = new MenuItem("Copy"));
        edit.add(item8 = new MenuItem("Paste"));
        edit.add(item9 = new MenuItem("-"));

        Menu sub = new Menu("Special");
        MenuItem item10, item11, item12;
        sub.add(item10 = new MenuItem("First"));
        sub.add(item11 = new MenuItem("Second"));
        sub.add(item12 = new MenuItem("Third"));
        edit.add(sub);

        // these are checkable menu items
        debug = new CheckboxMenuItem("Debug");
        edit.add(debug);
        test = new CheckboxMenuItem("Testing");
        edit.add(test);

        mbar.add(edit);

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);
        item6.addActionListener(this);
        item7.addActionListener(this);
        item8.addActionListener(this);
        item9.addActionListener(this);
        item10.addActionListener(this);
        item11.addActionListener(this);
        item12.addActionListener(this);
        debug.addItemListener(this);
        test.addItemListener(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        msg = "You selected ";
        String arg = e.getActionCommand();
        if (arg.equals("New...")) {
            msg += "New.";
            SampleDialog d = new SampleDialog(this, "New dialog box", false);
            d.setVisible(true);
        }
        else if (arg.equals("Open...")) {
            msg += "Open.";
            FileDialog fd = new FileDialog(this, "File Dialog Demo");
            fd.setVisible(true);
        }
        else if (arg.equals("Close")) msg += "Close.";
        else if (arg.equals("Quit...")) msg += "Quit.";
        else if (arg.equals("Edit")) msg += "Edit.";
        else if (arg.equals("Cut")) msg += "Cut.";
        else if (arg.equals("Copy")) msg += "Copy.";
        else if (arg.equals("Paste")) msg += "Paste.";
        else if (arg.equals("First")) msg += "First.";
        else if (arg.equals("Second")) msg += "Second.";
        else if (arg.equals("Third")) msg += "Third.";
        else if (arg.equals("Debug")) msg += "Debug.";
        else if (arg.equals("Testing")) msg += "Testing.";

        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg, 10, 200);

        if (debug.getState()) g.drawString("Debug is on.", 10, 220);
        else g.drawString("Debug is off.", 10, 220);

        if (test.getState()) g.drawString("Testing is on.", 10, 240);
        else g.drawString("Testing is off.", 10, 240);
    }
}

public class App extends Applet {
    Frame f;

    @Override
    public void init() {
        f = new MenuFrame("Menu Demo");
        int width = 250;
        int height = 250;
        setSize(new Dimension(width, height));

        f.setSize(width, height);
        f.setVisible(true);

    }

    @Override
    public void start() {
        f.setVisible(true);
    }

    @Override
    public void stop() {
        f.setVisible(false);
    }
}
