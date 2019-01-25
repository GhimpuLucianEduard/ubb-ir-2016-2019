import Models.Bill;
import Models.Stock;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ClientService extends UnicastRemoteObject implements IClient {

    private IServer server;
    MainWindowController mainWindowController;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public ClientService(IServer server) throws RemoteException {
        this.server = server;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public IServer getServer() {
        return server;
    }

    public void login(String name) throws ServerException, RemoteException {
        server.registerClient(this, name);
        this.name = name;
    }

    @Override
    public void getStock() throws RemoteException {
        Stock[] stocks = server.getStocks(this);
        List<Stock> list = new ArrayList<>();
        for (int i = 0; i < stocks.length; i++) {
            list.add(stocks[i]);
        }
        mainWindowController.setModel(list);
    }

    @Override
    public void updateStock(Stock[] stock) throws RemoteException {
        List<Stock> list = new ArrayList<>();
        for (int i = 0; i < stock.length; i++) {
            list.add(stock[i]);
        }
        mainWindowController.setModel(list);
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    public void handleBuyTotal(Bill bill) throws RemoteException {
        server.buy(this, bill);
    }
}
