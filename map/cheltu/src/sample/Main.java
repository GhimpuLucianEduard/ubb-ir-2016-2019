package sample;

import Repository.FileRepository;
import Service.ServiceCheltuieli;
import UI.ControllerAdult;
import UI.ControllerMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    ServiceCheltuieli srv = new ServiceCheltuieli(new FileRepository("C:\\Users\\Deus\\IdeaProjects\\cheltu\\src\\data\\cheltuieli.txt"),"C:\\Users\\Deus\\IdeaProjects\\cheltu\\src\\data\\membri.txt");


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/UI/mainView.fxml"));
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
