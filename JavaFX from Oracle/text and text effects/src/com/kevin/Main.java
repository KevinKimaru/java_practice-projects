package com.kevin;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    Stage stage;
    Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        intro(primaryStage);
        primaryStage.show();
        scene = new Scene(new Group(), 800, 750);

        ObservableList content = ((Group) scene.getRoot()).getChildren();
        content.add(perspective());
        content.add(blur());
        content.add(dropShadow());
        content.add(innerShadow());
        content.add(reflection());
        content.add(bindAndBlend());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Text Effects");
    }

    private void intro(Stage primaryStage) {
        FlowPane flowPane = new FlowPane();
        Scene scene = new Scene(flowPane);
        scene.getStylesheets().add("file:resources/css/main.css");

        Text text = new Text(10, 20, "Kevin Kimaru");
        text.setFont(Font.loadFont("file:resources/fonts/dayrom.ttf", 120));
        text.setFontSmoothingType(FontSmoothingType.LCD);
        text.setId("fancyText");

        flowPane.getChildren().add(text);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Node perspective() {
        Group g = new Group();
        PerspectiveTransform pt = new PerspectiveTransform();
        pt.setUlx(10.0f);
        pt.setUly(10.0f);
        pt.setUrx(310.0f);
        pt.setUry(40.0f);
        pt.setLrx(310.0f);
        pt.setLry(60.0f);
        pt.setLlx(10.0f);
        pt.setLly(90.0f);


        g.setEffect(pt);
        g.setCache(true);

        Rectangle r = new Rectangle(10.0f, 10.0f, 280.0f, 80.0f);
        r.setFill(Color.BLUE);

        Text t = new Text("Perspective");
        t.setFill(Color.RED);
        t.setX(20.0f);
        t.setY(65.0f);
        t.setFont(Font.font(null, FontWeight.BOLD, 36));

        g.getChildren().add(r);
        g.getChildren().add(t);

        return g;
    }

    private Node blur(){
        Text t = new Text("Blurry Text");
        t.setX(10.0f);
        t.setY(140.0f);
        t.setCache(true);
        t.setFill(Color.GREEN);
        t.setFont(Font.font(null, FontWeight.BOLD, 36));
//        t.setEffect(new GaussianBlur(10.0));
        t.setEffect(new GaussianBlur());
        return t;
    }

    private Node dropShadow (){
        DropShadow ds = new DropShadow();
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        ds.setOffsetY(3.0f);

        Text t = new Text("Drop Shadow");
        t.setX(10.0f);
        t.setY(270.0f);
        t.setCache(true);
        t.setFill(Color.GREEN);
        t.setFont(Font.font(null, FontWeight.BOLD, 36));
        t.setEffect(ds);

        return t;
    }

    private Node innerShadow() {
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        Text t = new Text("Inner Shadow");
        t.setX(20);
        t.setY(100);
        t.setCache(true);
        t.setFill(Color.GOLD);
        t.setFont(Font.font(null, FontWeight.BOLD, 80));
        t.setEffect(is);

        t.setTranslateX(200);
        t.setTranslateY(200);

        return t;
    }

    private Node reflection() {
        Reflection r = new Reflection();
        r.setFraction(0.7f);

        Text t = new Text("Reflection");
        t.setX(10);
        t.setY(300);
        t.setCache(true);
        t.setFill(Color.GOLD);
        t.setFont(Font.font(null, FontWeight.BOLD, 50));
        t.setEffect(r);

        return t;
    }

    private Node bindAndBlend() {
        Text t = new Text("Binding");
        t.setX(10);
        t.setY(450);
        t.setCache(true);
        t.setFill(Color.GOLD);
        t.setFont(Font.font(null, FontWeight.BOLD, 50));

        TextField tf = new TextField();
        tf.setLayoutX(70);
        tf.setLayoutY(450);

        t.textProperty().bind(tf.textProperty());

        Blend b = new Blend();
        b.setBottomInput(new DropShadow());
        Blend b2 = new Blend();
        b2.setTopInput(new GaussianBlur(10.0));
        b2.setBottomInput(new Reflection());
        b.setTopInput(b2);
        t.setEffect(b);

        Group g = new Group();
        g.getChildren().addAll(t, tf);

        return g;
    }
}
