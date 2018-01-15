package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane border = new BorderPane();

        HBox hBox = addHBox();

        border.setTop(hBox);
        border.setLeft(addVBox());

        addStackPane(hBox);

        border.setCenter(addAnchorPane(addGridPane()));

        border.setRight(addFlowPane());
        Scene scene = new Scene(border);
        scene.getStylesheets().add("main.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Layout sample");
        primaryStage.show();
    }

    public HBox addHBox() {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("hbox");
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);

        hBox.getChildren().addAll(buttonCurrent, buttonProjected);
        return hBox;
    }

    public VBox addVBox() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("vbox");
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(8);

        Text title = new Text("Data");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vBox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[]{
                new Hyperlink("Sales"),
                new Hyperlink("Marketing"),
                new Hyperlink("Distribution"),
                new Hyperlink("Cost")
        };
        for (int i = 0; i < options.length; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vBox.getChildren().add(options[i]);
        }

        return vBox;
    }

    public void addStackPane(HBox hb) {
        StackPane stackPane = new StackPane();
        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop[]{
                        new Stop(0, Color.web("#4977A3")),
                        new Stop(0.5, Color.web("#B0C6DA")),
                        new Stop(1, Color.web("#9CB6CF"))
                }));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);

        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0"));

        stackPane.getChildren().addAll(helpIcon, helpText);
        stackPane.setAlignment(Pos.CENTER_RIGHT);//right justify nodes in stack
        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));//center the helpTrxt

        hb.getChildren().add(stackPane);
        HBox.setHgrow(stackPane, Priority.ALWAYS);
    }

    public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid");
        grid.getStyleClass().add("pane");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        //Category in column 2 row 1
        Text category = new Text("Sales:");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(category, 1, 0);

        //Title in column 3 row 1
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(chartTitle, 2, 0);

        //subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Goods and services");
        grid.add(chartSubtitle, 2, 1, 1, 1);

        //house icon in columns 1 rows 1-2
        ImageView imageHouse = new ImageView(new Image(Main.class.getResourceAsStream("graphics/house.png")));
        grid.add(imageHouse, 0, 0, 1, 2);

        //left label in column 1(bottom) row 3
        Text goodsPercent = new Text("Goods\n80%");
        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
        grid.add(goodsPercent, 0, 2);

        //chart in columns 2-3 row 3
        ImageView imageChart = new ImageView(new Image(Main.class.getResourceAsStream("graphics/piechart.png")));
        grid.add(imageChart, 1, 2, 2, 1);

        //right label in column 4(top) row 3
        Text servicePercent = new Text("Services\n20%");
        GridPane.setValignment(servicePercent, VPos.TOP);
        grid.add(servicePercent, 3, 2);

        return grid;
    }

    public FlowPane addFlowPane() {
        FlowPane flowPane = new FlowPane();
        flowPane.getStyleClass().add("flow-tile");
        flowPane.setPadding(new Insets(5, 0, 5, 0));
        flowPane.setVgap(4);
        flowPane.setHgap(4);
        flowPane.setPrefWrapLength(170);//preferred width allows for two columns
        flowPane.setStyle("-fx-background-color:  DAE6F3");

        ImageView pages[] = new ImageView[8];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = new ImageView(new Image(Main.class.getResourceAsStream("graphics/chart_" + (i + 1) + ".png")));
            flowPane.getChildren().add(pages[i]);
        }

        return flowPane;
    }

    private TilePane addTilePane() {

        TilePane tile = new TilePane();
        tile.getStyleClass().add("flow-tile");
        tile.setPadding(new Insets(5, 0, 5, 0));
        tile.setVgap(4);
        tile.setHgap(4);
        tile.setPrefColumns(2);
        tile.setStyle("-fx-background-color: DAE6F3;");

        ImageView pages[] = new ImageView[8];
        for (int i=0; i<8; i++) {
            pages[i] = new ImageView(
                    new Image(Main.class.getResourceAsStream(
                            "graphics/chart_"+(i+1)+".png")));
            tile.getChildren().add(pages[i]);
        }

        return tile;
    }

    private AnchorPane addAnchorPane(GridPane grid) {
//        VBox fonts = new VBox();
//        ScrollPane sp = new ScrollPane();
//        sp.setContent(fonts);
//        for (String s: Font.getFontNames()) {
//            Label l = new Label(s);
//            l.setFont(Font.font(s, 24));
//            fonts.getChildren().add(l);
//        }

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("pane");
        anchorPane.getStyleClass().add("pane");

        Button btnSave = new Button("Save");
        btnSave.setId("button-custom");
        Button btnCancel = new Button("Cancel");
        btnCancel.setId("button-custom");

        HBox hb = new HBox();
        hb.getStyleClass().add("hbox");
        hb.setId("hbox-custom");
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(btnSave, btnCancel);

        anchorPane.getChildren().addAll(grid, hb);
        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);
        AnchorPane.setTopAnchor(grid, 10.0);

        return anchorPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
