package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * Created by Kevin Kimaru Chege on 12/7/2017.
 */
public class FxmlForm {
    @FXML private Text actionTarget;

    @FXML protected void handleSubmitBundleAction(ActionEvent actionEvent) {
        actionTarget.setText("Sign in button pressed");
    }
}
