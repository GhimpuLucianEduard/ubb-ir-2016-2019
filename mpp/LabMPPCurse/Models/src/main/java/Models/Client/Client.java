package Models.Client;

import Models.HasId;

public class Client implements HasId<Integer>{

    private int id;
    private String nume;
    private String prenume;

    public Client(int id, String nume, String prenume) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }



    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id=integer;
    }
}
