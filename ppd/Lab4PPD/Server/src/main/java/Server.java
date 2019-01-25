import Models.Bill;
import Models.Product;
import Models.Sell;
import Models.Stock;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Server implements IServer {

    private int THREADS_COUNT = 4;
    private Map<String, IClient> loggedClients;
    private Repository repository;
    private ConcurrentHashMap<String, Bill> bills;

    public Server() {
        init();
    }

    private void init() {
        repository = new Repository();
        bills = new ConcurrentHashMap<>();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FileManager.getInstance().getFilePath());
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        loggedClients = new TreeMap<>();
        randomBuy();
        checkSells();
    }

    @Override
    public void registerClient(IClient client, String name) throws ServerException {

        System.out.println("Register client" + name);
        if (loggedClients.containsKey(name)) {
            throw new ServerException("Client with username: " + name + " already logged in.");
        } else {
            FileManager.getInstance().writeToFile("[LOGIN] Client " + name + " logged in;");
            loggedClients.put(name, client);
        }
    }

    private class LockAmount {
        public double totalAmount = 0;
        public ReentrantLock lock = new ReentrantLock();
    }

    LockAmount totalAmount = new LockAmount();

    @Override
    public void buy(IClient client, Bill Bill) throws RemoteException {

        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            synchronized (bills) {
                try {
                    bills.put(client.getName(), Bill);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                synchronized (totalAmount) {
                    //totalAmount.lock.lock();
                    totalAmount.totalAmount += Bill.getTotalSum();
                    for (Sell sell : Bill.getSells()) {
                        try {
                            FileManager.getInstance().writeToFile("[SELL] Client " + client.getName() + " " + sell.toString());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        FileManager.getInstance().writeToFile("[SELL] total: " + Bill.getTotalSum());
                    }
                    repository.updateStock(Bill);
                }
            }
        });
        executor.shutdown();
        notifyStockChanged();
    }

    private void notifyStockChanged() {
        FileManager.getInstance().writeToFile("[NOTIFY STOCK CHANGED]");

        ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
        for (Map.Entry<String, IClient> e : loggedClients.entrySet()) {
            IClient client = e.getValue();
            executor.submit(() -> {
                try {
                    FileManager.getInstance().writeToFile("[NOTIFY STOCK CHANGED] " + e.getValue().getName());

                    List<Stock> stocks = new ArrayList<>(repository.getStocks().values());
                    Stock[] stocksDTO = new Stock[stocks.size()];
                    for (int i = 0; i < stocks.size(); i++) {
                        stocksDTO[i] = stocks.get(i);
                    }

                    Stock[] list = stocksDTO;
                    client.updateStock(list);
                } catch (Exception ex) {
                    FileManager.getInstance().writeToFile("[ERROR] " + ex.getMessage());
                }
            });
        }

        executor.shutdown();
    }

    @Override
    public Stock[] getStocks(IClient client) throws RemoteException {
        List<Stock> stocks = new ArrayList<>(repository.getStocks().values());
        Stock[] stocksDTO = new Stock[stocks.size()];
        FileManager.getInstance().writeToFile("[GET STOCK] Client: " + client.getName());
        for (int i = 0; i < stocks.size(); i++) {
            stocksDTO[i] = stocks.get(i);
        }
        return stocksDTO;
    }

    private void randomBuy() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (bills) {
                    synchronized (totalAmount) {
                        Product pr = getRandom();
                        Sell sell = new Sell();
                        sell.setProduct(pr);
                        sell.setDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                        sell.setAmount(1);
                        Bill bill = new Bill();
                        bill.getSells().add(sell);
                        bill.setTotalSum(sell.getAmount() * pr.getPrice());
                        bill.setName(sell.getDate());
                        bills.put(bill.getName(), bill);
                        totalAmount.totalAmount += bill.getTotalSum();
                        FileManager.getInstance().writeToFile("[SELL] total: " + bill.getTotalSum());
                        repository.updateStock(bill);
                        notifyStockChanged();
                    }
                }
            }
        }, 0, 3000);
    }

    private void checkSells() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (bills) {
                    synchronized (totalAmount) {
                        if (!bills.isEmpty()) {
                            FileManager.getInstance().writeToFile("[================ START CHECK ======================]");

                            final double[] sumBills = {0};
                            bills.values().stream().collect(Collectors.toList()).forEach(bill -> {
                                sumBills[0] += bill.getTotalSum();
                            });


                            if (sumBills[0] == totalAmount.totalAmount) {
                                FileManager.getInstance().writeToFile("[================ NO PROBLEMS FOUND ======================]");
                                FileManager.getInstance().writeToFile("[================ TOTAL SUM " + totalAmount.totalAmount + " ======================]");
                            } else {
                                FileManager.getInstance().writeToFile("[================ WE HAVE A PROBLEM ======================]");
                                FileManager.getInstance().writeToFile("[================ EXPECTED TOTAL " + totalAmount.totalAmount + " TOTAL FOUND: " + sumBills[0] +" ======================]");
                            }
                            totalAmount.totalAmount = 0;
                            bills.clear();
                        }
                        }
                }
            }
        }, 0, 5000);

    }

    private Product getRandom() {
        List<Product> productList = repository.getAvailableProducts();
        int rnd = new Random().nextInt(productList.size() - 1);
        return productList.get(rnd);
    }
}
