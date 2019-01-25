package Models;

import java.io.Serializable;

public class Stock implements Serializable {

    private Product product;
    private int avalaibleAmount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAvalaibleAmount() {
        return avalaibleAmount;
    }

    public void setAvalaibleAmount(int avalaibleAmount) {
        this.avalaibleAmount = avalaibleAmount;
    }

    public Stock() {

    }

    public Stock(Product product, int avalaibleAmount) {
        this.product = product;
        this.avalaibleAmount = avalaibleAmount;
    }

    @Override
    public String toString() {
        return "Models.Stock{" +
                "product=" + product.toString() +
                ", avalaibleAmount=" + avalaibleAmount +
                '}';
    }

    public String getIdent() {
        return product.getId();
    }

    public String getUnitMeasure() {
        return product.getUnitMeasure();
    }

    public String getName() {
        return product.getName();
    }

    public double getPrice() {
        return product.getPrice();
    }
}
