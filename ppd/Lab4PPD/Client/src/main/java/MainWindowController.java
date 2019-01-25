import Models.Bill;
import Models.Sell;
import Models.Stock;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainWindowController {

    @FXML
    TableView<Stock> tableViewStock;
    @FXML
    Label labelSelected;
    @FXML
    TextField inputQuantity;
    @FXML
    Label labelTotal;

    Double soldTotal = 0.0d;
    int selectedIndex = 0;
    Stock selectedStock;

    ObservableList<Stock> model = FXCollections.observableArrayList();

    ClientService service;
    Bill bill;
    public void setService(ClientService service) {
        this.service = service;
    }

    public MainWindowController() {
    }

    public void setModel(List<Stock> stockList) {
        model.setAll(stockList);
        tableViewStock.setItems(model);
    }

    void initialize() {
        bill = new Bill();

        tableViewStock.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) ->
        {
            try {
                selectedIndex = (int) newValue;
                selectedStock = model.get(selectedIndex);
                labelSelected.setText(selectedStock.getIdent());
            } catch (Exception e){

            }

        });
        labelTotal.setText(String.valueOf(soldTotal));
    }

    public void handleBuy(ActionEvent actionEvent) {
        String quant = inputQuantity.getText();

        if (labelSelected.getText().compareTo("Id") == 0) {
            return;
        }

        int amount = 0;
        try {
            amount = Integer.parseInt(quant);

            if (amount > selectedStock.getAvalaibleAmount()) {
                GuiUtils.showAlert(Alert.AlertType.ERROR, "error", "error", "amount too large");
                return;
            }

            double price = selectedStock.getPrice() * amount;
            soldTotal += price;
            bill.setTotalSum(soldTotal);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            Sell sell = new Sell();
            sell.setDate(timeStamp);
            sell.setAmount(amount);
            sell.setProduct(selectedStock.getProduct());
            bill.getSells().add(sell);
            labelTotal.setText(String.valueOf(soldTotal));
            selectedStock.setAvalaibleAmount(selectedStock.getAvalaibleAmount()-amount);
            tableViewStock.refresh();
        } catch (Exception e) {
            GuiUtils.showAlert(Alert.AlertType.ERROR, "error", "error", e.getMessage());
        }
    }

    public void handle_total_clicked(ActionEvent actionEvent) throws RemoteException {
        //do buy on sevrr
        bill.setName(service.getName());

        try
        {
            service.handleBuyTotal(bill);
            GuiUtils.showAlert(Alert.AlertType.INFORMATION, "Succes", "Succes", "Cumparaturi efectuate cu succes!");
        } catch (Exception e) {
            GuiUtils.showAlert(Alert.AlertType.ERROR, "error", "error", e.getMessage());
        }
    }
}
