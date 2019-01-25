package Controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MessageAlert {

    public static void showMessage(Stage owner, Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.initOwner(owner);
        alert.showAndWait();
    }

    public static void showError(Stage owner, String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(owner);
        alert.setTitle("Mesaj de eroare!");
        alert.setContentText(error);
        alert.showAndWait();
    }



}
