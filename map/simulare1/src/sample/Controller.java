package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Controller {

    private Service service;
    private ObservableList<Disciplina> model = FXCollections.observableArrayList();
    private ObservableList tipuri = FXCollections.observableArrayList();


    @FXML
    private TableView<Disciplina> tableDiscipline;
    @FXML
    private TableColumn columnCod;
    @FXML
    private TableColumn columnDenumire;
    @FXML
    private TableColumn columnTip;
    @FXML
    private TableColumn columnNr;
    @FXML
    private TextField field1;
    @FXML
    private TextField field2;
    @FXML
    private ComboBox comboTip;
    @FXML
    private CheckBox check1;
    @FXML
    private CheckBox check2;
    @FXML
    private CheckBox check3;


    @FXML
    public void initialize() {
        columnCod.setCellValueFactory(new PropertyValueFactory<Disciplina,String>("Cod"));
        columnDenumire.setCellValueFactory(new PropertyValueFactory<Disciplina,String>("Denumire"));
        columnTip.setCellValueFactory(new PropertyValueFactory<Disciplina,String>("Tip"));
        columnNr.setCellValueFactory(new PropertyValueFactory<Disciplina,String>("NrStudenti"));
        tipuri.addAll(Tip.values());

    }

    public void setService(Service srv) {
        this.service=srv;
        this.model.setAll(srv.getList());
        tableDiscipline.setItems(model);
        comboTip.setItems(tipuri);
    }


    public void handleCheck1(ActionEvent actionEvent) {
        if(check1.isSelected()){
            int nr1=0;
            int nr2=0;
            nr1=Integer.parseInt(field1.getText());
            nr2=Integer.parseInt(field2.getText());
            model.setAll(service.filtByTipAndNr((Tip) comboTip.getValue(),nr1,nr2));
        }
        else {
            model.setAll(service.getList());
        }

    }
    public void handleCheck2(ActionEvent actionEvent) {
        if(check2.isSelected()){
            model.setAll(service.filtByDisciplina(field1.getText()));
        }
        else {
            model.setAll(service.getList());
        }

    }
    public void handleCheck3(ActionEvent actionEvent) {
        if(check3.isSelected()){
            model.setAll(service.filtByNotRip((Tip) comboTip.getValue()));
        }
        else {
            model.setAll(service.getList());
        }

    }
}
