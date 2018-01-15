package com.kevin;

import javafx.scene.layout.FlowPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NORTHEAST;

public class Main {

    int count = 0;
    int group1Count = 0;
    int group2Count = 0;
    int group3Count = 0;
    int group4Count = 0;
    int group5Count = 0;
    JButton submitButton;
    JTextArea response = new JTextArea();
    String responseText = "";
    JLabel countLabel = new JLabel();
    JLabel group1CountLabel = new JLabel();
    JLabel group2CountLabel = new JLabel();
    JLabel group3CountLabel = new JLabel();
    JLabel group4CountLabel = new JLabel();
    JLabel group5CountLabel = new JLabel();
    String subjects = "";

    Main() {
        response.setBackground(Color.pink);
        JFrame jFrame = new JFrame("Subjects");
        jFrame.setBackground(Color.CYAN);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500, 700);
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        jFrame.setLayout(gridBagLayout);

        JCheckBox english = new JCheckBox("English");
        english.addItemListener(e -> {
            if (english.isSelected()) {
                group1Count++;
                count++;
                validate();
            } else {
                group1Count--;
                count--;
                validate();
            }
        });
        JCheckBox kiswahili = new JCheckBox("Kiswahili");
        kiswahili.setActionCommand("KISWAHILI");
        kiswahili.addItemListener(e -> {
            if (kiswahili.isSelected()) {
                group1Count++;
                count++;
               validate();
            } else {
                group1Count--;
                count--;
                validate();
            }
        });
        JCheckBox mathematics = new JCheckBox("Mathematics");
        mathematics.setActionCommand("MATHEMATICS");
        mathematics.addItemListener(e -> {
            if (mathematics.isSelected()) {
                group1Count++;
                count++;
                validate();
            } else {
                group1Count--;
                count--;
                validate();
            }
        });
        JCheckBox biology = new JCheckBox("Biology");
        biology.setActionCommand("BIOLOGY");
        biology.addItemListener(e -> {
            if (biology.isSelected()) {
                group2Count++;
                count++;
                validate();
            } else {
                group2Count--;
                count--;
                validate();
            }
        });
        JCheckBox physics = new JCheckBox("Physics");
        physics.setActionCommand("PHYSICS");
        physics.addItemListener(e -> {
            if (physics.isSelected()) {
                group2Count++;
                count++;
                validate();
            } else {
                group2Count--;
                count--;
                validate();
            }
        });
        JCheckBox chemistry = new JCheckBox("Chemistry");
        chemistry.setActionCommand("CHEMISTRY");
        chemistry.addItemListener(e -> {
            if (chemistry.isSelected()) {
                group2Count++;
                count++;
                validate();
            } else {
                group2Count--;
                count--;
               validate();
            }
        });
        JCheckBox history = new JCheckBox("History");
        history.setActionCommand("HISTORY");
        history.addItemListener(e -> {
            if (history.isSelected()) {
                group3Count++;
                count++;
                validate();
            } else {
                group3Count--;
                count--;
                validate();
            }
        });
        JCheckBox geography = new JCheckBox("Geography");
        geography.setActionCommand("GEOGRAPHY");
        geography.addItemListener(e -> {
            if (geography.isSelected()) {
                group3Count++;
                count++;
                validate();
            } else {
                group3Count--;
                count--;
                validate();
            }
        });
        JCheckBox cre = new JCheckBox("CRE");
        cre.setActionCommand("CRE");
        cre.addItemListener(e -> {
            if (cre.isSelected()) {
                group3Count++;
                count++;
                validate();
            } else {
                group3Count--;
                count--;
                validate();
            }
        });
        JCheckBox hindu = new JCheckBox("Hindu");
        hindu.setActionCommand("HINDU");
        hindu.addItemListener(e -> {
            if (hindu.isSelected()) {
                group3Count++;
                count++;
                validate();
            } else {
                group3Count--;
                count--;
                validate();
            }
        });
        JCheckBox islam = new JCheckBox("Islam");
        islam.setActionCommand("ISLAM");
        islam.addItemListener(e -> {
            if (islam.isSelected()) {
                group3Count++;
                count++;
                validate();
            } else {
                group3Count--;
                count--;
                validate();
            }
        });
        JCheckBox home_science = new JCheckBox("Home Science");
        home_science.setActionCommand("HOME_SCIENCE");
        home_science.addItemListener(e -> {
            if (home_science.isSelected()) {
                group4Count++;
                count++;
                validate();
            } else {
                group4Count--;
                count--;
                validate();
            }
        });
        JCheckBox art_and_design = new JCheckBox("Art and Design");
        art_and_design.setActionCommand("ART_AND_DESIGN");
        art_and_design.addItemListener(e -> {
            if (art_and_design.isSelected()) {
                group4Count++;
                count++;
                validate();
            } else {
                group4Count--;
                count--;
                validate();
            }
        });
        JCheckBox agriculture = new JCheckBox("Agriculture");
        agriculture.setActionCommand("AGRICULTURE");
        agriculture.addItemListener(e -> {
            if (agriculture.isSelected()) {
                group4Count++;
                count++;
                validate();
            } else {
                group4Count--;
                count--;
                validate();
            }
        });
        JCheckBox aviation = new JCheckBox("Aviation");
        aviation.setActionCommand("AVIATION");
        aviation.addItemListener(e -> {
            if (aviation.isSelected()) {
                group4Count++;
                count++;
                validate();
            } else {
                group4Count--;
                count--;
                validate();
            }
        });
        JCheckBox computer_studies = new JCheckBox("Computer Studies");
        computer_studies.setActionCommand("COMPUTER_STUDIES");
        computer_studies.addItemListener(e -> {
            if (computer_studies.isSelected()) {
                group4Count++;
                count++;
                validate();
            } else {
                group4Count--;
                count--;
                validate();
            }
        });
        JCheckBox metal_work = new JCheckBox("Metal Work");
        metal_work.setActionCommand("METAL_WORK");
        metal_work.addItemListener(e -> {
            if (metal_work.isSelected()) {
                group4Count++;
                count++;
                validate();
            } else {
                group4Count--;
                count--;
                validate();
            }
        });
        JCheckBox wood_work = new JCheckBox("Wood Work");
        wood_work.setActionCommand("WOOD_WORK");
        wood_work.addItemListener(e -> {
            if (wood_work.isSelected()) {
                group4Count++;
                count++;
                validate();
            } else {
                group4Count--;
                count--;
                validate();
            }
        });
        JCheckBox building_and_construction = new JCheckBox("Building and Construction");
        building_and_construction.setActionCommand("BUILDING_AND_CONSTRUCTION");
        building_and_construction.addItemListener(e -> {
            if (building_and_construction.isSelected()) {
                group4Count++;
                count++;
                validate();
            } else {
                group4Count--;
                count--;
                validate();
            }
        });
        JCheckBox power_mechanics = new JCheckBox("Power Mechanics");
        power_mechanics.setActionCommand("POWER_MECHANICS");
        power_mechanics.addItemListener(e -> {
            if (power_mechanics.isSelected()) {
                group4Count++;
                count++;
                validate();
            } else {
                group4Count--;
                count--;
                validate();
            }
        });
        JCheckBox electricity = new JCheckBox("Electricity");
        electricity.setActionCommand("ELECTRICITY");
        electricity.addItemListener(e -> {
            if (electricity.isSelected()) {
                group4Count++;
                count++;
                validate();
            } else {
                group4Count--;
                count--;
                validate();
            }
        });
        JCheckBox french = new JCheckBox("French");
        french.setActionCommand("FRENCH");
        french.addItemListener(e -> {
            if (french.isSelected()) {
                group5Count++;
                count++;
                validate();
            } else {
                group5Count--;
                count--;
                validate();
            }
        });
        JCheckBox german = new JCheckBox("German");
        german.setActionCommand("GERMAN");
        german.addItemListener(e -> {
            if (german.isSelected()) {
                group5Count++;
                count++;
                validate();
            } else {
                group5Count--;
                count--;
                validate();
            }
        });
        JCheckBox arabic = new JCheckBox("Arabic");
        arabic.setActionCommand("ARABIC");
        arabic.addItemListener(e -> {
            if (arabic.isSelected()) {
                group5Count++;
                count++;
                validate();
            } else {
                group5Count--;
                count--;
                validate();
            }
        });
        JCheckBox music = new JCheckBox("Music");
        music.setActionCommand("MUSIC");
        music.addItemListener(e -> {
            if (music.isSelected()) {
                group5Count++;
                count++;
                validate();
            } else {
                group5Count--;
                count--;
                validate();
            }
        });
        JCheckBox business_studies = new JCheckBox("Business Studies");
        business_studies.setActionCommand("BUSINESS_STUDIES");
        business_studies.addItemListener(e -> {
            if (business_studies.isSelected()) {
                group5Count++;
                count++;
                validate();
            } else {
                group5Count--;
                count--;
                validate();
            }
        });


