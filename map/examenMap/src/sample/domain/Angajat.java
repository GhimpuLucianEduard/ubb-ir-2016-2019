package sample.domain;

public class Angajat {
    private String nume;
    private tipAngajat tip;
    private boolean stare;

    public Angajat(String nume, tipAngajat tip) {
        this.nume = nume;
        this.tip = tip;
        this.stare=true;
    }

    public Angajat(String nume, tipAngajat tip, boolean stare) {
        this.nume = nume;
        this.tip = tip;
        this.stare = stare;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public tipAngajat getTip() {
        return tip;
    }

    public boolean isStare() {
        return stare;
    }

    public void setStare(boolean stare) {
        this.stare = stare;
    }

    public void setTip(tipAngajat tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return
                "nume='" + nume + '\'' +
                ", tip=" + tip +
                ", activ=" + stare +
                '}';
    }
}
