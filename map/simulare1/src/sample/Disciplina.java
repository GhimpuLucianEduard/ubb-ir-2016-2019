package sample;

public  class Disciplina {
    private int cod;
    private String denumire;
    private Tip tip;
    private int nrStudenti;

    public Disciplina(int cod, String denumire, Tip tip, int nrStudenti) {
        this.cod = cod;
        this.denumire = denumire;
        this.tip = tip;
        this.nrStudenti = nrStudenti;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public int getNrStudenti() {
        return nrStudenti;
    }

    public void setNrStudenti(int nrStudenti) {
        this.nrStudenti = nrStudenti;
    }


}
