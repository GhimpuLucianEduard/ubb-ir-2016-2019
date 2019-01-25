package Views;

import Models.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.lang.reflect.Field;
import java.util.Arrays;

public class StudentView {

    StudentController ctrl;

    public StudentView(StudentController ctrl) {
        this.ctrl = ctrl;
        initView();
    }

    TextField findTextField = createField();

    BorderPane borderPane;
    TableView<Student> tabelStudent = new TableView<>();
    Button buttonAdd = new Button("Add Student");
    Button buttonDelete = new Button("Delete Student");
    Button buttonUpdate = new Button("Update Student");
    Button buttonClear = new Button("Clear Fields");


    Label labelId = createBasicLabel("Id");
    Label labelNume = createBasicLabel("Nume");
    Label labelGrupa = createBasicLabel("Grupa");
    Label labelMail = createBasicLabel("Mail");
    Label labelProf = createBasicLabel("Prof");

    TextField fieldId = createField();
    TextField fieldNume = createField();
    TextField fieldGrupa = createField();
    TextField fieldMail = createField();
    TextField fieldProf = createField();

    private void initView() {
        borderPane = new BorderPane();
        borderPane.setTop(initTop());
        borderPane.setCenter(initCenter());
        borderPane.setBottom(initBottom());
    }


    private AnchorPane initTop() {

        AnchorPane topAnchorPane = new AnchorPane();
        findTextField = createField();
        findTextField.setPromptText("Tipariti numele studentului de cautat:");
        findTextField.setFocusTraversable(false);
        findTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                ctrl.handleSearch(newValue);
            }
        });
        topAnchorPane.getChildren().add(findTextField);
        topAnchorPane.setTopAnchor(findTextField,10d);
        topAnchorPane.setLeftAnchor(findTextField,10d);
        topAnchorPane.setRightAnchor(findTextField,10d);
        topAnchorPane.setBottomAnchor(findTextField,10d);
        return topAnchorPane;
    }

    private AnchorPane initCenter() {
        AnchorPane centerAnchorPane = new AnchorPane();
        TableView<Student> tableview = createTableView();
        centerAnchorPane.getChildren().add(tableview);
        AnchorPane.setTopAnchor(tableview,10d);
        AnchorPane.setLeftAnchor(tableview,10d);
        AnchorPane.setBottomAnchor(tableview,10d);
        AnchorPane.setRightAnchor(tableview,10d);


        return centerAnchorPane;

    }

    private AnchorPane initBottom() {
        AnchorPane bottomPane = new AnchorPane();
        GridPane gridPane = createGridPane();
        bottomPane.getChildren().add(gridPane);
        AnchorPane.setTopAnchor(gridPane,10d);
        AnchorPane.setBottomAnchor(gridPane,10d);
        AnchorPane.setLeftAnchor(gridPane,10d);
        AnchorPane.setRightAnchor(gridPane,10d);
        return bottomPane;
    }


    public BorderPane getView(){ return borderPane; }

    private TextField createField() {
        TextField textField = new TextField();
        return textField;
    }

    private Label createBasicLabel(String s) {
        Label l = new Label(s);
        l.setFont(new Font(20));
        return l;
    }

    private TableView<Student> createTableView() {
        TableColumn<Student,String> columnId = new TableColumn<>("Nr. Matricol");
        TableColumn<Student,String> columnNume = new TableColumn<>("Nume");
        TableColumn<Student,Integer> columnGrupa = new TableColumn<>("Grupa");
        TableColumn<Student,String> columnMail = new TableColumn<>("Mail");
        TableColumn<Student,String> columnProf = new TableColumn<>("Prof");
        tabelStudent.getColumns().addAll(columnId,columnNume,columnGrupa,columnMail,columnProf);

        columnId.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
        columnNume.setCellValueFactory(new PropertyValueFactory<Student,String>("Name"));
        columnGrupa.setCellValueFactory(new PropertyValueFactory<Student,Integer>("Group"));
        columnMail.setCellValueFactory(new PropertyValueFactory<Student,String>("Email"));
        columnProf.setCellValueFactory(new PropertyValueFactory<Student,String>("prof"));

        tabelStudent.setItems(ctrl.getModel());
        tabelStudent.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tabelStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
                ctrl.showStundetInfo(newValue);
            }
        });



        return tabelStudent;
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();

        gridPane.add(labelId,0,0);
        gridPane.add(labelNume,1,0);
        gridPane.add(labelGrupa,2,0);
        gridPane.add(labelMail,3,0);
        gridPane.add(labelProf,4,0);



        gridPane.add(fieldId,0,1);
        fieldId.setPromptText("Id:");
        fieldId.setFocusTraversable(false);
        gridPane.add(fieldNume,1,1);
        fieldNume.setPromptText("Nume:");
        fieldNume.setFocusTraversable(false);
        gridPane.add(fieldGrupa,2,1);
        fieldGrupa.setPromptText("Grupa:");
        fieldGrupa.setFocusTraversable(false);
        gridPane.add(fieldMail,3,1);
        fieldMail.setPromptText("Mail:");
        fieldMail.setFocusTraversable(false);
        gridPane.add(fieldProf,4,1);
        fieldProf.setPromptText("Prof:");
        fieldProf.setFocusTraversable(false);

        gridPane.add(buttonAdd,0,2);
        buttonAdd.setOnAction(ctrl::handleAdd);
        gridPane.add(buttonDelete,1,2);
        buttonDelete.setOnAction(ctrl::handleDelete);
        gridPane.add(buttonUpdate,2,2);
        buttonUpdate.setOnAction(ctrl::handleUpdate);
        gridPane.add(buttonClear,3,2);
        buttonClear.setOnAction(ctrl::clearFields);


        gridPane.setHgap(10); //horizontal gap in pixels
        gridPane.setVgap(10); //horizontal gap in pixels

        return gridPane;

    }


}
