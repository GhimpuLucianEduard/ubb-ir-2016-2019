package Views;

import Models.Tema;
import Models.Validators.ValidationException;
import Services.TemaService;
import Utils.CustomStringConverter;
import Utils.MessageAlert;
import Utils.ObserverDP.Observable;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.*;

public class ViewTemaDialogPrelungireController {
    private Tema model;
    private Stage view;
    private TemaService service;
    private ObservableList<Integer> date = FXCollections.observableArrayList();


    @FXML
    private ComboBox<Integer> comboBoxSaptamana;
    @FXML
    private Label labelSaptCurenta;

    public void setService(TemaService service, Stage view, Tema tm) {
        this.service=service;
        this.model=tm;
        this.view=view;
        labelSaptCurenta.setText(String.valueOf(service.getSaptCurenta()));

        date.addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        comboBoxSaptamana.setItems(date);
        comboBoxSaptamana.setValue(model.getDeadline());

    }

    public void handleModificare(ActionEvent actionEvent) {
        try {
            service.prelungire(model.getId(),comboBoxSaptamana.getValue());
            MessageAlert.showMessage(null,Alert.AlertType.INFORMATION,"Deadline Prelungit","Deadline-ul a fost prelungit cu succes!");
        } catch (ValidationException e) {
            MessageAlert.showError(null,e.getMessage());
        }
        view.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        view.close();
    }
}