        Font font = new Font("Serif", Font.BOLD, 20);
        JLabel group1 = new JLabel("Group 1");
        group1.setFont(font);
        JLabel group2 = new JLabel("Group 2");
        group2.setFont(font);
        JLabel group3 = new JLabel("Group 3");
        group3.setFont(font);
        JLabel group4 = new JLabel("Group 4");
        group4.setFont(font);
        JLabel group5 = new JLabel("Group 5");
        group5.setFont(font);

        submitButton = new JButton("Submit");
        submitButton.setFont(font);
        submitButton.addActionListener(e -> {
            if (english.isSelected()) {
                subjects += english.getText() + "\n";
            }
            if (kiswahili.isSelected()) {
                subjects += kiswahili.getText() + "\n";
            }
            if (mathematics.isSelected()) {
                subjects += mathematics.getText() + "\n";
            }
            if (biology.isSelected()) {
                subjects += biology.getText() + "\n";
            }
            if (physics.isSelected()) {
                subjects += physics.getText() + "\n";
            }
            if (chemistry.isSelected()) {
                subjects += chemistry.getText() + "\n";
            }
            if (history.isSelected()) {
                subjects += history.getText() + "\n";
            }
            if (geography.isSelected()) {
                subjects += geography.getText() + "\n";
            }
            if (cre.isSelected()) {
                subjects += cre.getText() + "\n";
            }
            if (hindu.isSelected()) {
                subjects += hindu.getText() + "\n";
            }
            if (islam.isSelected()) {
                subjects += islam.getText() + "\n";
            }
            if (home_science.isSelected()) {
                subjects += home_science.getText() + "\n";
            }
            if (art_and_design.isSelected()) {
                subjects += art_and_design.getText() + "\n";
            }
            if (agriculture.isSelected()) {
                subjects += agriculture.getText() + "\n";
            }
            if (aviation.isSelected()) {
                subjects += aviation.getText() + "\n";
            }
            if (computer_studies.isSelected()) {
                subjects += computer_studies.getText() + "\n";
            }
            if (metal_work.isSelected()) {
                subjects += metal_work.getText() + "\n";
            }
            if (wood_work.isSelected()) {
                subjects += wood_work.getText() + "\n";
            }
            if (building_and_construction.isSelected()) {
                subjects += building_and_construction.getText() + "\n";
            }
            if (power_mechanics.isSelected()) {
                subjects += power_mechanics.getText() + "\n";
            }
            if (electricity.isSelected()) {
                subjects += electricity.getText() + "\n";
            }
            if (french.isSelected()) {
                subjects += french.getText() + "\n";
            }
            if (german.isSelected()) {
                subjects += german.getText() + "\n";
            }
            if (arabic.isSelected()) {
                subjects += arabic.getText() + "\n";
            }
            if (music.isSelected()) {
                subjects += music.getText() + "\n";
            }
            if (business_studies.isSelected()) {
                subjects += business_studies.getText() + "\n";
            }

            jFrame.setVisible(false);
            JFrame page2 = new JFrame();
            GridBagLayout gl = new GridBagLayout();
            GridBagConstraints glc = new GridBagConstraints();
            page2.setLayout(gl);
            page2.setSize(500, 700);
            JTextArea subjectsTA = new JTextArea("Your selection has been saved.\nYou chose:\t\t\t\t\n" +
                    subjects + "\n\n\n\n\n\n\n\n\n");
            subjectsTA.setBackground(Color.cyan);
            subjectsTA.setFont(font);
            glc.gridy = 0;
            glc.weighty = 2;
            gl.setConstraints(subjectsTA, glc);
            page2.add(subjectsTA);
            JButton btnBack = new JButton("Back");
            glc.gridy = 1;
            glc.weighty = 1;
            gl.setConstraints(subjectsTA, glc);
            btnBack.addActionListener(ev -> {
                page2.setVisible(false);
                jFrame.setVisible(true);
                subjects = "";
            });
            page2.add(btnBack);
            page2.setVisible(true);

        });

