package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        List<Articol> list = new ArrayList<>();
        Articol a1 = new Articol("Cod1",Domeniu.Mate,"Algebra avansata", "Vasile Matematicul;","algebra;matemamatica;avansat");
        Articol a2 = new Articol("Cod1232",Domeniu.Mate,"Analiza avansata", "Vasile Matematicul;Ion Fizicianul;","algebra;matemamatica;avansat");
        Articol a3 = new Articol("Cod3321",Domeniu.Mate,"Calcul avansata", "Vasile Matematicul;Ion Fizicianul;Bogdan Informaticianul","algebra;matemamatica;avansat");
        Articol a4 = new Articol("Cod3412",Domeniu.Fizica,"Gravitatia", "Vasile Voiculescu","algebra;matemamatica;avansat");
        Articol a5 = new Articol("Cod7651",Domeniu.Fizica,"Energia cinetica", "Vasile Matematicul;Ion Fizicianul;Vasile Voiculescu","algebra;matemamatica;avansat");
        Articol a6 = new Articol("Cod2311",Domeniu.Fizica,"Corupuri", "Vasile Matematicul;Ion Fizicianul;Bogdan Informaticianul","algebra;matemamatica;avansat");
        Articol a7 = new Articol("Cod52341",Domeniu.Info,"C++ basics", "Vasile Matematicul;Ion Fizicianul;Bogdan Informaticianul","algebra;matemamatica;avansat");
        Articol a8 = new Articol("Cod436621",Domeniu.Info,"C++ vs Java", "Vasile Matematicul;Ion Fizicianul;Bogdan Informaticianul","algebra;matemamatica;avansat");
        Articol a9 = new Articol("Cod1532",Domeniu.Info,"How to C++", "Vasile Matematicul;Ion Fizicianul;Bogdan Informaticianul","algebra;matemamatica;avansat");
        Articol a10 = new Articol("Cod6261",Domeniu.Info,"Java 4ever", "Vasile Matematicul;Ion Fizicianul;Bogdan Informaticianul","algebra;matemamatica;avansat");
        Articol a11 = new Articol("Cod21341",Domeniu.Info,"GUI tutorial", "Vasile Matematicul;Ion Fizicianul;Bogdan Informaticianul","algebra;matemamatica;avansat");
        Articol a12 = new Articol("Cod8651",Domeniu.Info,"Informatica de baza", "Vasile Matematicul;Ion Fizicianul;Bogdan Informaticianul","algebra;matemamatica;avansat");
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        list.add(a6);
        list.add(a7);
        list.add(a8);
        list.add(a9);
        list.add(a10);
        list.add(a11);
        list.add(a12);
        Service srv = new Service(list);
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller rootController = loader.getController();
        rootController.setService(srv);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
