import Models.Bill;
import Models.Sell;
import Models.Stock;

import java.rmi.RemoteException;
import java.util.List;

public interface IServer {
    void registerClient(IClient client, String name) throws ServerException, RemoteException;
    void buy(IClient client, Bill bill) throws RemoteException;
    Stock[] getStocks(IClient client) throws RemoteException;
}
