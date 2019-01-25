package UI;

import Models.Intrebare;
import Models.Raspuns;
import ObserverDP.ListEvent;
import ObserverDP.Observer;
import Service.ServiceTest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class ControllerStud implements Observer<Intrebare> {
    private ServiceTest srvT;
    private ObservableList<Intrebare> model = FXCollections.observableArrayList();
    private ObservableList<Pair> modelRezultate = FXCollections.observableArrayList();
    public String numeStudent;
    public ObservableList<Intrebare> getModel() {
        return model;
    }
    ToggleGroup group = new ToggleGroup();
    @FXML
    Label labelStud;
    @FXML
    RadioButton radio1;
    @FXML
    RadioButton radio2;
    @FXML
    RadioButton radio3;

    public void setLabel(String s) {
        labelStud.setText(s);
    }

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
                radio1.setText(newValue.getR1());
                radio2.setText(newValue.getR2());
                radio3.setText(newValue.getR3());
            }
        });
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);
        radio1.setText("");
        radio2.setText("");
        radio3.setText("");
        group.selectedToggleProperty();

    }

    @FXML
    ListView<Intrebare> listTest;
    @FXML
    ListView<Pair> listRez;

    public void setService(ServiceTest srv) {
        this.srvT = srv;
        this.model.setAll(srvT.getTest());
        listTest.setItems(model);
        initialie();
    }

    @Override
    public void notifyEvent(ListEvent<Intrebare> event) {
        this.model.setAll((Collection<? extends Intrebare>) event.getList());
        //setitems to table or list
    }

    @Override
    public void notifyEvent(TreeMap<String, Double> rez) {
        List<Pair> resultate = new ArrayList<>();
        rez.forEach((x,y)->{
            Pair p = new Pair(y,x);
            resultate.add(p);
        });
        modelRezultate.setAll(resultate);
        listRez.setItems((ObservableList<Pair>) modelRezultate);
    }

    class Pair {
        public double val;
        public String Nume;

        public Pair(double val, String nume) {
            this.val = val;
            Nume = nume;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "val=" + val +
                    ", Nume='" + Nume + '\'' +
                    '}';
        }
    }

    public void handleRaspuns(ActionEvent actionEvent) {
        if((group.getSelectedToggle() ==null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(null);
            alert.setTitle("Mesaj de eroare!");
            alert.setContentText("SELECTEAZA un raspuns!");
            alert.showAndWait();
        }
        else
        {
            if(group.getSelectedToggle()==radio1) {
                Raspuns r = new Raspuns(labelStud.getText(),listTest.getSelectionModel().getSelectedItem().getId(),listTest.getSelectionModel().getSelectedItem().getR1());
                Raspuns R = srvT.addToRaspunsuriNecorectate(r);
                if(R!=null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(null);
                    alert.setTitle("Mesaj de eroare!");
                    alert.setContentText("DEJJA AI RASPUNS LA ASTA");
                    alert.showAndWait();
                }

            }
            else if(group.getSelectedToggle()==radio2) {
                Raspuns r = new Raspuns(labelStud.getText(),listTest.getSelectionModel().getSelectedItem().getId(),listTest.getSelectionModel().getSelectedItem().getR2());
                Raspuns R = srvT.addToRaspunsuriNecorectate(r);
                if(R!=null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(null);
                    alert.setTitle("Mesaj de eroare!");
                    alert.setContentText("DEJJA AI RASPUNS LA ASTA");
                    alert.showAndWait();
                }
            }
            else {
                Raspuns r = new Raspuns(labelStud.getText(),listTest.getSelectionModel().getSelectedItem().getId(),listTest.getSelectionModel().getSelectedItem().getR3());
                Raspuns R = srvT.addToRaspunsuriNecorectate(r);
                if(R!=null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(null);
                    alert.setTitle("Mesaj de eroare!");
                    alert.setContentText("DEJJA AI RASPUNS LA ASTA");
                    alert.showAndWait();
                }
            }



        }
    }
}