package Views;

import Models.Nota;
import Services.NotaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewCatalogNoteStudentController {

    private ObservableList<Nota> model = FXCollections.observableArrayList();
    private String idStudent;
    private NotaService service;

    @FXML
    private TableView tableNote;
    @FXML
    private TableColumn columnNrTema;
    @FXML
    private TableColumn columnValoare;
    @FXML
    private TableColumn columnPredare;

    @FXML
    public void initialize() {
        columnNrTema.setCellValueFactory(new PropertyValueFactory<Nota,String>("idTema"));
        columnValoare.setCellValueFactory(new PropertyValueFactory<Nota,String>("Valoare"));
        columnPredare.setCellValueFactory(new PropertyValueFactory<Nota,String>("saptPredare"));

    }

    @FXML
    public void setService(NotaService srv, String idStudent) {
        this.service=srv;
        this.idStudent=idStudent;
        model.setAll(service.getNoteByStudent(this.idStudent));
        tableNote.setItems(model);
    }

}
