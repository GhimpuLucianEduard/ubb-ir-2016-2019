package viewProfesor;

import Models.Intrebare;
import Observer.ListEvent;
import Observer.Observer;
import Service.ServiceIntrebari;
import Service.ServiceTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Collection;

public class Controller implements Observer<Intrebare> {
    private ServiceIntrebari srv;
    private ServiceTest srvt;
    private ObservableList<Intrebare> model = FXCollections.observableArrayList();
    public ObservableList<Intrebare> getModel() {
        return model;
    }

    @FXML
    ListView list;

    @FXML
    public void initialie() {

    }

    public void setService(ServiceIntrebari srv, ServiceTest srvt) {
        this.srv = srv;
        this.srvt = srvt;
        this.model.setAll(srv.getAll());
        list.setItems(model);

    }


    @Override
    public void notifyEvent(ListEvent<Intrebare> e) {
        this.model.setAll((Collection<? extends Intrebare>) e.getList());
        list.setItems(model);
    }

    public void handleAddToQuiz(ActionEvent actionEvent) {
        Intrebare e = (Intrebare) list.getSelectionModel().getSelectedItem();
        if(e==null){
            MessageAlert.showError(null,"Selecteaza o intrebare din lista!");
        }
        else {
            srvt.addToTest(e);
            srv.deleteIntrebare(e.getId());
        }
    }
}