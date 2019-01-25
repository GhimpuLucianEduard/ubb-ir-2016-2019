package Views;

import Models.Tema;
import Services.TemaService;
import Utils.MessageAlert;
import Utils.ObserverDP.ListEvent;
import Utils.ObserverDP.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Collection;

public class ViewTemeController implements Observer<Tema> {

    private TemaService service;

    public ObservableList<Tema> getModel() {
        return model;
    }

    private ObservableList<Tema> model = FXCollections.observableArrayList();

    @FXML
    private ComboBox comboBoxId;
    @FXML
    private Label labelDeadline;
    @FXML
    private Label labelDesc;



    @FXML
    public void initialize() {

        comboBoxId.setCellFactory(new Callback<ListView<Tema>, ListCell<Tema>>() {
            @Override
            public ListCell<Tema> call(ListView<Tema> param) {
                return new ListCell<Tema>() {
                  @Override
                  public void updateItem(Tema item, boolean empty) {
                      super.updateItem(item,empty);
                      if(!empty) {
                          setText(String.valueOf(item.getId()));
                          setGraphic(null);
                      }
                  }
                };
            }
        });

        comboBoxId.setButtonCell(
                new ListCell<Tema>() {
                    @Override
                    protected void updateItem(Tema t, boolean bln) {
                        super.updateItem(t, bln);
                        if (bln) {
                            setText("");
                        } else {
                            setText(String.valueOf(t.getId()));
                        }
                    }
                });




        comboBoxId.setItems(model);
        //comboBoxId.getSelectionModel().select(1);
    }

    public void setService(TemaService service) {
        this.service = service;
        this.model.setAll(service.getAllTeme());
    }


    @Override
    public void notifyEvent(ListEvent<Tema> e) {
        this.model.setAll((Collection<? extends Tema>) e.getList());
    }

    public void handleSelectedItem(ActionEvent actionEvent) {

        Tema aux = (Tema) comboBoxId.getSelectionModel().getSelectedItem();
        if(aux!=null) {
            setLabels(String.valueOf(aux.getDeadline()),aux.getInfo());
        }
        else {
            setLabels("","");
        }

    }

    public void handleDeleteTema(ActionEvent actionEvent) {
        Tema aux = (Tema) comboBoxId.getSelectionModel().getSelectedItem();
        if(aux==null) {
            MessageAlert.showError(null,"Selecteaza o tema!");
        }
        else {
            service.removeTema(aux.getId());
            setLabels("","");
        }

    }

    private void setLabels(String deadline, String descriere) {
        labelDeadline.setText(deadline);
        labelDesc.setText(descriere);
    }

    public void handleAddTema(ActionEvent actionEvent) {
        showTemaDialog(null);
    }

    public void handleUpdateTema(ActionEvent actionEvent) {
        Tema aux = getSelectionModel();
        if(aux==null) {
            MessageAlert.showError(null,"Selecteaza o tema din ComboBox!");
        }
        else {
            showTemaDialog(aux);
        }
    }

    private void showTemaDialog(Tema tm) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/ViewTemeDialog.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ViewTemeDialogController dialogController = loader.getController();
            dialogController.setService(service,dialogStage,tm);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        comboBoxId.getSelectionModel().clearSelection();
    }

    public Tema getSelectionModel() {
        return (Tema) comboBoxId.getSelectionModel().getSelectedItem();
    }


    public void handlePrelungire(ActionEvent actionEvent) {
        Tema aux = getSelectionModel();
        if(aux==null) {
            MessageAlert.showError(null,"Selecteaza o tema din ComboBox!");
        }
        else {
            showTemaDialogPrelungire(aux);
        }
    }

    private void showTemaDialogPrelungire(Tema aux) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/ViewTemaDialogPrelungire.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ViewTemaDialogPrelungireController dialogController = loader.getController();
            dialogController.setService(service,dialogStage,aux);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        comboBoxId.getSelectionModel().clearSelection();
    }
}
