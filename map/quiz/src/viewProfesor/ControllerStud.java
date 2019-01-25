package viewProfesor;

import Models.Intrebare;

import Observer.Observer;
import Observer.ListEvent;
import Service.ServiceIntrebari;
import Service.ServiceTest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.util.Callback;

import java.util.Collection;

public class ControllerStud implements Observer<Intrebare> {
    private String nume;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    private ServiceTest srvt;
    private ObservableList<Intrebare> model = FXCollections.observableArrayList();

    public ObservableList<Intrebare> getModel() {
        return model;
    }

    @FXML
    ListView listTest;
    @FXML
    RadioButton radio1;
    @FXML
    RadioButton radio2;
    @FXML
    RadioButton radio3;
    @FXML
    public void initialie() {
        listTest.setCellFactory(lv -> new ListCell<Intrebare>() {
            @Override
            public void updateItem(Intrebare item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    String text = item.getDesc();
                    setText(text);
                }
            }
        });
        listTest.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Intrebare>() {
            @Override
            public void changed(ObservableValue<? extends Intrebare> observable, Intrebare oldValue, Intrebare newValue) {
                setRadio(newValue);
                //labelMedie.setText(String.valueOf(service.getMedie(newValue)));
            }
        });
    }

    public void setRadio(Intrebare e) {
        radio1.setText(e.getR1());
        radio2.setText(e.getR2());
        radio3.setText(e.getR3());
    }
    public void setService(ServiceTest srv) {
        //this.nume=nume;
        this.srvt = srvt;
        this.model.setAll(srvt.getTest());
        listTest.setItems(model);
        initialie();
    }

    @Override
    public void notifyEvent(ListEvent<Intrebare> event) {
        this.model.setAll((Collection<? extends Intrebare>) event.getList());
        listTest.setItems(model);
    }

    public void handleRaspuns(ActionEvent actionEvent) {
        if(radio1.isSelected()==false && radio2.isSelected() == false && radio3.isSelected()==false) {
            MessageAlert.showError(null,"selecteaza un raspuns!");
        }

    }
}