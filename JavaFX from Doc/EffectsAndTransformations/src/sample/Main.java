package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Main extends Application {

    double angle = 0.0;
    double glowVal = 0.0;
    boolean shadow = false;
    double scaleFactor = 1.0;

    //create initial effects and transforms
    Glow glow = new Glow(0.0);
    InnerShadow innerShadow = new InnerShadow(10.0, Color.RED);
    Rotate rotate = new Rotate();
    Scale scale = new Scale(scaleFactor, scaleFactor);

    //create four push buttons
    Button btnRotate = new Button("Rotate");
    Button btnGlow = new Button("Glow");
    Button btnShadow = new Button("Shadow off");
    Button btnScale = new Button("Scale");

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Effects and transforms demo");
        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);
        Scene myScene = new Scene(rootNode, 300, 100);
        primaryStage.setScene(myScene);

        btnGlow.setEffect(glow);
        btnRotate.getTransforms().add(rotate);
        btnScale.getTransforms().add(scale);

        btnRotate.setOnAction(e -> {
            // Each time button is pressed, it is rotated 30 degrees  around its center.
            angle += 30;
            rotate.setAngle(angle);
            rotate.setPivotX(btnRotate.getWidth() / 2);
            rotate.setPivotY(btnRotate.getHeight() / 2);
        });

        btnScale.setOnAction(e -> {
            scaleFactor += 0.1;
            if (scaleFactor > 1.0) scaleFactor = 0.4;

            scale.setX(scaleFactor);
            scale.setY(scaleFactor);
        });

        btnShadow.setOnAction(e -> {
            // Each time button is pressed, its shadow status is changed.
            shadow = !shadow;
            if (shadow) {
                btnShadow.setEffect(innerShadow);
                btnShadow.setText("Shadow on");
            } else {
                btnShadow.setEffect(null);
                btnShadow.setText("Shadow off");
            }
        });

        btnGlow.setOnAction(e -> {
            // Each time button is pressed, its glow value is changed.
            glowVal +=0.1;
            if (glowVal > 1.0) glowVal = 0.0;
            glow.setLevel(glowVal);
        });

        rootNode.getChildren().addAll(btnGlow, btnRotate, btnScale, btnShadow);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
