package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("StudentViewFXML.fxml"));
        AnchorPane rootLayout = null;
        try {
            rootLayout = (AnchorPane) loader.load();
            primaryStage.setScene(new Scene(rootLayout, 700, 400));
            primaryStage.setTitle("Hello World");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
            launch(args);
    }
}