        JPanel group1Panel = new JPanel(new GridLayout(5, 1));
        group1Panel.setSize(80, 80);
        group1Panel.add(group1);
        group1Panel.add(english);
        group1Panel.add(kiswahili);
        group1Panel.add(mathematics);
        group1Panel.add(group1CountLabel);

        JPanel group2Panel = new JPanel(new GridLayout(5, 1));
        group2Panel.setSize(40, 40);
        group2Panel.add(group2);
        group2Panel.add(biology);
        group2Panel.add(physics);
        group2Panel.add(chemistry);
        group2Panel.add(group2CountLabel);

        JPanel group3Panel = new JPanel(new GridLayout(7, 1));
        group3Panel.setSize(40, 40);
        group3Panel.add(group3);
        group3Panel.add(history);
        group3Panel.add(geography);
        group3Panel.add(cre);
        group3Panel.add(hindu);
        group3Panel.add(islam);
        group3Panel.add(group3CountLabel);

        JPanel group4Panel = new JPanel(new GridLayout(11, 1));
        group4Panel.setSize(40, 40);
        group4Panel.add(group4);
        group4Panel.add(home_science);
        group4Panel.add(art_and_design);
        group4Panel.add(agriculture);
        group4Panel.add(computer_studies);
        group4Panel.add(metal_work);
        group4Panel.add(wood_work);
        group4Panel.add(building_and_construction);
        group4Panel.add(power_mechanics);
        group4Panel.add(electricity);
        group4Panel.add(group4CountLabel);

