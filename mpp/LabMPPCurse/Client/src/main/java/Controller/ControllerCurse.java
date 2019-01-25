package Controller;

import Models.Cursa.Cursa;
import Models.Destinatie.Destinatie;


import Observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


import java.util.Collection;



public class ControllerCurse implements Observer{

    ControllerManage parentController;
    MainServiceClient service;
    private ObservableList<Cursa> model = FXCollections.observableArrayList();
    private ObservableList<Destinatie> modelDestinatii =  FXCollections.observableArrayList();
    private ObservableList<String> ore =  FXCollections.observableArrayList();

    @FXML
    TableView tableCurse;
    @FXML
    TableColumn columnId;
    @FXML
    TableColumn columnDestinatie;
    @FXML
    TableColumn columnData;
    @FXML
    TableColumn columnPlecare;
    @FXML
    TableColumn columnLocuri;
    @FXML
    ComboBox<Destinatie> comboDestinatii;
    @FXML
    ComboBox<String> comboOra;
    @FXML
    DatePicker dataPicker;

    public MainServiceClient getService() {
        return service;
    }

//    @Override
//    public void notifyEvent(ListEvent<Cursa> e) {
//        if(e.getType()== ListEventType.UPDATE) {
//            model.setAll((Collection<? extends Cursa>) e.getList());
//            tableCurse.setItems(model);
//            parentController.handleGetViewRezervare(e.getElement());
//        }
//
//    }

    @FXML
    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<Cursa,String>("Id"));
        columnPlecare.setCellValueFactory(new PropertyValueFactory<Cursa,String>("locatiePlecare"));
        columnDestinatie.setCellValueFactory(new PropertyValueFactory<Cursa,String>("idDestinatie"));
        columnData.setCellValueFactory(new PropertyValueFactory<Cursa,String>("Data"));
        columnLocuri.setCellValueFactory(new PropertyValueFactory<Cursa,String>("nrLocuriDisponibile"));


        tableCurse.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleSelectedRow((Cursa) newSelection);
            }
        });

        comboDestinatii.setCellFactory(new Callback<ListView<Destinatie>, ListCell<Destinatie>>() {
            @Override
            public ListCell<Destinatie> call(ListView<Destinatie> param) {
                return new ListCell<Destinatie>() {
                    @Override
                    public void updateItem(Destinatie item, boolean empty) {
                        super.updateItem(item,empty);
                        if(!empty) {
                            setText(item.getNume());
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        comboDestinatii.setButtonCell(
                new ListCell<Destinatie>() {
                    @Override
                    protected void updateItem(Destinatie t, boolean bln) {
                        super.updateItem(t, bln);
                        if (bln) {
                            setText("");
                        } else {
                            setText(t.getNume());
                        }
                    }
                });

    }

    private void handleSelectedRow(Cursa newSelection) {
        parentController.handleGetViewRezervare(newSelection);
    }

    public void setService(MainServiceClient serviceM, ControllerManage parent) {

        service=serviceM;
        parentController = parent;

        String s = "2018-03-11";

//        service.getServiceCurse().addCursa(new Cursa(133,455, s,"Galati",10));
//        service.getServiceCurse().addCursa(new Cursa(134,455, s,"Braila",4));
//        service.getServiceCurse().addCursa(new Cursa(136,455, s,"Bucuresti", 3));
        //service.getServiceCurse().addCursa(new Cursa(1363,455, s,"Bucuresti", 3));


        for (int i = 0; i <= 23; i++) {
            ore.add(i+":00");
        }
        comboOra.setItems(ore);
        modelDestinatii.setAll((Collection<? extends Destinatie>) service.findAllDestinatii());
        comboDestinatii.setItems(modelDestinatii);
        model.setAll((Collection<? extends Cursa>) service.getAllCurse());
        tableCurse.setItems(model);

    }

    public void handleFindCursa(ActionEvent actionEvent) {
//        if((comboDestinatii.getSelectionModel().getSelectedItem()==null)||(comboOra.getSelectionModel().getSelectedItem()==null)||(dataPicker.getValue()==null)) {
//            MessageAlert.showError(null,"Destinatie, data sau ora nu au fost selectate!");
//        }
//        else {
//
//
//           int id = comboDestinatii.getSelectionModel().getSelectedItem().getId();
//           String s = String.valueOf(dataPicker.getValue());
//           Cursa c = service.getServiceCurse().findCursaByDetails(id, s);
//           if(c==null){
//               MessageAlert.showError(null,"Nu exista o astfel de cursa!");
//           }
//           else {
//               parentController.handleGetViewRezervare(c);
//           }
//
//
//
//        }


    }

    @Override
    public void update(Object arg) {
            model.setAll((Collection<? extends Cursa>) arg);
            tableCurse.setItems(model);
    }
}
