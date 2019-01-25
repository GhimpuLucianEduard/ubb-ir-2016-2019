import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClient extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ApplicationContext factory = new ClassPathXmlApplicationContext("/spring.xml");
            IServer serv = (IServer) factory.getBean("service");
            FXMLLoader loader = new FXMLLoader(MainClient.class.getResource("/login_window.fxml"));
            AnchorPane myPane = (AnchorPane) loader.load();

            ClientService service = new ClientService(serv);
            LoginWindowController ctrl = loader.getController();
            ctrl.setService(service);
            Scene myScene = new Scene(myPane);
            primaryStage.setScene(myScene);
            primaryStage.setTitle("Login!");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