        JPanel group5Panel = new JPanel(new GridLayout(7, 1));
        group5Panel.setSize(40, 40);
        group5Panel.add(group5);
        group5Panel.add(french);
        group5Panel.add(german);
        group5Panel.add(arabic);
        group5Panel.add(music);
        group5Panel.add(business_studies);
        group5Panel.add(group5CountLabel);

        JPanel allGroups = new JPanel(new GridLayout(2, 4, 2, 2));
        allGroups.add(group1Panel);
        allGroups.add(group2Panel);
        allGroups.add(group3Panel);
        allGroups.add(group4Panel);
        allGroups.add(group5Panel);
        gridBagConstraints.anchor = NORTHEAST;
        gridBagConstraints.fill = HORIZONTAL;
        gridBagConstraints.weighty = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagLayout.setConstraints(allGroups, gridBagConstraints);

        JPanel general = new JPanel(new FlowLayout());
        general.add(countLabel);
        general.add(response);
        general.add(submitButton);
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagLayout.setConstraints(general, gridBagConstraints);

        jFrame.add(allGroups);
        jFrame.add(general);
        validate();
        jFrame.setVisible(true);
    }

    private void validate() {
        boolean isValid = true;
        if (count < 7) {
            isValid = false;
            responseText += "You should choose a minimum of 7 subjects.  \n";
        }
        if (count > 9) {
            isValid = false;
            responseText += "You should choose a maximum of 9 subjects.  \n";
        }
        if (group1Count != 3) {
            isValid =false;
            responseText += "All group 1 subjects are compulsory.  \n";
        }
        if (group2Count < 2) {
            isValid =false;
            responseText += "You should choose at least 2 group 2 subjects.  \n";
        }
        if (group3Count < 1) {
            isValid =false;
            responseText += "You should choose at least 1 group 3 subject.  \n";
        }
        if (group4Count > 1) {
            isValid =false;
            responseText += "You should choose only 1 subject from group 4.  \n";
        }
        group1CountLabel.setText(String.valueOf(group1Count));
        group2CountLabel.setText(String.valueOf(group2Count));
        group3CountLabel.setText(String.valueOf(group3Count));
        group4CountLabel.setText(String.valueOf(group4Count));
        group5CountLabel.setText(String.valueOf(group5Count));
        countLabel.setText(String.valueOf(count));
        if (!isValid) {
            submitButton.setEnabled(false);
        } else {
            submitButton.setEnabled(true);
        }
        response.setText(responseText);
        responseText = "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

}
