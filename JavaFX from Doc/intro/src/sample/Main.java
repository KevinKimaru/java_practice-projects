package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    Label response;
    Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.BLACK, Color.BROWN};
    int colorIdx = 0;
    GraphicsContext gc;

    public static void main(String[] args) {
        System.out.println("Launching JavaFX application.");

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    @Override
    public void init() {
        System.out.println("Inside the init() method.");
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Inside the start() method.");

        // Give the stage a title.
        primaryStage.setTitle("JavaFX Skeleton");

        // Use a FlowPane for the root node. In this case,     // vertical and horizontal gaps of 10.
        FlowPane rootNode = new FlowPane(10, 10);
        // Center the controls in the scene.
        rootNode.setAlignment(Pos.CENTER);

        //create a label
        response = new Label("Push a button");

        //create two buttons
        Button alphaBtn = new Button("Alpha");
        Button betaBtn = new Button("Beta");
        Button changeDraw = new Button("Change Color");
        Canvas c = new Canvas(400, 400);
        drawMe(c);

        changeDraw.setOnAction(e -> {
            gc.setFill(colors[colorIdx]);
            gc.setStroke(colors[colorIdx]);
            drawMe(c);
            colorIdx++;
            if (colorIdx == colors.length) colorIdx = 0;
        });

        //handle action events for the butoon
        alphaBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                response.setText("Alpha was pressed");
            }
        });

        betaBtn.setOnAction(e -> response.setText("Beta was pressed"));

        rootNode.getChildren().addAll(alphaBtn, betaBtn, response, c, changeDraw);

        //create a scene
        Scene myScene = new Scene(rootNode, 500, 500);

        //set the scene on the stage
        primaryStage.setScene(myScene);


        //show the stage and the scene
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.out.println("Inside the stop method.");
    }

    private void drawMe(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();

        gc.strokeLine(0, 0, 200, 200);
        gc.strokeOval(100, 100, 200, 200);
        gc.strokeRect(0, 200, 50, 200);
        gc.fillOval(0, 0, 20, 20);
        gc.fillRect(100, 320, 300, 40);

        gc.setFont(new Font(20));
        gc.fillText("This is drawn on the canvas.", 60, 50);

    }
}
