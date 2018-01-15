package com.kevin;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.effect.Effect;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    private static class Employee {
        private final SimpleStringProperty name;
        private final SimpleStringProperty department;

        private Employee(String name, String department) {
            this.name = new SimpleStringProperty(name);
            this.department = new SimpleStringProperty(department);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getDepartment() {
            return department.get();
        }

        public void setDepartment(String department) {
            this.department.set(department);
        }
    }

    private final class TextFieldTreeCellImpl extends TreeCell<String> {
        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();

        public TextFieldTreeCellImpl() {
            MenuItem addMenuItem = new MenuItem("Add Employee");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction(e -> {
                TreeItem newEmployee = new TreeItem("New Employee");
                getTreeItem().getChildren().add(newEmployee);
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER)
                    commitEdit(textField.getText());
                else if (keyEvent.getCode() == KeyCode.ESCAPE)
                    cancelEdit();
            });
        }

        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null)
                createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null)
                        setContextMenu(addMenu);
                }
            }

        }
    }

    List<Employee> employees = Arrays.<Employee>asList(
            new Employee("Ethan Williams", "Sales Department"),
            new Employee("Emma Jones", "Sales Department"),
            new Employee("Michael Brown", "Sales Department"),
            new Employee("Anna Black", "Sales Department"),
            new Employee("Rodger York", "Sales Department"),
            new Employee("Susan Collins", "Sales Department"),
            new Employee("Mike Graham", "IT Support"),
            new Employee("Judy Mayer", "IT Support"),
            new Employee("Gregory Smith", "IT Support"),
            new Employee("Jacob Smith", "Accounts Department"),
            new Employee("Isabella Johnson", "Accounts Department"));

    TreeItem<String> rootNode = new TreeItem<>("Elec Human Resources");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        treeview(primaryStage);
//        checkBoxTree(primaryStage);
//        combobox(primaryStage);
//        htmleditor(primaryStage);
//        pagination(primaryStage);
        filechoosing(primaryStage);
    }

    private void filechoosing(Stage primaryStage) {
        Desktop desktop = Desktop.getDesktop();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open resource file");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All text files", "*.txt")
        );

        Button openBtn = new Button("Open a picture...");
        Button dirChoose = new Button("Choose dir");
        Button openMultipleBtn = new Button("Open pictures..");
        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(("hello world").getBytes());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        dirChoose.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File choosenDirectory = directoryChooser.showDialog(primaryStage);
            if (choosenDirectory != null) {
                fileChooser.setInitialDirectory(choosenDirectory);
            }
        });
        openBtn.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    desktop.open(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        openMultipleBtn.setOnAction(e -> {
            List<File> files = fileChooser.showOpenMultipleDialog(primaryStage);
            if (files != null) {
                for (File file : files) {
                    try {
                        desktop.open(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(dirChoose, openBtn, openMultipleBtn, saveBtn);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void pagination(Stage primaryStage) {
        final String[] textPages = new String[]{
                "The apple is the pomaceous fruit of the apple tree, species Malus "
                        + "domestica in the rose family (Rosaceae). It is one of the most "
                        + "widely cultivated tree fruits, and the most widely known of "
                        + "the many members of genus Malus that are used by humans. "
                        + "The tree originated in Western Asia, where its wild ancestor, "
                        + "the Alma, is still found today.",
                "The hawthorn is a large genus of shrubs and trees in the rose family,"
                        + "Rosaceae, native to temperate regions of the Northern Hemisphere "
                        + "in Europe, Asia and North America. The name hawthorn was "
                        + "originally applied to the species native to northern Europe, "
                        + "especially the Common Hawthorn C. monogyna, and the unmodified "
                        + "name is often so used in Britain and Ireland.",
                "The ivy is a flowering plant in the grape family (Vitaceae) native to "
                        + " eastern Asia in Japan, Korea, and northern and eastern China. "
                        + "It is a deciduous woody vine growing to 30 m tall or more given "
                        + "suitable support,  attaching itself by means of numerous small "
                        + "branched tendrils tipped with sticky disks.",
                "The quince is the sole member of the genus Cydonia and is native to "
                        + "warm-temperate southwest Asia in the Caucasus region. The "
                        + "immature fruit is green with dense grey-white pubescence, most "
                        + "of which rubs off before maturity in late autumn when the fruit "
                        + "changes color to yellow with hard, strongly perfumed flesh.",
                "Aster (syn. Diplopappus Cass.) is a genus of flowering plants "
                        + "in the family Asteraceae. The genus once contained nearly 600 "
                        + "species in Eurasia and North America, but after morphologic "
                        + "and molecular research on the genus during the 1990s, it was "
                        + "decided that the North American species are better treated in a "
                        + "series of other related genera. After this split there are "
                        + "roughly 180 species within the genus, all but one being confined "
                        + "to Eurasia."
        };
        Pagination pagination = new Pagination(textPages.length, 0);
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                if (param < textPages.length) {
                    VBox box = new VBox(5);
                    TextArea textArea = new TextArea(textPages[param]);
                    textArea.setWrapText(true);
                    box.getChildren().add(textArea);
                    return box;
                } else
                    return null;
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(pagination);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private void htmleditor(Stage primaryStage) {
        HTMLEditor htmlEditor = new HTMLEditor();
//        htmlEditor.setStyle("-fx-font: 12 cambria;"
//                + "-fx-border-color: brown; "
//                + "-fx-border-style: dotted;"
//                + "-fx-border-width: 2;");
        Map.Entry<String, Effect>[] efects = new Map.Entry[]{

        };
        String initialText = "<html><body>" +
                "<h1 style='color: red'>THis is awesome</h1>" +
                "<P><u>Much easier than i thought</u></p>" +
                "</html></body>";
        htmlEditor.setHtmlText(initialText);
        StackPane root = new StackPane();
        root.getChildren().add(htmlEditor);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private void combobox(Stage primaryStage) {
        SepiaTone sp = new SepiaTone();
        sp.setLevel(0.4);
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setEffect(sp);
        comboBox.getItems().addAll("Highest", "High", "Normal", "Low", "Lowest");
        comboBox.setEditable(true);
        comboBox.setVisibleRowCount(3);
        comboBox.setPromptText("Enter Email");
        comboBox.setValue("Normal");
        comboBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    {
                        super.setPrefWidth(100);
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            if (item.contains("High")) {
                                setTextFill(Color.RED);
                            } else if (item.contains("Low")) {
                                setTextFill(Color.GREEN);
                            } else {
                                setTextFill(Color.BLACK);
                            }
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(comboBox);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private void treeview(Stage primaryStage) {
        rootNode.setExpanded(true);
        for (Employee employee : employees) {
            TreeItem<String> empLeaf = new TreeItem<>(employee.getName());
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(employee.getDepartment())) {
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> depNode = new TreeItem<>(employee.getDepartment());
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }

        TreeView<String> tree = new TreeView<>(rootNode);
        tree.setEditable(true);
        tree.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                return new TextFieldTreeCellImpl();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(tree);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private void checkBoxTree(Stage stage) {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("View source files");
        root.setExpanded(true);

        TreeView tree = new TreeView(root);
        tree.setEditable(true);
        tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
        for (int i = 0; i < 8; i++) {
            final CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<String>("Sample" + (i + 1));
            root.getChildren().add(checkBoxTreeItem);
        }

        StackPane root2 = new StackPane();
        root2.getChildren().add(tree);
        stage.setScene(new Scene(root2, 300, 250));
        stage.show();
    }
}
