package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    Label response;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Demonstrating TreeView");
        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);
        Scene myScene = new Scene(rootNode, 310, 460);
        primaryStage.setScene(myScene);

        response = new Label("No selection");

        //create tree items starting with the root
        TreeItem<String> tiRoot = new TreeItem<>("Food");

        //Now add subtrees
        //1 Fruit
        TreeItem<String> tiFruit = new TreeItem<>("Fruit");

        //1.0 Apple
        TreeItem<String> tiApple = new TreeItem<>("Apple");

        //add childeren to apple
        tiApple.getChildren().add(new TreeItem<String>("Fuji"));
        tiApple.getChildren().add(new TreeItem<String>("Winesap"));
        tiApple.getChildren().add(new TreeItem<String>("Jonathan"));

        //add children to fruits
        tiFruit.getChildren().add(tiApple);
        tiFruit.getChildren().add(new TreeItem<String>("Oranges"));
        tiFruit.getChildren().add(new TreeItem<String>("Pears"));

        //add child fruit to food
        tiRoot.getChildren().add(tiFruit);

        //2.0 vegetables
        TreeItem<String> tiVegetables = new TreeItem<String>("Vegetables");
        tiVegetables.getChildren().add(new TreeItem<String>("Corn"));
        tiVegetables.getChildren().add(new TreeItem<String>("Peas"));
        tiVegetables.getChildren().add(new TreeItem<String>("Broccoli"));
        tiVegetables.getChildren().add(new TreeItem<String>("Beans"));
        tiRoot.getChildren().add(tiVegetables);

        //3.0 Nuts
        TreeItem<String> tiNuts = new TreeItem<String>("Nuts");
        tiNuts.getChildren().add(new TreeItem<String>("Walnuts"));
        tiNuts.getChildren().add(new TreeItem<String>("Peanuts"));
        tiNuts.getChildren().add(new TreeItem<String>("Pecans"));
        tiRoot.getChildren().add(tiNuts);


        //create a tree view
        TreeView<String> treeView = new TreeView<>(tiRoot);

        //get the treeview selection model
        MultipleSelectionModel<TreeItem<String>> model = treeView.getSelectionModel();

        model.selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue, TreeItem<String> newValue) {
                if (newValue != null) {
                    String path = newValue.getValue();
                    TreeItem<String> tmp = newValue.getParent();

                    while (tmp != null) {
                        path = tmp.getValue() + " -> " + path;
                        tmp = tmp.getParent();
                    }
                    response.setText("Selection is " + newValue.getValue() + "\nComplete path is " + path);
                }
            }
        });

        rootNode.getChildren().addAll(treeView, response);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
