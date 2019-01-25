package Views;

import Models.Comparatorul;
import Models.Student;
import Services.StudentService;
import Utils.MessageAlert;
import Utils.ObserverDP.ListEvent;
import Utils.ObserverDP.Observer;
import Utils.Pair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//implements Observer<Student>
public class StudentController implements Observer<Student> {


    private List<Pair<RadioButton,Predicate<Student>>> listPredicate = new ArrayList<>();
    private StudentService service;

    public ObservableList<Student> getModel() {
        return model;
    }

    private ObservableList<Student> model = FXCollections.observableArrayList();
    private ObservableList<Integer> grupe = FXCollections.observableArrayList();
    private ObservableList<String> profi = FXCollections.observableArrayList();

    @FXML
    private AnchorPane studentView;
    @FXML
    private TextField fieldSearch;
    @FXML
    private TableView studentsTable;

    @FXML
    private TableColumn columnId;
    @FXML
    private TableColumn columnNume;
    @FXML
    private TableColumn columnGrupa;
    @FXML
    private TableColumn columnMail;
    @FXML
    private TableColumn columnProf;
    @FXML
    private ComboBox<Integer> comboGrupa;
    @FXML
    private RadioButton checkGrupa;

    @FXML
    private RadioButton checkProf;
    @FXML
    private ComboBox<String> comboProf;

    @FXML
    private Pagination paginareTabelStudenti;

    private int rowsPerPage =10;
    private int curentIndex=0;
    private ToggleGroup toggleGroup = new ToggleGroup();


    private Node createPage(int pageIndex) {

        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, model.size());
        studentsTable.setItems(FXCollections.observableArrayList(model.subList(fromIndex, toIndex)));
        curentIndex=pageIndex;
        return new BorderPane(studentsTable);
    }

    private void reloadTable(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, model.size());
        studentsTable.setItems(FXCollections.observableArrayList(model.subList(fromIndex, toIndex)));
        curentIndex=pageIndex;
    }

    @FXML
    public void initialize() {

        columnId.setCellValueFactory(new PropertyValueFactory<Student,String>("Id"));
        columnNume.setCellValueFactory(new PropertyValueFactory<Student,String>("Name"));
        columnGrupa.setCellValueFactory(new PropertyValueFactory<Student,String>("Group"));
        columnMail.setCellValueFactory(new PropertyValueFactory<Student,String>("Email"));
        columnProf.setCellValueFactory(new PropertyValueFactory<Student,String>("Prof"));




        //studentsTable.setItems(model);
    }

    public void setService(StudentService service) {
        this.service = service;
        this.model.setAll(service.getAllStudents());
        this.grupe.setAll(service.getAllGrupe());
        this.profi.setAll(service.getAllProf());
        studentsTable.setItems(model);


        comboGrupa.setItems(grupe);
        comboProf.setItems(profi);

        //Pagination pagination = new Pagination((model.size() / rowsPerPage + 1), 0);
        paginareTabelStudenti.setPageCount(model.size()/rowsPerPage +1);
        paginareTabelStudenti.setCurrentPageIndex(0);

        paginareTabelStudenti.setPageFactory(this::createPage);
//        paginareTabelStudenti.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) ->
//                createPage((Integer) newIndex));


    }

    @Override
    public void notifyEvent(ListEvent<Student> e) {
        this.model.setAll((Collection<? extends Student>) e.getList());
        comboGrupa.setItems(grupe);
        comboProf.setItems(profi);
        reloadTable(curentIndex);
        paginareTabelStudenti.setCurrentPageIndex(curentIndex);

    }



    public void handleSearch(KeyEvent keyEvent) {
        FilteredList<Student> filt = new FilteredList<Student>(getModel(), x->true);
        filt.setPredicate(x->x.getName().contains(fieldSearch.getText()));
        studentsTable.setItems(filt);
    }

    public void handleAddStudent(MouseEvent mouseEvent) {
        showStudentDialog(null);
    }

    private void showStudentDialog(Student st) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/StudentDialogView.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            StudentDialogController dialogController = loader.getController();
            dialogController.setService(service,dialogStage,st);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteStudent(MouseEvent mouseEvent) {
        Student st = (Student) studentsTable.getSelectionModel().getSelectedItem();
        if(st==null) {
            MessageAlert.showError(null,"Selecteaza un student din tabel!");
        }
        else {
            this.service.deleteStundet(st.getId());
        }
    }

    public void handleUpdateStudent(MouseEvent mouseEvent) {
        Student aux = (Student) studentsTable.getSelectionModel().getSelectedItem();
        if(aux==null) {
            MessageAlert.showError(null,"Selecteaza un student din tabel!");
        }
        else {
            showStudentDialog(aux);
        }

    }

    public void handleFilt(ActionEvent actionEvent) {
        if(checkGrupa.isSelected() && checkProf.isSelected()) {
            if(comboGrupa.getSelectionModel().getSelectedItem()==null || comboProf.getSelectionModel().getSelectedItem()==null){
                MessageAlert.showError(null,"Selecteaza o grupa din combo box si un prof!");
            }
            else {

                model.setAll(service.getAllStudents().stream().filter(service.filtGrupa(comboGrupa.getSelectionModel().getSelectedItem()).and(service.filtProf(comboProf.getSelectionModel().getSelectedItem()))).sorted(Comparatorul.getInstance()::compareStudentByName).collect(Collectors.toList()));
            }

        }
        else if(checkProf.isSelected() && !checkGrupa.isSelected()) {
            if(comboProf.getSelectionModel().getSelectedItem()==null) {
                MessageAlert.showError(null,"Selecteaza un profesor din combo box!");
            }
            else {
                model.setAll(service.getAllStudents().stream().filter(service.filtProf(comboProf.getSelectionModel().getSelectedItem())).sorted(Comparatorul.getInstance()::compareStudentByName).collect(Collectors.toList()));
            }
        }
        else if(!checkProf.isSelected() && checkGrupa.isSelected()) {
            if(comboGrupa.getSelectionModel().getSelectedItem()==null) {
                MessageAlert.showError(null,"Selecteaza o grupa din combo box!");
            }
            else {
                model.setAll(service.getAllStudents().stream().filter(service.filtGrupa(comboGrupa.getSelectionModel().getSelectedItem())).sorted(Comparatorul.getInstance()::compareStudentByName).collect(Collectors.toList()));
            }
        }
        else {
            model.setAll(service.getAllStudents());
        }

    }


}
