package Controller;

import Models.Cursa.Cursa;
import Models.Loc;
import Models.ServiceException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Collection;

public class ControllerRezervare {

    public Cursa idCursa;
    private ObservableList<Loc> model = FXCollections.observableArrayList();

    @FXML
    Label labelCursaCurenta;
    @FXML
    TableView tableLocuri;
    @FXML
    TableColumn columnLoc;
    @FXML
    TableColumn columnNume;
    @FXML
    TableColumn columnPrenume;
    @FXML
    TextField fieldNume;
    @FXML
    TextField fieldPrenume;
    @FXML
    Slider sliderLocuri;
    @FXML
    Label labelLocuri;


    public void setService(MainServiceClient mainService, Cursa idCursa) {
        this.idCursa = idCursa;
        this.mainService = mainService;
        model.setAll((Collection<? extends Loc>) mainService.getLocuri(idCursa));
        labelCursaCurenta.setText(String.valueOf(idCursa));
        tableLocuri.setItems(model);
    }

    private MainServiceClient mainService;

    @FXML
    public void initialize() {
        columnLoc.setCellValueFactory(new PropertyValueFactory<Loc,String>("nrCurent"));
        columnNume.setCellValueFactory(new PropertyValueFactory<Loc,String>("NumeClient"));
        columnPrenume.setCellValueFactory(new PropertyValueFactory<Loc,String>("PrenumeClient"));
        sliderLocuri.setMax(18);
        sliderLocuri.setMin(1);
        //sliderLocuri.setBlockIncrement(1);
        //sliderLocuri.setMinorTickCount(1);
        sliderLocuri.setMajorTickUnit(1);
        sliderLocuri.valueProperty().addListener((obs, oldval, newVal) ->
                sliderLocuri.setValue(Math.round(newVal.doubleValue())));
        sliderLocuri.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                labelLocuri.setText(String.valueOf(new_val));
            }
        });


    }


    public void handleAdd(ActionEvent actionEvent) {

        if(fieldNume.getText().isEmpty()||fieldPrenume.getText().isEmpty()) {
            MessageAlert.showError(null,"Nume sau Prenumele nu poate fi vid!");
        } else {
            mainService.addRezervare(idCursa,fieldNume.getText(),fieldPrenume.getText(), (int) sliderLocuri.getValue());
        }


    }


//    @Override
//    public void notifyEvent(ListEvent<Cursa> e) {
//        idCursa=e.getElement().getId();
//        model.setAll((Collection<? extends Loc>) mainService.getLocuri(idCursa));
//        labelCursaCurenta.setText(String.valueOf(idCursa));
//        tableLocuri.setItems(model);
//    }

    public MainServiceClient getService() {
        return mainService;
    }
}
