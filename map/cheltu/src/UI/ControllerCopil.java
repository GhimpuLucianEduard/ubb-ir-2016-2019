package UI;

import Domain.Cheltuiala;
import Domain.Membru;
import ObserverDP.ListEvent;
import ObserverDP.Observer;
import Service.ServiceCheltuieli;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCopil implements Observer<Cheltuiala> {

    private Membru membru;
    private ObservableList<Cheltuiala> model = FXCollections.observableArrayList();
    private ServiceCheltuieli srv;
    @FXML
    TableView<Cheltuiala> tableChelt;
    @FXML
    TableColumn cId;
    @FXML
    TableColumn cTip;
    @FXML
    TableColumn cData;
    @FXML
    TableColumn cDesc;
    @FXML
    TableColumn cSuma;
    @FXML
    Label labelSumaDisp;

    @FXML
    void initialize() {
        cId.setCellValueFactory(new PropertyValueFactory<Cheltuiala,String>("Id"));
        cTip.setCellValueFactory(new PropertyValueFactory<Cheltuiala,String>("tip"));
        cSuma.setCellValueFactory(new PropertyValueFactory<Cheltuiala,String>("suma"));
        cData.setCellValueFactory(new PropertyValueFactory<Cheltuiala,String>("data"));
        cDesc.setCellValueFactory(new PropertyValueFactory<Cheltuiala,String>("desc"));
    }

    public void setService(ServiceCheltuieli srv,Membru m) {
        this.srv=srv;
        this.membru=m;
        this.model.setAll(srv.getCheltuialaByUser(m.getNume()));
        tableChelt.setItems(model);
        labelSumaDisp.setText(String.valueOf(srv.returnSumaRamasa(m.getNume())));
    }

    @Override
    public void notifyEvent(ListEvent<Cheltuiala> e) {
        this.model.setAll(srv.getCheltuialaByUser(membru.getNume()));
        tableChelt.setItems(model);
        //labelSumaDisp.setText(String.valueOf(srv.returnSumaRamasa(membru.getNume())));
    }

    public void handleAdd(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addChelt.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ControllerDialogAdd dialogController = loader.getController();
            dialogController.setService(srv,this.membru,dialogStage);
            //srv.addObserver(dialogController);
            //dialogStage.setTitle(m.getNume()+":"+m.getTip());
            //dialogController.setLabel(st);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
