package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class ControllerLogin {

    private MainServiceClient mainService;
    private Stage mainStage;
    public void setMainService(MainServiceClient mainService, Stage mainStage) {
        this.mainService = mainService;
        this.mainStage = mainStage;
    }

    @FXML
    TextField textUsername;
    @FXML
    PasswordField textPass;

    public void setTextFieldStyleClass(TextField tf, StyleType st) {
        tf.getStyleClass().clear();
        if(st.compareTo(StyleType.NORMAL)==0){
            tf.getStyleClass().add("customTextFieldChecked");
        }
        else if(st.compareTo(StyleType.ERROR)==0){
            tf.getStyleClass().add("customTextFieldError");
        }
    }

    @FXML
    public void initialize() {

    }

    public void handleLogin(ActionEvent actionEvent) {

        if(textUsername.getText().isEmpty() || textPass.getText().isEmpty()) {
            MessageAlert.showError(null, "Va rugam sa completati fieldurile!");
            if(textUsername.getText().isEmpty()) {
                setTextFieldStyleClass(textUsername, StyleType.ERROR);
            } else {
                setTextFieldStyleClass(textUsername, StyleType.NORMAL);
            }
            if (textPass.getText().isEmpty()) {
                setTextFieldStyleClass(textPass, StyleType.ERROR);
            }else {
                setTextFieldStyleClass(textPass, StyleType.NORMAL);
            }
        }
        else {

            try {
                if(mainService.loginHandler(textUsername.getText(),textPass.getText())) {
                    showMainView();
                    mainStage.hide();
                }
                else {
                    MessageAlert.showError(null,"Invalid password!");
                    setTextFieldStyleClass(textUsername, StyleType.ERROR);
                }

            } catch (Exception e) {
                MessageAlert.showError(null,e.getMessage());
                setTextFieldStyleClass(textPass, StyleType.ERROR);
            }

        }


    }

    private void showMainView() {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ViewManage.fxml"));
            root=fxmlLoader.load();
            ControllerManage controllerManage = fxmlLoader.getController();
            controllerManage.setMainService(mainService,this.mainStage);

            Stage stage = new Stage();
            stage.setTitle("title");
            stage.setScene(new Scene(root, 1070, 720));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
