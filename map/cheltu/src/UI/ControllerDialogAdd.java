package UI;

import Domain.Cheltuiala;
import Domain.Membru;
import Domain.TipCheltuiala;
import Domain.TipMembru;
import Service.ServiceCheltuieli;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerDialogAdd {
    private Stage view;
    private ServiceCheltuieli srv;
    private Membru m;
    ObservableList<String> tipuri = FXCollections.observableArrayList();
    @FXML
    void initialize()
    {

//        comboTip.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
//            @Override
//            public ListCell<String> call(ListView<Tema> param) {
//                return new ListCell<Tema>() {
//                    @Override
//                    public void updateItem(Tema item, boolean empty) {
//                        super.updateItem(item,empty);
//                        if(!empty) {
//                            setText(String.valueOf(item.getId()));
//                            setGraphic(null);
//                        }
//                    }
//                };
//            }
//        });
//
//        comboTip.setButtonCell(
//                new ListCell<Tema>() {
//                    @Override
//                    protected void updateItem(Tema t, boolean bln) {
//                        super.updateItem(t, bln);
//                        if (bln) {
//                            setText("");
//                        } else {
//                            setText(String.valueOf(t.getId()));
//                        }
//                    }
//                });
    }

    @FXML
    ComboBox<String> comboTip;
    @FXML
    TextField fieldSuma;
    @FXML
    TextField fieldDesc;

    public void setService(ServiceCheltuieli srv, Membru m,Stage view) {
        this.srv=srv;
        this.m=m;
        tipuri.add("educatie");
        tipuri.add("excursie");
        tipuri.add("hrana");
        tipuri.add("imbracaminte");
        comboTip.setItems(tipuri);
        this.view=view;
        //educatie,excursie,hrana,imbracaminte
    }

    public void handleAdd(ActionEvent actionEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String date = sdf.format(d);
        Cheltuiala c = new Cheltuiala(TipCheltuiala.valueOf(comboTip.getSelectionModel().getSelectedItem()),Double.parseDouble(fieldSuma.getText()),fieldDesc.getText(),m.getNume(),java.sql.Date.valueOf(date));
        srv.addCheltuiala(c);
        view.close();
    }
}
