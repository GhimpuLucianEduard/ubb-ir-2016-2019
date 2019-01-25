package Models;

public class Raspuns {
    private String nume;
    private int codI;
    private String raspuns;
    private double punctaj;

    public double getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(double punctaj) {
        this.punctaj = punctaj;
    }

    public Raspuns(String nume, int codI, String raspuns) {
        this.nume = nume;
        this.codI = codI;
        this.raspuns = raspuns;
    }

    public Raspuns(String nume, int codI, double punctaj) {
        this.nume = nume;
        this.codI = codI;
        this.raspuns = raspuns;
    }

    public Raspuns(String nume, int codI, String ras, double punctaj) {
        this.nume = nume;
        this.codI = codI;
        this.raspuns = null;
        this.punctaj = punctaj;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCodI() {
        return codI;
    }

    public void setCodI(int codI) {
        this.codI = codI;
    }

    public String getRaspuns() {
        return raspuns;
    }

    public void setRaspuns(String raspuns) {
        this.raspuns = raspuns;
    }
}
