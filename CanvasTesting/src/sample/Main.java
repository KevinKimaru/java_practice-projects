package sample;

import com.sun.javafx.print.PrintHelper;
import com.sun.javafx.print.Units;
import javafx.application.Application;
import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Main extends Application {
    String separator = "===================================";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(300, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

//        gc.setFont(Font.font(null, FontWeight.BOLD, 14));
        gc.fillText(separator, 20, 20, 260);
        gc.fillText("Sleek Restaurant Receipt", 90, 40);
        gc.fillText(separator, 20, 60, 260);

        gc.fillText(separator, 20, 80, 260);
        gc.fillText("Dish", 20, 100, 130);
        gc.fillText("price", 150, 100, 40);
        gc.fillText("Quant", 200, 100, 40);
        gc.fillText("Total", 250, 100, 40);
        gc.fillText(separator, 20, 120, 260);

//        gc.setFont(Font.font(null, 14));
        for(int i = 140; i <= 300; i+=20) {
            gc.fillText("Ugali + sukuma wiki", 20, i, 130);
            gc.fillText("100", 150, i, 40);
            gc.fillText("4", 200, i, 40);
            gc.fillText("400", 250, i, 40);
        }

        gc.fillText(separator, 20, 320, 260);
        gc.fillText("Total", 20, 340, 130);
        gc.fillText("1200", 250, 340, 40);
        gc.fillText(separator, 20, 360, 260);

        gc.fillText(separator, 20, 390, 260);
        gc.fillText("Thanks for shopping with Sleek Restaurant", 40, 410);
        gc.fillText(separator, 20, 430, 260);

        Button button = new Button("Print");
        button.setOnAction(e -> {
//            Printer printer = Printer.getDefaultPrinter();
//            Paper receipt = PrintHelper.createPaper("receipt", 140, 210, Units.MM);
//            PageLayout pageLayout = printer.createPageLayout(receipt, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
//            double scaleX = pageLayout.getPrintableWidth() / canvas.getBoundsInParent().getWidth();
//            double scaleY = pageLayout.getPrintableHeight() / canvas.getBoundsInParent().getHeight();
//            canvas.getTransforms().add(new Scale(scaleX, scaleY));

            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null) {
                boolean success = job.printPage(canvas);
                if (success) {
                    job.endJob();
                }
            }
        });

        Scene root = new Scene(new Pane(canvas, button));
        primaryStage.setScene(root);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
