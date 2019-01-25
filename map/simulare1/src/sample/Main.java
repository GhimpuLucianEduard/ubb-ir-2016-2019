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
        List<Disciplina> listDiscipline = new ArrayList<>();
        Disciplina d1 = new Disciplina(1,"Map",Tip.Obligatorie,200);
        Disciplina d2 = new Disciplina(2,"Retele",Tip.Obligatorie,120);
        Disciplina d3 = new Disciplina(3,"Baze de date",Tip.Obligatorie,180);
        Disciplina d4 = new Disciplina(4,"PLF",Tip.Obligatorie,50);
        Disciplina d5 = new Disciplina(5,"PS",Tip.Obligatorie,20);
        Disciplina d6 = new Disciplina(6,"Engleza",Tip.Facultativa,1);
        Disciplina d7 = new Disciplina(7,"Cerc C",Tip.Optionala,10);
        Disciplina d8 = new Disciplina(8,"Cerc Securitate",Tip.Optionala,20);
        Disciplina d9 = new Disciplina(9,"CevaFacultativ",Tip.Facultativa,140);
        Disciplina d10 = new Disciplina(10,"Ceva Optional",Tip.Optionala,90);
        listDiscipline.add(d1);
        listDiscipline.add(d2);
        listDiscipline.add(d3);
        listDiscipline.add(d4);
        listDiscipline.add(d5);
        listDiscipline.add(d6);
        listDiscipline.add(d7);
        listDiscipline.add(d8);
        listDiscipline.add(d9);
        listDiscipline.add(d10);
        Service service = new Service(listDiscipline);
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        DashboardController rootController = loader.getController();
//        rootController.setCatalogService(catalogService);
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 700, 575));
//        primaryStage.show();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller rootController = loader.getController();
        rootController.setService(service);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }




}
