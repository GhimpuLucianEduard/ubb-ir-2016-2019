package sample.viewPrincipal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Domain.Bug;
import sample.ObserverDP.ListEvent;
import sample.ObserverDP.Observer;
import sample.Service.Service;

import java.util.Collection;

public class ControllerTesteri implements Observer<Bug> {
    private ObservableList<Bug> model = FXCollections.observableArrayList();

    public Service getService() {
        return service;
    }

    private Service service;
    @FXML
    private ListView listBugs;
    @FXML
    private Button buttonAdd;
    @FXML
    private TextField fieldNume;
    @FXML
    private TextField fieldId;

    public void initialize() {
        listBugs.setItems(model);
    }
    public void setService(Service service) {
        this.service=service;
        model.setAll(service.getAllBugs());
    }

    @Override
    public void notifyEvent(ListEvent<Bug> e) {
        this.model.setAll((Collection<? extends Bug>) e.getList());
    }

    public void handleAdd(ActionEvent actionEvent) {
        String nume  = fieldNume.getText();
        int id = Integer.parseInt(fieldId.getText());
        Bug aux = service.addBug(nume, id);
    }
}
