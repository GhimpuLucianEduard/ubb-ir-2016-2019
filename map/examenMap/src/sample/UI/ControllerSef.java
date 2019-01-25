package sample.UI;

import ObserverDP.ListEvent;
import ObserverDP.ListEventType;
import ObserverDP.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Service.ServiceAngajat;
import sample.domain.Angajat;
import sample.domain.Mesaj;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class ControllerSef implements Observer<Angajat> {
    private ServiceAngajat srv;
    private ObservableList<Angajat> model = FXCollections.observableArrayList();
    private ObservableList<Mesaj> modelMesaje = FXCollections.observableArrayList();
    private Angajat angajat;
    public ObservableList<Angajat> getModel() {
        return model;
    }
    @FXML
    ListView listAngajati;
    @FXML
    Button buttonRetragere;
    @FXML
    public void initialie() {
        cExp.setCellValueFactory(new PropertyValueFactory<Mesaj,String>("exp"));
        cData.setCellValueFactory(new PropertyValueFactory<Mesaj,String>("data"));
        cMesaj.setCellValueFactory(new PropertyValueFactory<Mesaj,String>("mesaj"));
        tableMesaje.getSortOrder().add(cData);
    }

    public void setService(ServiceAngajat srv, Angajat a) {
        this.srv = srv;
        this.model.setAll(srv.getAllActivi());
        this.angajat=a;
        listAngajati.setItems(model);
        tableMesaje.setItems(modelMesaje);
        buttonRetragere.setDisable(true);

        initialie();

        //setitems to table etc...
    }

    @Override
    public void notifyEvent(ListEvent<Angajat> event) {
        if(event.getType()== ListEventType.RETRAJE)
        {
            this.model.setAll((Collection<? extends Angajat>) event.getList());
            listAngajati.setItems(model);
        }
        else {
            this.modelMesaje.setAll(srv.getAllMesaje());
        }

        if(srv.sefulPoate()) {
            buttonRetragere.setDisable(false);
        }
    }
    @FXML
    Button buttonAdd;
    public void handleRetragere(ActionEvent actionEvent) {
        Angajat aux = new Angajat(angajat.getNume(),angajat.getTip(),false);
        buttonAdd.setDisable(true);
        srv.retrageUnangajat(aux);
    }

    @FXML
    TextField fieldMesaj;
    @FXML
    TableView tableMesaje;
    @FXML
    TableColumn cExp;
    @FXML
    TableColumn cData;
    @FXML
    TableColumn cMesaj;
    public void handleAddToti(ActionEvent actionEvent) {
        String mesaj = fieldMesaj.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Date d = new Date();
        String date = sdf.format(d);
        Mesaj nou = new Mesaj(mesaj,date,angajat.getNume());
        srv.addMesaj(nou);
    }
}