package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bill extends BaseModel implements Serializable {

    private String name;
    private List<Sell> sells = new ArrayList<>();
    private double totalSum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sell> getSells() {
        return sells;
    }

    public void setSells(List<Sell> sells) {
        this.sells = sells;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public Bill() {
        super();
    }

    public Bill(String id, String name, List<Sell> sells, double totalSum) {
        super(id);
        this.name = name;
        this.sells = sells;
        this.totalSum = totalSum;
    }
}
