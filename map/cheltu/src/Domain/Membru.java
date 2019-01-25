package Domain;

public class Membru {

    private String nume;
    private TipMembru tip;
    private double sumaDisp;

    public Membru(String nume, TipMembru tip, double sumaDisp) {
        this.nume = nume;
        this.tip = tip;
        this.sumaDisp = sumaDisp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public TipMembru getTip() {
        return tip;
    }

    public void setTip(TipMembru tip) {
        this.tip = tip;
    }

    public double getSumaDisp() {
        return sumaDisp;
    }

    public void setSumaDisp(double sumaDisp) {
        this.sumaDisp = sumaDisp;
    }

    public String toString() {
        return nume+" suma disp: "+sumaDisp;
    }
}
