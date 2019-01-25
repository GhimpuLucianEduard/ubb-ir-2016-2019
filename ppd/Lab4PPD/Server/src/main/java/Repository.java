import Models.Bill;
import Models.Product;
import Models.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Repository {

    private ConcurrentHashMap<String, Stock> stocks;

    public ConcurrentHashMap getStocks() {
        return stocks;
    }

    public Repository() {
        stocks = new ConcurrentHashMap<>();
        stocks.put("p1", new Stock(new Product("p1", "Cartofi", 7.0d, "Kg"), 1000));
        stocks.put("p2", new Stock(new Product("p2", "Morcovi", 12.0d, "Kg"), 200));
        stocks.put("p3", new Stock(new Product("p3", "Apa Plata", 4.0d, "L"), 3000));
        stocks.put("p4", new Stock(new Product("p4", "Bors", 6.0d, "L"), 1020));
        stocks.put("p5", new Stock(new Product("p5", "Drojdie", 1.0d, "g"), 500));
        stocks.put("p6", new Stock(new Product("p6", "Malai", 13.0d, "Kg"), 270));
    }

    public List<Product> getAvailableProducts() {
        List<Product> pr = new ArrayList<>();
        pr.add(new Product("p1", "Cartofi", 7.0d, "Kg"));
        pr.add(new Product("p2", "Morcovi", 12.0d, "Kg"));
        pr.add(new Product("p3", "Apa Plata", 4.0d, "L"));
        pr.add(new Product("p4", "Bors", 6.0d, "L"));
        pr.add(new Product("p5", "Drojdie", 1.0d, "g"));
        pr.add(new Product("p6", "Malai", 13.0d, "Kg"));
        return pr;
    }

    public void updateStock(Bill bill) {
        bill.getSells().forEach(s -> {
            int amount = stocks.get(s.getProduct().getId()).getAvalaibleAmount();
            stocks.get(s.getProduct().getId()).setAvalaibleAmount(amount - s.getAmount());
            FileManager.getInstance().writeToFile("[STOCK CHANGE] Product: " + s.getProduct().getId() + " new amount: " + stocks.get(s.getProduct().getId()).getAvalaibleAmount());
        });
    }
}
