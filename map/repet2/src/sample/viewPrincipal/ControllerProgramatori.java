package sample.viewPrincipal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Domain.Bug;
import sample.ObserverDP.ListEvent;
import sample.ObserverDP.Observer;
import sample.Service.Service;

import java.util.Collection;

public class ControllerProgramatori implements Observer<Bug> {

    private ObservableList<Bug> model = FXCollections.observableArrayList();

    public Service getService() {
        return service;
    }

    private Service service;
    @FXML
    private ListView list;
    @FXML
    private Button buttonD;


    public void initialize() {
        list.setItems(model);
    }
    public void setService(Service service) {
        this.service=service;
        model.setAll(service.getAllBugs());
    }

    @Override
    public void notifyEvent(ListEvent<Bug> e) {
        this.model.setAll((Collection<? extends Bug>) e.getList());
    }


    public void handleDelete(ActionEvent actionEvent) {
        Bug bug = (Bug) list.getSelectionModel().getSelectedItem();
        if(bug==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ceva");
            alert.setContentText("vezi ca nu ai selectat");
            alert.showAndWait();

        }
        else {
            service.deleteBug(bug.getId());
        }
    }
}
