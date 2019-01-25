package sample.Domain;

public class Bug {
    private String nume;
    private int id;

    public Bug(String nume, int id) {
        this.nume = nume;
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;

    }

    @Override
    public String toString() {
        return nume+";"+id+";";
    }
}
