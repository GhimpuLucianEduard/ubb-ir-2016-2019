package Views;

import Models.Student;
import Models.Validators.ValidationException;
import Services.StudentService;
import Utils.MessageAlert;
import Utils.MessageAlert;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.util.Optional;


public class StudentDialogController {

    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldNume;
    @FXML
    private TextField textFieldGrupa;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldProf;
    @FXML
    private Button saveStuentButton;

    private StudentService service;
    Stage view;
    Student student;

    public void setService(StudentService service, Stage stage, Student st) {
        this.service=service;
        this.view=stage;
        this.student=st;
        if (null != st) {
            setFields(st);
            textFieldId.setEditable(false);
            Tooltip tooltip = new Tooltip();
            tooltip.setText("Numarul matricol nu poate fi modificat");
            textFieldId.setTooltip(tooltip);
            //saveStuentButton.setText("Modifica");
        }

    }

    private void setFields(Student st) {
        textFieldId.setText(st.getId());
        textFieldNume.setText(st.getName());
        textFieldGrupa.setText(String.valueOf(st.getGroup()));
        textFieldEmail.setText(st.getEmail());
        textFieldProf.setText(st.getProf());
    }

    public void handleAddStudent(MouseEvent mouseEvent) {
        String id = textFieldId.getText();
        String nume = textFieldNume.getText();
        String auxInt = textFieldGrupa.getText();
        int grupa=0;
        if(auxInt.compareTo("")==0){
            grupa = -1;
        }
        else {
            grupa = Integer.parseInt(auxInt);
        }

        String email = textFieldEmail.getText();
        String prof = textFieldProf.getText();
        Student st = new Student(id,nume,grupa,email,prof);
        if(null==student) {
            saveStudent(st);
        }
        else {
            updateStudent(st);
        }


    }

    private void saveStudent(Student st) {
        try {
            Optional<Student> aux = service.addStudent(st);
            if(!aux.isPresent()) {
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Student salvat","Studentul a fost salvat cu succes!");
            }
        } catch (ValidationException e) {
            MessageAlert.showError(null,e.getMessage());
        }
        view.close();
    }

    private void updateStudent(Student st) {
        try {
            Optional<Student> aux = service.updateStudent(st);
            if(!aux.isPresent()) {
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Student modificat","Studentul a fost modificat cu succes!");
            }
        } catch (ValidationException e) {
            MessageAlert.showError(null,e.getMessage());
        }
        view.close();
    }

    public void handleCancel(MouseEvent mouseEvent) {
        view.close();
    }
}
