package sample.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Service.ServiceAngajat;
import sample.Service.ServiceMesaj;
import sample.domain.Angajat;
import sample.domain.tipAngajat;

import java.io.IOException;

public class ControllerMain {
    private ServiceAngajat srv;

    private ObservableList<Angajat> model = FXCollections.observableArrayList();

    public ObservableList<Angajat> getModel() {
        return model;
    }

    @FXML
    public void initialie() {
        //lset table property et....
    }

    public void setService(ServiceAngajat srv) {
        this.srv = srv;

        this.model.setAll(srv.getAll());
        model.forEach(a->{
            if(a.getTip()== tipAngajat.sef) {
                showDialogSef(a);
            }
            else {
                showDialogNormal(a);
            }
        });

    }

    public void showDialogSef(Angajat a) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewSef.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ControllerSef dialogController = loader.getController();
            dialogController.setService(srv,a);
            srv.addObserver(dialogController);
            dialogStage.setTitle(a.getNume()+" "+a.getTip());

            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDialogNormal(Angajat a) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewNormal.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ControllerNormal dialogController = loader.getController();
            dialogController.setService(srv,a);
            srv.addObserver(dialogController);
            dialogStage.setTitle(a.getNume()+" "+a.getTip());
            //dialogController.setLabel(st);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}