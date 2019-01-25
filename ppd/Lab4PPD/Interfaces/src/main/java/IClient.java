import Models.Stock;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
    void getStock() throws RemoteException;
    void updateStock(Stock[] stock) throws RemoteException;
    String getName() throws RemoteException;
}
