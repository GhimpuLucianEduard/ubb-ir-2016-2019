package Views;

import Models.Nota;
import Models.Student;
import Services.CatalogService;
import Utils.MessageAlert;
import Utils.ObserverDP.ListEvent;
import Utils.ObserverDP.Observer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.util.Collection;


public class ViewCatalogStudentiController implements Observer<Nota> {
    private CatalogService service;

    public ObservableList<Nota> getModelNote() {
        return modelNote;
    }

    public ObservableList<Student> getModelStudenti() {
        return modelStudenti;
    }

    private ObservableList<Nota> modelNote = FXCollections.observableArrayList();
    private ObservableList<Student> modelStudenti = FXCollections.observableArrayList();


    @FXML
    private ListView listStudenti;
    @FXML
    private TableView tableNote;
    @FXML
    private TableColumn colIdTema;
    @FXML
    private TableColumn colValoare;
    @FXML
    private TableColumn colSaptPred;
    @FXML
    private Label labelMedie;


    public void initialize() {
        listStudenti.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
            @Override
            public ListCell<Student> call(ListView<Student> param) {
                return new ListCell<Student>() {
                    public void updateItem(Student item, boolean empty) {
                        super.updateItem(item,empty);
                        if(!empty) {
                            setText(String.valueOf(item.getId()));
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        listStudenti.setItems(modelStudenti);
        listStudenti.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
                setItemsTabel(newValue.getId());
                labelMedie.setText(String.valueOf(service.getMedie(newValue)));
            }
        });
        colIdTema.setCellValueFactory(new PropertyValueFactory<Nota,String>("idTema"));
        colValoare.setCellValueFactory(new PropertyValueFactory<Nota,String>("Valoare"));
        colSaptPred.setCellValueFactory(new PropertyValueFactory<Nota,String>("saptPredare"));

    }

    public void handleAddNota(ActionEvent actionEvent) {
        showNotaDialog(null);
    }


    private void showNotaDialog(Nota nt) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/ViewCatalogDialogBox.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ViewCatalogDialogBoxController dialogController = loader.getController();
            dialogController.setService(service,dialogStage,nt);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setItemsTabel(String idStudent) {
        FilteredList<Nota> filt = new FilteredList<Nota>(getModelNote(), x->x.getIdStudent().compareTo(idStudent)==0);
        //filt.setPredicate(x->x.getName().contains(fieldSearch.getText()));
        tableNote.setItems(filt);
    }


    public void setService(CatalogService service) {
        this.service = service;
        modelNote.setAll(service.getServiceNota().getAllNote());
        modelStudenti.setAll(service.getServiceStudent().getAllStudents());
    }


    @Override
    public void notifyEvent(ListEvent<Nota> e) {
        modelNote.setAll((Collection<? extends Nota>) e.getList());
        labelMedie.setText("");
    }

    public void handleDeleteNota(ActionEvent actionEvent) {
       Nota nt = (Nota) tableNote.getSelectionModel().getSelectedItem();
        if(nt==null) {
            MessageAlert.showError(null,"Selecteaza o nota din tabel!");
        }
        else {
            this.service.getServiceNota().removeNota(nt.getId());
        }
    }

    public void handleUpdateNota(ActionEvent actionEvent) {
        Nota nt = (Nota) tableNote.getSelectionModel().getSelectedItem();
        if(nt==null) {
            MessageAlert.showError(null,"Selecteaza o nota din tabel!");
        }
        else {
            showNotaDialog(nt);
        }

    }


    public void showHistory(ActionEvent actionEvent) {

        Student aux = (Student) listStudenti.getSelectionModel().getSelectedItem();
        if(aux!=null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/ViewIstoricNote.fxml"));
            try {
                AnchorPane root = loader.load();
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                ViewIstoricNoteController controller = loader.getController();
                controller.setModel(aux);
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            MessageAlert.showError(null,"Selecteaza un Student din lista!");
        }
    }


    public void showNoteStudent() {
        Student aux = (Student) listStudenti.getSelectionModel().getSelectedItem();
        if(aux!=null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/ViewCatalogNoteStudent.fxml"));
            try {
                AnchorPane root = loader.load();
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                ViewCatalogNoteStudentController controller = loader.getController();
                controller.setService(service.getServiceNota(),aux.getId());
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            MessageAlert.showError(null,"Selecteaza un Student din lista!");
        }
    }
}
