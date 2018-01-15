package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {
    Label allTargets;
    CheckBox cbWeb;
    CheckBox cbDesktop;
    CheckBox cbMobile;
    String targets = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Controls");
        FlowPane rootNode = new FlowPane(Orientation.VERTICAL, 20, 20);
        rootNode.setAlignment(Pos.TOP_LEFT);
        Scene myScene = new Scene(rootNode, 300, 200);
        primaryStage.setScene(myScene);

        Image image = new Image("patientsicon.png");
        ImageView imageView = new ImageView(image);
        Separator s1 = new Separator();

        ImageView imageView1 = new ImageView("doctorsicon.jpg");
        Label label = new Label("Patient", imageView1);
        label.setContentDisplay(ContentDisplay.CENTER);
        Separator s2 = new Separator();

        Button button = new Button("Click me", new ImageView("doctorsicon.jpg"));
        button.setOnAction(e -> button.setGraphic(imageView));
        Separator s3 = new Separator();

        ToggleButton onOff = new ToggleButton("On/Off");
        onOff.setText("Off");
        onOff.setOnAction(e -> {
            if (onOff.isSelected()) onOff.setText("On");
            else onOff.setText("Off");
        });
        Separator s5 = new Separator();

        ToggleGroup tg = new ToggleGroup();
        Label trans = new Label();
        RadioButton train = new RadioButton("Train");
        RadioButton car = new RadioButton("Car");
        RadioButton lorry = new RadioButton("Lorry");
        Separator s4 = new Separator();
        s4.setPrefWidth(200);
        train.setToggleGroup(tg);
        car.setToggleGroup(tg);
        lorry.setToggleGroup(tg);
        //method 1 to handle events
//        train.setOnAction(e-> trans.setText("Transport selected is train"));
//        car.setOnAction(e-> trans.setText("Transport selected is car"));
//        lorry.setOnAction(e-> trans.setText("Transport selected is lorry"));
//        car.fire();
        //method 2 to handle events
//        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//            @Override
//            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
//                RadioButton rb = (RadioButton) newValue;
//                trans.setText("Means of transportation is: " + rb.getText());
//            }
//        });
//        car.setSelected(true);
        //method 3 to handle events
        Button btnTrans = new Button("Confirm selection");
        btnTrans.setOnAction(e -> {
            RadioButton rb = (RadioButton) tg.getSelectedToggle();
            trans.setText("Means of transportation is: " + rb.getText());
        });

        Label heading = new Label("Select deployment options");
        Label dep = new Label("No deployment selected");
        cbMobile = new CheckBox("Mobile");
        cbDesktop = new CheckBox("Desktop");
        cbWeb = new CheckBox("Web");
        cbMobile.setTooltip(new Tooltip("Deploy to mobile"));
        cbWeb.setTooltip(new Tooltip("Deploy to web"));
        cbDesktop.setTooltip(new Tooltip("Deploy to desktop"));
        allTargets = new Label("<None>");
        Separator s6 = new Separator();
        s6.setPrefWidth(200);
        cbMobile.setAllowIndeterminate(true);
        cbDesktop.setAllowIndeterminate(true);
        cbWeb.setAllowIndeterminate(true);
        cbWeb.setOnAction(e -> {
            if (cbWeb.isIndeterminate()) dep.setText("Web deployment Indeterminate");
            else if (cbWeb.isSelected()) dep.setText("Web deployment selected");
            else dep.setText("Web deployment cleared");
            showAll();
        });
        cbMobile.setOnAction(e -> {
            if (cbMobile.isIndeterminate()) dep.setText("Mobile deployment Indeterminate");
            else if (cbMobile.isSelected()) dep.setText("Mobile deployment selected");
            else dep.setText("Mobile deployment cleared");
            showAll();
        });
        cbDesktop.setOnAction(e -> {
            if (cbDesktop.isIndeterminate()) dep.setText("Desktop deployment Indeterminate");
            else if (cbDesktop.isSelected()) dep.setText("Desktop deployment selected");
            else dep.setText("Desktop deployment cleared");
            showAll();
        });


        Label listLabel = new Label("Select transport type");
        //create an observable list for entries in the list view
        ObservableList<String> transObsList = FXCollections.observableArrayList("Car", "Train", "Tractor", "Bicycle");
        //create the listview
        ListView listView = new ListView(transObsList);
        //set the preffered size and width
        listView.setPrefSize(80, 80);
        //get the listview selection model
        MultipleSelectionModel<String> model = listView.getSelectionModel();
        //optional:  only if you want to use multiple selections
        model.setSelectionMode(SelectionMode.MULTIPLE);
        //use a chenge listener to listen for changes in selsction in the listView
        model.selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //for single selectuon
//                listLabel.setText("Transport selected is: " + newValue);
                //for multiple selection
                ObservableList<String> ol = listView.getSelectionModel().getSelectedItems();
                String selected = "";
                for (int i = 0; i < ol.size(); i++) selected += "\n      " + ol.get(i);
                listLabel.setText("Transports selected: " + selected);
            }
        });
        Separator s7 = new Separator();
        s7.setPrefWidth(200);

        ComboBox<String> comboBox = new ComboBox<>(transObsList);
        //set it editable
        comboBox.setEditable(true);
        Label cbLabel = new Label();
        //setDefaultValue
        comboBox.setValue("Train");
        cbLabel.setText("Selected transport is: " + comboBox.getValue());
        //handle action events
        comboBox.setOnAction(e -> cbLabel.setText("Selected transport is: " + comboBox.getValue()));
        Separator s8 = new Separator();
        s8.setPrefWidth(200);

        Button tfBtn = new Button("Get serach string");
        Label tfLabel = new Label();
        TextField textField = new TextField();
        textField.setPromptText("Get search string");
        textField.setPrefColumnCount(15);
        tfBtn.setOnAction(e -> tfLabel.setText("You want to search: " + textField.getText()));
        textField.setOnAction(e -> tfLabel.setText("You want to search: " + textField.getText()));
        Separator s9 = new Separator();
        s9.setPrefWidth(200);

        Label scrBarLabel = new Label("A ScrollPane streamlines the process of\n" +
                "adding scroll bars to a window whose\n" +
                "contents exceed the window's dimensions.\n" +
                "It also enables a control to fit in a\n" +
                "smaller space than it otherwise would.\n" +
                "As such, it often provides a superior\n" +
                "approach over using individual scroll bars." +
                "adding scroll bars to a window whose\n" +
                "contents exceed the window's dimensions.\n" +
                "It also enables a control to fit in a\n" +
                "smaller space than it otherwise would.\n");
        ScrollPane scrollPane = new ScrollPane(scrBarLabel);
        scrollPane.setPrefViewportHeight(80);
        scrollPane.setPrefViewportWidth(130);
        scrollPane.setPannable(true);
        Button btnReset = new Button("Reset scroll pane");
        btnReset.setOnAction(e -> {
            scrollPane.setHvalue(0);
            scrollPane.setVvalue(0);
        });
        Separator s10 = new Separator();
        s10.setPrefWidth(200);


        rootNode.getChildren().addAll(label, s1, imageView, s2, button, s3, onOff, s5,
                train, car, lorry, trans, btnTrans, s4,
                heading, dep, cbDesktop, cbMobile, cbWeb, allTargets, s6,
                listView, listLabel, s7,
                comboBox, cbLabel, s8,
                textField, tfBtn, tfLabel, s9,
                scrollPane, btnReset, s10);

        primaryStage.show();
    }

    void showAll() {
        targets = "";
        if (cbWeb.isSelected()) targets = "Web ";
        if (cbDesktop.isSelected()) targets += "Desktop ";
        if (cbMobile.isSelected()) targets += "Mobile ";
        if (targets.equals("")) targets = "<None>";
        allTargets.setText(targets);
    }

}
