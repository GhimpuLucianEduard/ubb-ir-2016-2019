package Controller;

import Models.Cursa.Cursa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerManage {

    private MainServiceClient mainService;
    private Stage parentStage;
    public void setMainService(MainServiceClient mainService, Stage mainStage) {
        this.mainService = mainService;
        this.parentStage = mainStage;
        handleGetViewCurse();
    }

    @FXML
    AnchorPane anchorPaneLeft;
    @FXML
    AnchorPane anchorPaneRight;

    @FXML
    public void initialize() {
        //MessageAlert.showError(null,"");
    }


    public void handleGetViewCurse() {

        //anchorPaneLeft.getChildren().clear();
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("ViewCurse.fxml"));
            newLoadedPane = loader.load();
            ControllerCurse controllerCurse = loader.getController();
            controllerCurse.setService(mainService,this);
            mainService.addObserver(controllerCurse);
            //controllerCurse.getService().getServiceCurse().addObserver(controllerCurse);
            //controllerCurse.getServiceStudent().addObserver(studentController);

        } catch (IOException e) {
            e.printStackTrace();
        }
        anchorPaneLeft.getChildren().add(newLoadedPane);

    }

    public void handleGetViewRezervare(Cursa cursa) {

        anchorPaneRight.getChildren().clear();
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("ViewRezervare.fxml"));
            newLoadedPane = loader.load();
            ControllerRezervare controllerRezervare = loader.getController();
            controllerRezervare.setService(mainService,cursa);
            //controllerRezervare.getService().getServiceCurse().addObserver(controllerRezervare);
            //studentController.initialize();
            //controllerCurse.getServiceStudent().addObserver(studentController);

        } catch (IOException e) {
            e.printStackTrace();
        }
        anchorPaneRight.getChildren().add(newLoadedPane);
    }
}
