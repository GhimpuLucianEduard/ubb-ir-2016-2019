package Models.Destinatie;

import Models.HasId;

public class Destinatie implements HasId<Integer> {

    private int id;
    private String nume;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer integer) {
        this.id=integer;
    }

    public Destinatie(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Destinatie{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }
}
