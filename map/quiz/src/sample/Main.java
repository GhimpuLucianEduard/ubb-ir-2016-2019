package sample;

import Repo.FileRepository;
import Service.ServiceIntrebari;
import Service.ServiceTest;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static String[] arguments;

    @Override
    public void start(Stage primaryStage) throws Exception{

        ServiceIntrebari serv = new ServiceIntrebari(new FileRepository("C:\\Users\\Deus\\IdeaProjects\\quiz\\src\\Data\\quizes.txt"));
        ServiceTest srvt = new ServiceTest();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/viewProfesor/mainView.fxml"));
        Parent root = loader.load();

        viewProfesor.ControllerMain rootController = loader.getController();
        rootController.setService(serv,srvt);



        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
//        for(String arg:arguments) {
//            rootController.handleStud(arg);
//        }

    }



    public static void main(String[] args) {
        arguments = args;
        launch(args);

    }
}
