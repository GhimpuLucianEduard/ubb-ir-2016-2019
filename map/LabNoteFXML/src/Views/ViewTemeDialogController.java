package Views;

import Models.Tema;
import Models.Validators.ValidationException;
import Services.StudentService;
import Services.TemaService;
import Utils.CustomStringConverter;
import Utils.MessageAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;


public class ViewTemeDialogController {

    private Tema model;
    private Stage view;
    private TemaService service;

    @FXML
    private TextField fieldId;
    @FXML
    private TextField fieldDeadline;
    @FXML
    private TextArea fieldDesc;
    @FXML
    private Button buttonAddTema;
    @FXML
    private Button buttonCancel;



    public void setService(TemaService service, Stage view, Tema tm) {
        this.service=service;
        this.model=tm;
        this.view=view;
        if(tm!=null) {
            setField(tm);
            fieldId.setEditable(false);
            Tooltip tooltip = new Tooltip();
            tooltip.setText("Numarul temei nu poate fi modificat");
            fieldId.setTooltip(tooltip);
        }
    }



    public void setField(Tema tm) {
        fieldId.setText(String.valueOf(tm.getId()));
        fieldDeadline.setText(String.valueOf(tm.getDeadline()));
        fieldDesc.setText(tm.getInfo());
    }

    public void handleAddTema(ActionEvent actionEvent) {
        int id  = CustomStringConverter.convertStringToInt(fieldId);
        int deadline = CustomStringConverter.convertStringToInt(fieldDeadline);
        String info = fieldDesc.getText();
        Tema tm = new Tema(id,info,deadline);
        if(model==null) {
            saveTema(tm);
        }
        else
        {
            updateTema(tm);
        }
    }

    private void updateTema(Tema tm) {
        try {
            Optional<Tema> aux = service.updateTema(tm);
            if(!aux.isPresent()) {
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Tema modificata","Tema a fost modificata cu succes!");
            }
        } catch (ValidationException e) {
            MessageAlert.showError(null,e.getMessage());
        }
        view.close();
    }




    private void saveTema(Tema tm) {
        try {
            Optional<Tema> aux = service.addTema(tm);
            if(!aux.isPresent()) {
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Tema salvata","Tema a fost salvata cu succes!");
            }
        } catch (ValidationException e) {
            MessageAlert.showError(null,e.getMessage());
        }
        view.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        view.close();
    }
}
