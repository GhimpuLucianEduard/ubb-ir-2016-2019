package Models;

public class Nota implements HasID<String> {

    private String id;
    private String idStudent;
    private int idTema;
    private double valoare;
    private int saptPredare;

    public Nota(String idStudent, int idTema, double valoare, int saptPredare) {

        this.id = idStudent + idTema;
        this.idStudent = idStudent;
        this.idTema = idTema;
        this.valoare = valoare;
        this.saptPredare = saptPredare;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String s) {
        id = s;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    public int getSaptPredare() {
        return saptPredare;
    }

    public void setSaptPredare(int saptPredare) {
        this.saptPredare = saptPredare;
    }

    @Override
    public String toString() {
        return idStudent+";"+idTema+";"+valoare+";"+saptPredare+";";
    }
}
