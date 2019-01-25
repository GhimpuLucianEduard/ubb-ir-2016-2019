package dto;

import java.io.Serializable;

public class DestinatieDTO implements Serializable {

    private int id;
    private String nume;


    public Integer getId() {
        return id;
    }

    public void setId(Integer integer) {
        this.id=integer;
    }

    public DestinatieDTO(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

}
