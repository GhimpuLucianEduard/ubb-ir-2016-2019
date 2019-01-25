package Views;

import Services.CatalogService;
import Views.ViewRapoarte.ViewRapoarteController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardController {


    public void setStudentService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    private CatalogService catalogService;
    @FXML
    private Pane contentPane;

    @FXML
    public void handleGetViewStudenti() {

        contentPane.getChildren().clear();
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/StudentsView.fxml"));
            newLoadedPane = loader.load();
            StudentController studentController = loader.getController();
            studentController.setService(catalogService.getServiceStudent());
            //studentController.initialize();
            catalogService.getServiceStudent().addObserver(studentController);

        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.getChildren().add(newLoadedPane);
//        AnchorPane.setTopAnchor(newLoadedPane, 10.0);
//        AnchorPane.setBottomAnchor(newLoadedPane, 10.0);
//        AnchorPane.setLeftAnchor(newLoadedPane, 10.0);
//        AnchorPane.setRightAnchor(newLoadedPane, 10.0);

    }


    public void handleGetViewTeme() {
        contentPane.getChildren().clear();
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ViewTeme.fxml"));
            newLoadedPane = loader.load();
            ViewTemeController temeController = loader.getController();
            temeController.setService(catalogService.getServiceTema());
            //temeController.initialize();
            catalogService.getServiceTema().addObserver(temeController);

        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.getChildren().add(newLoadedPane);
    }

    public void handleGetViewCatalog(MouseEvent mouseEvent) {
        contentPane.getChildren().clear();
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ViewCatalog.fxml"));
            newLoadedPane = loader.load();
            ViewCatalogController catalogController = loader.getController();
            catalogController.setService(catalogService);
            //catalogController.initialize();
            //catalogService.getServiceTema().addObserver(temeController);

        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.getChildren().add(newLoadedPane);
    }


    public void handleGetViewRapoarte(MouseEvent mouseEvent) {
        contentPane.getChildren().clear();
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ViewRapoarte.fxml"));
            newLoadedPane = loader.load();
            ViewRapoarteController controller = loader.getController();
            controller.setService(catalogService);
            catalogService.getServiceNota().addObserver(controller);
            catalogService.getServiceTema().addObserver(controller);
            catalogService.getServiceStudent().addObserver(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.getChildren().add(newLoadedPane);
    }
}
