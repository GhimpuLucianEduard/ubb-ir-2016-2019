package Views;

import Models.Nota;
import Models.Student;
import Models.Tema;
import Models.Validators.ValidationException;
import Services.CatalogService;
import Utils.CustomStringConverter;
import Utils.MessageAlert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Optional;

public class ViewCatalogDialogBoxController {

    private Nota model;
    private Stage view;
    private CatalogService service;
    private ObservableList<Student> studenti = FXCollections.observableArrayList();
    private ObservableList<Tema> teme = FXCollections.observableArrayList();
    private ObservableList<Double> note = FXCollections.observableArrayList();
    private ObservableList<Integer> saptamani = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Student> comboBoxStud;
    @FXML
    private ComboBox<Double>comboBoxNota;
    @FXML
    private ComboBox<Integer> comboBoxSapt;
    @FXML
    private ComboBox<Tema> comboBoxTema;
    @FXML
    private TextField textfieldObs;

    public void initialize() {
        comboBoxStud.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
            @Override
            public ListCell<Student> call(ListView<Student> param) {
                return new ListCell<Student>() {
                    @Override
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

        comboBoxStud.setButtonCell(
                new ListCell<Student>() {
                    @Override
                    protected void updateItem(Student t, boolean bln) {
                        super.updateItem(t, bln);
                        if (bln) {
                            setText("");
                        } else {
                            setText(t.getId());
                        }
                    }
                });

        comboBoxTema.setCellFactory(new Callback<ListView<Tema>, ListCell<Tema>>() {
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

        comboBoxTema.setButtonCell(
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
        note.addAll(1.00,1.50,2.00,2.50,3.00,3.00,4.00,4.50,5.00,6.00,7.00,8.00,9.00,10.00);
        saptamani.addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        comboBoxStud.setItems(studenti);
        comboBoxNota.setItems(note);
        comboBoxSapt.setItems(saptamani);
        comboBoxTema.setItems(teme);
    }

    public void setService(CatalogService service, Stage stage, Nota nt) {
        this.service=service;
        studenti.setAll(service.getServiceStudent().getAllStudents());
        teme.setAll(service.getServiceTema().getAllTeme());
        this.view=stage;
        this.model=nt;

        if (null != nt) {
            setFields(nt);
            comboBoxTema.setDisable(true);
            comboBoxStud.setDisable(true);
            //saveStuentButton.setText("Modifica");
        }

    }

    public void handleAddNota() {
        int idTema=0;
        String idStudent;
        if(model==null){
            Student aux = comboBoxStud.getSelectionModel().getSelectedItem();
            if (aux == null) {
                idStudent = "";
            }
            else {
                idStudent = aux.getId();
            }
            Tema aux2 = comboBoxTema.getSelectionModel().getSelectedItem();
            if (aux2 == null) {
                idTema=-1;
            }
            else {
                idTema = aux2.getId();
            }

        }
        else {
            idStudent = model.getIdStudent();
            idTema = model.getIdTema();
        }
        int sapt;
        //int sapt = CustomStringConverter.convertStringToInt(comboBoxSapt);
        if(comboBoxSapt.getSelectionModel().isEmpty()) {
            sapt=-1;
        }
        else {
            sapt=comboBoxSapt.getSelectionModel().getSelectedItem();
        }
        //double nota = CustomStringConverter.convertStringToInt(comboBoxNota);
        double nota;
        if(comboBoxNota.getSelectionModel().isEmpty()) {
            nota=-1;
        }
        else {
            nota = comboBoxNota.getSelectionModel().getSelectedItem();
        }
        Nota nt  = new Nota(idStudent,idTema,nota,sapt);
        String obs = textfieldObs.getText();
        if(null==model) {
            saveNota(nt,obs);
        }
        else {
            updateNota(nt,obs);
        }
    }

    private void updateNota(Nota nt, String obs) {
        try {
            Optional<Nota> aux = service.modificareNota(nt.getIdStudent(),nt.getIdTema(),nt.getValoare(),nt.getSaptPredare(),obs);
            if(!aux.isPresent()) {
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Nota modificata","Nota a fost modificata cu succes!");
            }
            else {
                MessageAlert.showError(null,"Nota nu s-a a fost modificata, deorece nota va fi mai mica!");
            }
        } catch (ValidationException e) {
            MessageAlert.showError(null,e.getMessage());
        }
        view.close();
    }

    private void saveNota(Nota nt, String obs) {
        try {
            Optional<Nota> aux = service.adaugareNota(nt.getIdStudent(),nt.getIdTema(),nt.getValoare(),nt.getSaptPredare(),obs);
            if(!aux.isPresent()) {
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Nota Salvata","Nota a fost salvat cu succes!");
            }
        } catch (ValidationException e) {
            MessageAlert.showError(null,e.getMessage());
        }
        view.close();
    }

    private void setFields(Nota nt) {
        comboBoxStud.setValue(service.getServiceStudent().findStudentById(nt.getIdStudent()).get());
        comboBoxNota.setValue(nt.getValoare());
        comboBoxTema.setValue(service.getServiceTema().findTemaById(nt.getIdTema()).get());
        comboBoxSapt.setValue(nt.getSaptPredare());
    }


    public void handleCancel(ActionEvent actionEvent) {
        view.close();
    }
}
