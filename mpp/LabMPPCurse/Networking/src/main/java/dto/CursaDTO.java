package dto;

import java.io.Serializable;

public class CursaDTO implements Serializable {

    private int id;
    private int idDestinatie;
    private String data;
    private String locatiePlecare;
    private int nrLocuriDisponibile;

    public String getLocatiePlecare() {
        return locatiePlecare;
    }

    public void setLocatiePlecare(String locatiePlecare) {
        this.locatiePlecare = locatiePlecare;
    }

    public CursaDTO(int id, int idDestinatie, String data, String locatiePlecare, int nrLocuriDisponibile) {
        this.id = id;
        this.idDestinatie = idDestinatie;
        this.data = data;
        this.locatiePlecare = locatiePlecare;

        this.nrLocuriDisponibile = nrLocuriDisponibile;
    }

    public int getIdDestinatie() {
        return idDestinatie;
    }

    public void setIdDestinatie(int idDestinatie) {
        this.idDestinatie = idDestinatie;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getNrLocuriDisponibile() {
        return nrLocuriDisponibile;
    }

    public void setNrLocuriDisponibile(int nrLocuriDisponibile) {
        this.nrLocuriDisponibile = nrLocuriDisponibile;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer integer) {
        this.id=integer;
    }

}
