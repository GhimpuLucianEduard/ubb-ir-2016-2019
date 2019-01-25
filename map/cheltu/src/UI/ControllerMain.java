package UI;

import Domain.Membru;
import Domain.TipMembru;
import Service.ServiceCheltuieli;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMain {
    private ServiceCheltuieli srv;


    @FXML
    public void initialie() {
        //lset table property et....
    }

    public void setService(ServiceCheltuieli srv) {
        this.srv = srv;
        srv.getMembri().forEach(x->{
            if(x.getTip()== TipMembru.Adult) {
                showDialogAdult(x);
            }
            else {
                showDialogCopil(x);
            }
        });
    }

    public void showDialogAdult(Membru m) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewAdult.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ControllerAdult dialogController = loader.getController();
            dialogController.setService(srv,m);
            srv.addObserver(dialogController);
            dialogStage.setTitle(m.getNume()+":"+m.getTip());
            //dialogController.setLabel(st);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDialogCopil(Membru m) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewCopil.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ControllerCopil dialogController = loader.getController();
            dialogController.setService(srv,m);
            srv.addObserver(dialogController);
            dialogStage.setTitle(m.getNume());
            //dialogController.setLabel(st);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}