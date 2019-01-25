package Views;

import Models.Student;
import Models.Validators.ValidationException;
import Services.StudentService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

//todo evental in loc de text area sa fie un tabel
public class ViewIstoricNoteController {

    public void setModel(Student model) {
        this.model = model;
        readFile();
    }

    private Student model;
    @FXML
    private TextArea textIstoric;


    @FXML
    public void initialize() {
        textIstoric.setEditable(false);
    }

    private void readFile() {

        Path path = Paths.get("C:\\Users\\Deus\\IdeaProjects\\LabNoteFXML\\src\\Catalog\\" + model.getId()+".txt");
        try {
            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> {
                if (line.compareTo("")!=0) {
                    textIstoric.appendText(line);
                    textIstoric.appendText("\n");
                }
            });
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
