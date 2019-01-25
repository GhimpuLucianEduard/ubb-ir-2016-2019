package Views;

import Models.Student;
import Models.Validators.ValidationException;
import Services.StudentService;

import Utils.ObserverDP.ListEvent;
import Utils.ObserverDP.Observer;
import Views.StudentView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentController implements Observer<Student> {

    private StudentService service;

    public ObservableList getModel() {
        return model;
    }

    private ObservableList model;

    public void setView(StudentView view) {
        this.view = view;
    }

    private StudentView view;

    public StudentController(StudentService service) {
        this.service = service;
        model = FXCollections.observableArrayList(service.getAllStudents());
    }

    @Override
    public void notifyEvent(ListEvent<Student> e) {
        model.setAll(StreamSupport.stream(e.getList().spliterator(),false).collect(Collectors.toList()));
    }


    public void handleAdd(ActionEvent actionEvent) {
        try {

            if(service.addStudent(getStudentFromFields()).isPresent()) {
                showError("Exista deja acest student in database!");
            }
            else {
                showMessage(Alert.AlertType.INFORMATION, "Informare", "Studentul a fost salvat cu succes!");
            }
        } catch (ValidationException e) {
            showError(e.getMessage());
        }
    }

    public void handleDelete(ActionEvent actionEvent) {

        Student aux =  view.tabelStudent.getSelectionModel().getSelectedItem();
        if(aux==null) {
            showError("Nu ai selectat nimic pentru stergere!");
        }
        else {
            if(!service.deleteStundet(view.fieldId.getText()).isPresent()) {
                showError("Nu exista  acest student in database!");
            }
            else {
                showMessage(Alert.AlertType.INFORMATION,"Informare","Studentul a fost sters cu succes!");
            }
        }


    }

    public void handleUpdate(ActionEvent actionEvent) {
        try {

            service.updateStudent(getStudentFromFields());
            showMessage(Alert.AlertType.INFORMATION,"Informare","Student updatat cu succes!");

        } catch (ValidationException e) {
            showError(e.getMessage());
        }
    }

    public void handleSearch(String newValue) {

        FilteredList<Student> filt = new FilteredList<Student>(getModel(),x->true);
        filt.setPredicate(x->x.getName().contains(newValue));
        view.tabelStudent.setItems(filt);
    }


    public Student getStudentFromFields() {
        String id = view.fieldId.getText();
        String nume = view.fieldNume.getText();
        int grupa=0;
        if (view.fieldGrupa.getText().isEmpty()){
            grupa = -1;
        }
        else {
            grupa = Integer.parseInt(view.fieldGrupa.getText());
        }
        //int grupa = Integer.parseInt(view.fieldGrupa.getText());
        String mail = view.fieldMail.getText();
        String prof = view.fieldProf.getText();
        return new Student(id,nume,grupa,mail,prof);
    }


    public void showStundetInfo(Student st) {
        if(st != null) {
            view.fieldId.setText(st.getId());
            view.fieldNume.setText(st.getName());
            view.fieldGrupa.setText(String.valueOf(st.getGroup()));
            view.fieldMail.setText(st.getEmail());
            view.fieldProf.setText(st.getProf());
        }
    }

    public void showError(String st) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(st);
        alert.setTitle("A aparut o eroare!");
        alert.showAndWait();
    }

    static void showMessage(Alert.AlertType type, String header, String text){
        Alert message=new Alert(type);
        message.setHeaderText(header);
        message.setContentText(text);
        message.showAndWait();
    }

    public void clearFields(ActionEvent actionEvent) {
        view.fieldId.clear();
        view.fieldNume.clear();
        view.fieldGrupa.clear();
        view.fieldMail.clear();
        view.fieldProf.clear();
    }



}
