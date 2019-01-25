package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Repo.Repository;
import sample.Service.Service;
import sample.viewPrincipal.ControllerProgramatori;
import sample.viewPrincipal.ControllerTesteri;
import sample.viewPrincipal.mainController;

import java.io.IOException;

public class Main extends Application {

    Service srv = new Service(new Repository("C:\\Users\\Deus\\IdeaProjects\\repet2\\src\\sample\\data.txt"));


    @Override
    public void start(Stage primaryStage) throws Exception{
            show1();
            show2();

    }

    private void show2() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewPrincipal/viewProgramatori.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ControllerProgramatori ctr = loader.getController();
            ctr.setService(srv);
            ctr.getService().addObserver(ctr);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show1() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewPrincipal/viewTesteri.fxml"));
        try {
            AnchorPane root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ControllerTesteri ctr = loader.getController();
            ctr.setService(srv);
            ctr.getService().addObserver(ctr);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
