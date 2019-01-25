package Models;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private Double price;
    private String unitMeasure;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public Product() {
        super();
    }

    public Product(String id, String name, Double price, String unitMeasure) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unitMeasure = unitMeasure;
    }

    @Override
    public String toString() {
        return "Models.Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", unitMeasure='" + unitMeasure + '\'' +
                '}';
    }
}
