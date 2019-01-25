package Views;

import Models.Nota;
import Models.Student;
import Services.CatalogService;
import Utils.MessageAlert;
import Utils.ObserverDP.ListEvent;
import Utils.ObserverDP.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ViewCatalogController {

    private CatalogService service;
    private ObservableList<String> model = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> comboBoxChoice;
    @FXML
    private Pane paneContent;

    @FXML
    public void initialize() {
        model.addAll("Studenti","Teme");
        comboBoxChoice.setItems(model);
        comboBoxChoice.getSelectionModel().selectFirst();

    }

    public void setService(CatalogService service) {
        this.service = service;
        handleChoiceView();
    }

    public void handleChoiceView() {
        paneContent.getChildren().clear();
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/ViewCatalog"+comboBoxChoice.getValue()+".fxml"));
            newLoadedPane = loader.load();
            ViewCatalogStudentiController catalogController = loader.getController();
            catalogController.setService(service);
            //catalogController.initialize();
            service.getServiceNota().addObserver(catalogController);

        } catch (IOException e) {
            e.printStackTrace();
        }
        paneContent.getChildren().add(newLoadedPane);
    }
}
