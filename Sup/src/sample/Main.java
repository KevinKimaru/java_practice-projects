package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));

        /*Group root = new Group();
        Text text = new Text("Sup?");
        text.setFont(new Font("Papyrus", 80));
        Label label = new Label("Name:");
        Button button = new Button();
        TextField textField = new TextField();
        GridPane gridPane = new GridPane();
        gridPane.add(label, 0, 0);
        gridPane.add(textField, 1, 0);
        gridPane.setHgap(20);
        gridPane.add(button, 1, 1);
        //gridPane.setGridLinesVisible(true);
        button.setText("Say sup!");
        button.setOnAction(event -> System.out.printf("Sup %s!%n", textField.getText()));
        text.setY(50);
        VBox vBox = new VBox();
        vBox.getChildren().addAll( text, gridPane);
        root.getChildren().add(vBox);*/
        primaryStage.setTitle("Sup");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
