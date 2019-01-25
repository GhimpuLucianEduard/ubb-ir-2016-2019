import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class LoginWindowController{

    ClientService service;

    public void setService(ClientService service) {
        this.service = service;
    }

    @FXML
    TextField usernameTextField;
    @FXML
    Button buttonLogin;

    public LoginWindowController() {
        initialize();
    }

    public void initialize() {

    }

    public void handleButtonClicked() {
        try {
            service.login(usernameTextField.getText());

            try {
                FXMLLoader loader = new FXMLLoader(LoginWindowController.class.getResource("/main_window.fxml"));
                AnchorPane myPane = (AnchorPane) loader.load();
                MainWindowController ctrl = loader.getController();
                ctrl.setService(service);
                service.setMainWindowController(ctrl);
                service.getStock();
                ctrl.initialize();
                Scene myScene = new Scene(myPane);
                Stage stage = new Stage();
                stage.setScene(myScene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (ServerException | RemoteException e) {
            GuiUtils.showAlert(Alert.AlertType.ERROR, "error", "error", e.getMessage());
        }
    }
}
