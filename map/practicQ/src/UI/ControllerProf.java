package UI;

import Models.Intrebare;
import ObserverDP.ListEvent;
import ObserverDP.Observer;
import Service.ServiceIntrebari;
import Service.ServiceTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class ControllerProf implements Observer<Intrebare> {
    private ServiceIntrebari srv;
    private ServiceTest srvT;
    private ObservableList<Intrebare> model = FXCollections.observableArrayList();
    public String[] stundeti;

    public ObservableList<Intrebare> getModel() {
        return model;
    }

    @FXML
    private ListView<Intrebare> listIntrebari;

    @FXML
    public void initialie() {

    }

    public void setService(ServiceIntrebari srv, String[] stundeti, ServiceTest srvT) {
        this.srv = srv;
        this.model.setAll(srv.getAllIntrebari());
        this.srvT=srvT;
        listIntrebari.setItems(model);
        this.stundeti=stundeti;

    }

    @Override
    public void notifyEvent(ListEvent<Intrebare> event) {
        this.model.setAll((Collection<? extends Intrebare>) event.getList());

    }

    @Override
    public void notifyEvent(TreeMap<String, Double> rez) {
        return;
    }

    private void showStudentDialog(String st) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewStudent.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ControllerStud dialogController = loader.getController();
            dialogController.setService(srvT);
            srvT.addObserver(dialogController);
            dialogStage.setTitle(st);
            dialogController.setLabel(st);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void handleDeschide(ActionEvent actionEvent) {
        for (String s:stundeti) {
            showStudentDialog(s);
        }
    }

    public void handleSend(ActionEvent actionEvent) {
        Intrebare aux = listIntrebari.getSelectionModel().getSelectedItem();
        if(aux==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(null);
            alert.setTitle("Mesaj de eroare!");
            alert.setContentText("SELECTEAZA O INTREBARE DIN LISTA INAINTE DE A TRIMITE!");
            alert.showAndWait();
        }
        else {
            srv.deleteIntrebare(aux.getId());
            srvT.addToTest(aux);
        }
    }

    public void handleRezultate(ActionEvent actionEvent) {
        srvT.getRaspunsuriFinale();
        srvT.getRezultat();
    }
}