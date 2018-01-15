package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;


public class Main extends Application {

    public static class Person {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        public Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String firstName) {
            this.firstName.set(firstName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String lastName) {
            this.lastName.set(lastName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String email) {
            this.email.set(email);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        scrollbar(primaryStage);
        listview(primaryStage);
//        tables(primaryStage);

//        tables2(primaryStage);
    }

    private void tables2(Stage primaryStage) {
        VBox vBox = new VBox();
        final String column1MapKey = "A";
        final String column2MapKey = "B";

        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            Map<String, String> dataRow = new HashMap<>();
            String value1 = "A" + i;
            String value2 = "B" + i;
            dataRow.put(column1MapKey, value1);
            dataRow.put(column2MapKey, value2);
            allData.add(dataRow);
        }

        TableView tableView = new TableView(allData);
        TableColumn<Map, String> column1 = new TableColumn<>("Class A");
        TableColumn<Map, String> column2 = new TableColumn<>("Class B");
        column1.setCellValueFactory(new MapValueFactory<>(column1MapKey));
        column2.setCellValueFactory(new MapValueFactory<>(column2MapKey));
        tableView.setEditable(true);
//        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.getColumns().setAll(column1, column2);

        Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap =
                new Callback<TableColumn<Map, String>, TableCell<Map, String>>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        return new TextFieldTableCell(new StringConverter() {
                            @Override
                            public String toString(Object t) {
                                return t.toString();
                            }

                            @Override
                            public Object fromString(String string) {
                                return string;
                            }
                        });
                    }
                };
        column1.setCellFactory(cellFactoryForMap);
        column2.setCellFactory(cellFactoryForMap);

        vBox.getChildren().add(tableView);
        Scene scene = new Scene(vBox, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void tables(Stage primaryStage) {
        Label label = new Label("Address Book");
        label.setFont(Font.font("Arial", 20));

        final ObservableList<Person> data = FXCollections.observableArrayList(
                new Person("Jacob", "Smith", "jacob.smith@example.com"),
                new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                new Person("Ethan", "Williams", "ethan.williams@example.com"),
                new Person("Emma", "Jones", "emma.jones@example.com"),
                new Person("Michael", "Brown", "michael.brown@example.com"));


        TableView table = new TableView();
        table.setPlaceholder(new Text("No data available for now"));
        table.setEditable(true);
        TableColumn t1 = new TableColumn("First Name");
        t1.setMinWidth(100);
        t1.setSortType(TableColumn.SortType.ASCENDING);
        TableColumn t2 = new TableColumn("Second Name");
        t2.setMinWidth(100);
        t2.setSortable(true);
        TableColumn t3 = new TableColumn("Email");
        TableColumn t31 = new TableColumn("Primary Email");
        t31.setMinWidth(200);
        TableColumn t32 = new TableColumn("Secondary Email");
        t3.getColumns().addAll(t31, t32);
        table.getColumns().addAll(t1, t2, t3);
        t1.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        t2.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        t31.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        table.setItems(data);

        TextField tf1 = new TextField();
        tf1.setPromptText("First Name");
        tf1.setMaxWidth(t1.getPrefWidth());
        TextField tf2 = new TextField();
        tf2.setPromptText("Last Name");
        tf1.setMaxWidth(t1.getPrefWidth());
        TextField tf3 = new TextField();
        tf3.setPromptText("Email");
        tf1.setMaxWidth(t1.getPrefWidth());
        Button btn = new Button("Add");
        HBox hBox = new HBox();
        hBox.setSpacing(3);
        hBox.getChildren().addAll(tf1, tf2, tf3, btn);

        t1.setCellFactory(TextFieldTableCell.forTableColumn());
        t1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow()))
                        .setFirstName(t.getNewValue()
                        );
            }
        });
        t2.setCellFactory(TextFieldTableCell.forTableColumn());
        t2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow()))
                        .setFirstName(t.getNewValue()
                        );
            }
        });
        t3.setCellFactory(TextFieldTableCell.forTableColumn());
        t3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow()))
                        .setFirstName(t.getNewValue()
                        );
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                data.add(new Person(tf1.getText(), tf2.getText(), tf3.getText()));
                tf1.clear();
                tf2.clear();
                tf3.clear();
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, table, hBox);
        Scene scene = new Scene(vBox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void listview(Stage primaryStage) {
        ObservableList names = FXCollections.observableArrayList();
        ObservableList data = FXCollections.observableArrayList();

        names.addAll("Adam", "Alex", "Alfred", "Albert", "Brenda", "Connie", "Derek",
                "Donny", "Lynne", "Myrtle", "Rose", "Rudolph", "Tony", "Trudy", "Williams", "Zach");
        data.addAll("chocolate", "salmon", "gold", "coral", "darkorchid",
                "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
                "blueviolet", "brown");

        ListView listView = new ListView(data);
        listView.setEditable(true);
        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));

        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
//                return new ListCell<String>() {
//                    @Override
//                    protected void updateItem(String item, boolean empty) {
//                        super.updateItem(item, empty);
//                        Rectangle rec = new Rectangle(100, 20);
//                        if (item != null) {
//                            try {
//                                rec.setFill(Color.web(item));
//                                setText(item);
//                                setGraphic(rec);
//                            } catch (Exception e) {
//
//                            }
//                        }
//                    }
//                };

                return new ComboBoxListCell<String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        Rectangle rec = new Rectangle(100, 20);
                        if (item != null) {
                            try {
                                rec.setFill(Color.web(item));
                                setText(item);
                                setGraphic(rec);
                                forListView(names);
                            } catch (Exception e) {

                            }
                        }
                    }
                };
            }


        });

        StackPane root = new StackPane();
        root.getChildren().

                add(listView);

        Scene scene = new Scene(root, 200, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void scrollbar(Stage primaryStage) {
        Group group = new Group();

        Scene scene = new Scene(group, 180, 180);

        ScrollBar sb = new ScrollBar();
        sb.setPrefHeight(180);
        sb.setMax(300);
        sb.setMin(0);
        sb.setOrientation(Orientation.VERTICAL);
        sb.setLayoutX(scene.getWidth() - sb.getWidth());


        VBox vb = new VBox();
        for (int i = 0; i < 50; i++) {
            Button b = new Button(i + "");
            vb.getChildren().add(b);
        }
        vb.setLayoutX(5);

        group.getChildren().addAll(sb, vb);

        sb.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                vb.setLayoutY(-newValue.doubleValue());
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("ScrollBar");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
