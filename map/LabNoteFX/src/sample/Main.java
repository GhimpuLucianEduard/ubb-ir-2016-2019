package sample;

import Models.Validators.StudentValidator;
import Repos.FileRepos.StudentFileRepo;
import Services.StudentService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    //TemaService teme = new TemaService(new TemaFileRepo(new TemaValidator(), "C:\\Users\\Deus\\IdeaProjects\\labNote\\src\\com\\company\\Utils\\teme.txt"));
    StudentService stud = new StudentService(new StudentFileRepo(new StudentValidator(), "C:\\Users\\Deus\\IdeaProjects\\LabNoteFX\\src\\Data\\studenti.txt"));
    //NotaService note = new NotaService(new NotaFileRepo(new NotaValidator(), "C:\\Users\\Deus\\IdeaProjects\\labNote\\src\\com\\company\\Utils\\note.txt"));
    //CatalogService catalog = new CatalogService(teme,stud,note);


//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//
//        BorderPane root  = initView();
//        primaryStage.setScene(new Scene(root, 600, 500));
//        primaryStage.show();
//    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        //Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/Views/StudentViewFXML.fxml"));
        AnchorPane rootLayout = null;
        try {
            rootLayout = (AnchorPane)loader.load();
            primaryStage.setScene(new Scene(rootLayout, 700, 400));
            primaryStage.setTitle("Hello World");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



//    private BorderPane initView() {
//        StudentController ctrlStud = new StudentController(stud);
//        stud.addObserver(ctrlStud);
//        StudentView view = new StudentView(ctrlStud);
//        ctrlStud.setView(view);
//        return view.getView();
//    }
    public static void main(String[] args) {
        launch(args);
    }
}
