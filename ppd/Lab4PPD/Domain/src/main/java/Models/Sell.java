package Models;

import java.io.Serializable;

public class Sell extends BaseModel implements Serializable {

    private String date;
    private Product product;
    private int amount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Sell() {
        super();
    }

    public Sell(String id, String date, Product product, int amount) {
        super(id);
        this.date = date;
        this.product = product;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Models.Sell{" +
                "date='" + date + '\'' +
                ", product=" + product.toString() +
                ", amount=" + amount +
                '}';
    }
}
