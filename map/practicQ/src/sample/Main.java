package sample;

import Repo.FileRepository;
import Service.ServiceIntrebari;
import Service.ServiceTest;
import UI.ControllerProf;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static String[] arguments;
    ServiceIntrebari servInt = new ServiceIntrebari(new FileRepository("C:\\Users\\Deus\\IdeaProjects\\practicQ\\src\\intrebari.txt"));

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/UI/viewProf.fxml"));
        Parent root = loader.load();
//        for(String arg:arguments) {
//            System.out.println(arg);
//        }
        ControllerProf rootController = loader.getController();
        ServiceTest serviceTest = new ServiceTest();
        rootController.setService(servInt,arguments,serviceTest);
        servInt.addObserver(rootController);
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        arguments = args;
        launch(args);
    }
}
