package viewProfesor;

import Service.ServiceIntrebari;
import Service.ServiceTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMain  {
    private ServiceIntrebari srv;
    private ServiceTest srvt;


    @FXML
    public void initialie() {
        //lset table property et....
    }

    public void setService(ServiceIntrebari srv, ServiceTest srvt) {
        this.srv = srv;
        this.srvt = srvt;

    }

    public void handleProf(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewProf.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            Controller ctr = loader.getController();
            ctr.setService(srv,srvt);
            srv.addObserver(ctr);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void handleStud(ActionEvent actionEvent, String nume) {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("viewStudent.fxml"));
//        try {
//            AnchorPane root = loader.load();
//            Stage dialogStage = new Stage();
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            Scene scene = new Scene(root);
//            dialogStage.setScene(scene);
//            ControllerStud ctr = loader.getController();
//            ctr.setService(srvt, nume);
//            srvt.addObserver(ctr);
//            dialogStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void handleStud() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewStudent.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ControllerStud ctr = loader.getController();
            ctr.setService(srvt);
            srvt.addObserver(ctr);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}