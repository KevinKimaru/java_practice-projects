package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    Label response;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Menus");

        BorderPane rootNode = new BorderPane();

        Scene myScene = new Scene(rootNode, 300, 300);

        primaryStage.setScene(myScene);

        response = new Label("Menu Demo");

        MenuBar mb = new MenuBar();

        //ceate the file menu
        Menu fileMenu = new Menu("_File");
//        fileMenu.setMnemonicParsing(false);
        MenuItem open = new MenuItem("Open");
        MenuItem close = new MenuItem("Close");
        MenuItem save = new MenuItem("Save");
        MenuItem exit = new MenuItem("Exit");
        open.setAccelerator(KeyCombination.keyCombination("shortcut+O"));
        close.setAccelerator(KeyCombination.keyCombination("shortcut+C"));
        save.setAccelerator(KeyCombination.keyCombination("shortcut+S"));
        exit.setAccelerator(KeyCombination.keyCombination("shortcut+E"));
        fileMenu.getItems().addAll(open, close, save, exit);

        //add file menu to the menu bar
        mb.getMenus().add(fileMenu);

        //create the options menu
        Menu optionsMenu = new Menu("Options");

        //create the colors submenu
        Menu colorsMenu = new Menu("Colors");
        CheckMenuItem red = new CheckMenuItem("Red");
        CheckMenuItem green = new CheckMenuItem("Green");
        CheckMenuItem blue = new CheckMenuItem("Blue");
        green.setSelected(true);

        colorsMenu.getItems().addAll(red, green, blue);
        optionsMenu.getItems().add(colorsMenu);

        //create the priority submenu
        Menu priorityMenu = new Menu("Priority");
        RadioMenuItem high = new RadioMenuItem("High");
        RadioMenuItem low = new RadioMenuItem("Low");
        ToggleGroup tg = new ToggleGroup();
        high.setToggleGroup(tg);
        low.setToggleGroup(tg);
        low.setSelected(true);
        priorityMenu.getItems().addAll(high, low);
        optionsMenu.getItems().add(priorityMenu);

        //add a separator
        optionsMenu.getItems().add(new SeparatorMenuItem());

        // Create the Reset menu item.
        MenuItem reset = new MenuItem("Reset");
        optionsMenu.getItems().add(reset);

        // Add Options menu to the menu bar.
        mb.getMenus().add(optionsMenu);

        //create the help menu
        Menu helpMenu = new Menu("Help");
        MenuItem about = new MenuItem("About");
        helpMenu.getItems().add(about);

        mb.getMenus().add(helpMenu);

        // Create one event handler that will handle menu action events.
        EventHandler<ActionEvent> MEHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = ((MenuItem) event.getTarget()).getText();

                if (name.equals("Exit")) Platform.exit();

                response.setText(name + " selected");
            }
        };

        // Set action event handlers for the menu items.

        open.setOnAction(MEHandler);
        close.setOnAction(MEHandler);
        save.setOnAction(MEHandler);
        exit.setOnAction(MEHandler);
        red.setOnAction(MEHandler);
        green.setOnAction(MEHandler);
        blue.setOnAction(MEHandler);
        high.setOnAction(MEHandler);
        low.setOnAction(MEHandler);
        reset.setOnAction(MEHandler);
        about.setOnAction(MEHandler);

        //ContextMenu
        //context menu items
        MenuItem cut = new MenuItem("Cut");
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");

        ContextMenu editMenu = new ContextMenu(cut, copy, paste);

        cut.setOnAction(MEHandler);
        copy.setOnAction(MEHandler);
        paste.setOnAction(MEHandler);

        TextField tf = new TextField();
        tf.setPrefColumnCount(20);
        tf.setContextMenu(editMenu);

        // Create a flow pane that will hold both the response // label and the text field.
        FlowPane fpRoot = new FlowPane(10, 10);
        fpRoot.setAlignment(Pos.CENTER);
        fpRoot.getChildren().addAll(response, tf);

        rootNode.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                editMenu.show(rootNode, event.getScreenX(), event.getScreenY());
            }
        });

        //Toolbar
        Button btnSet = new Button("Set Breakpoint", new ImageView("waaardicon.png"));
        Button btnClear = new Button("Clear Breakpoint", new ImageView("waaardicon.png"));
        Button btnResume = new Button("Resume Execution", new ImageView("waaardicon.png"));

        //turn of the text on the buttons. the text is to be used in the handler
        btnSet.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnClear.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnResume.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        btnSet.setTooltip(new Tooltip("Set a breakpoint"));
        btnClear.setTooltip(new Tooltip("Clear a braekpoint"));
        btnResume.setTooltip(new Tooltip("Resume execution"));

        ToolBar tbDebug = new ToolBar(btnSet, btnClear, btnResume);

        EventHandler<ActionEvent> btnHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                response.setText(((Button)event.getTarget()).getText());
            }
        };

        btnClear.setOnAction(btnHandler);
        btnSet.setOnAction(btnHandler);
        btnResume.setOnAction(btnHandler);

        // Add the menu bar to the top of the border pane and
        // Add the flow pane to the center of the border layout.
        rootNode.setTop(mb);
        rootNode.setCenter(fpRoot);
        rootNode.setBottom(tbDebug);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
