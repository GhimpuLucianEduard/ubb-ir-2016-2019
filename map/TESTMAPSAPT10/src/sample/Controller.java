package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Controller {

    private ObservableList<Articol> model = FXCollections.observableArrayList();
    private ObservableList<String> autori = FXCollections.observableArrayList();
    private Service service;
    @FXML
    private TableView tableArticole;
    @FXML
    private TableColumn columnCod;
    @FXML
    private TableColumn columnTitlu;
    @FXML
    private TableColumn columnAutori;
    @FXML
    private TableColumn columnKeywords;
    @FXML
    private TableColumn columnDomeniu;
    @FXML
    private ComboBox<String> comboAutori;
    @FXML
    private CheckBox check1;
    @FXML
    private CheckBox check2;
    @FXML
    private CheckBox check3;
    @FXML
    private TextField fieldTitlu;


    @FXML
    public void initialize() {
        columnCod.setCellValueFactory(new PropertyValueFactory<Articol,String>("Cod"));
        columnTitlu.setCellValueFactory(new PropertyValueFactory<Articol,String>("Titlu"));
        columnAutori.setCellValueFactory(new PropertyValueFactory<Articol,String>("Autori"));
        columnKeywords.setCellValueFactory(new PropertyValueFactory<Articol,String>("Keywords"));
        columnDomeniu.setCellValueFactory(new PropertyValueFactory<Articol,String>("Domeniu"));


    }

    public void setService(Service srv) {
        this.service = srv;
        this.model.setAll(service.getList());
        tableArticole.setItems(model);
        service.getAllAutori().forEach(x->autori.add(x));
        comboAutori.setItems(autori);
    }



    public Predicate<Articol> predicatAutor(String s) {
        return new Predicate<Articol>() {
            @Override
            public boolean test(Articol articol) {
                return  articol.getAutori().contains(s);
            }
        };
    }



    public void handleFilt1(ActionEvent actionEvent) {
        if(check1.isSelected()){
            model.setAll(service.filtByAutor(comboAutori.getSelectionModel().getSelectedItem()));
        }
        else {
            model.setAll(service.getList());
        }


    }

    public void handleFilt2(ActionEvent actionEvent) {
        if(check3.isSelected()){
            model.setAll(service.filtByTitlu(fieldTitlu.getText()));
        }
        else {
            model.setAll(service.getList());
        }
    }
}
