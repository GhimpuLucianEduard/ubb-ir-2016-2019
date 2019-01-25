package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Repo.FileRepositoryAngajat;
import sample.Repo.FileRepositoryMesaje;
import sample.Service.ServiceAngajat;
import sample.Service.ServiceMesaj;
import sample.UI.ControllerMain;

public class Main extends Application {


    public ServiceAngajat srv = new ServiceAngajat(new FileRepositoryAngajat("C:\\Users\\Deus\\IdeaProjects\\examenMap\\src\\sample\\data\\angajati.txt"),new FileRepositoryMesaje("C:\\Users\\Deus\\IdeaProjects\\examenMap\\src\\sample\\data\\discutiiCuSeful.txt"));



    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UI/mainView.fxml"));
        Parent root = loader.load();
        ControllerMain rootController = loader.getController();

        rootController.setService(srv);
        //srv.addObserver(rootController);
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.hide();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
