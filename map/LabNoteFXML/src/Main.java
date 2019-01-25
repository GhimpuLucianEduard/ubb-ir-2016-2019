package sample;

import Models.Student;
import Models.Validators.NotaValidator;
import Models.Validators.StudentValidator;
import Models.Validators.TemaValidator;
import Repos.DBRepos.DBRepoNota;
import Repos.DBRepos.DBRepoStudent;
import Repos.DBRepos.DBRepoTema;
import Repos.DBRepos.DBSettings;
import Repos.FileRepos.NotaStreamFileRepo;
import Repos.FileRepos.StudentStreamFileRepo;
import Repos.FileRepos.TemaStreamFileRepo;
import Repos.Repository;
import Services.CatalogService;
import Services.NotaService;
import Services.StudentService;
import Services.TemaService;
import Views.DashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //PDDocument document = new PDDocument();document.save("C:\\Users\\Deus\\IdeaProjects\\LabNoteFXML\\src\\Catalog");
        /*
        db test area
         */
        DBSettings.setUsername("testlogin2");
        DBSettings.setPass("pass");
        DBSettings.setConnectionString("jdbc:sqlserver://localhost;databaseName=LabNoteMap;integratedSecurity=true;");
        Repository<Student,String> repoStudDB = new DBRepoStudent(new StudentValidator());
        StudentService studentService = new StudentService(repoStudDB);

        TemaService temaService = new TemaService(new DBRepoTema(new TemaValidator()));
        NotaService notaService = new NotaService(new DBRepoNota(new NotaValidator()));


        /*
            normal
         */
        //StudentService studentService = new StudentService(new StudentStreamFileRepo(new StudentValidator(),"C:\\Users\\Deus\\IdeaProjects\\LabNoteFXML\\src\\Data\\studenti.txt"));
        //TemaService temaService = new TemaService(new TemaStreamFileRepo(new TemaValidator(),"C:\\Users\\Deus\\IdeaProjects\\LabNoteFXML\\src\\Data\\teme.txt"));
        //NotaService notaService = new NotaService(new NotaStreamFileRepo(new NotaValidator(), "C:\\Users\\Deus\\IdeaProjects\\LabNoteFXML\\src\\Data\\note.txt"));
        CatalogService catalogService = new CatalogService(temaService,notaService,studentService);
        //Parent root = FXMLLoader.load(getClass().getResource("/Views/Dashboard.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Dashboard.fxml"));
        //newLoadedPane = loader.load();
        Parent root = loader.load();

        DashboardController rootController = loader.getController();
        rootController.setCatalogService(catalogService);
        rootController.handleGetViewStudenti();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
