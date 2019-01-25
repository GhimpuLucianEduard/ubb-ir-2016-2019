package Models.Cursa;

import Models.HasId;


public class Cursa implements HasId<Integer>{

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

    public Cursa(int id, int idDestinatie, String data, String locatiePlecare, int nrLocuriDisponibile) {
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

    @Override
    public String toString() {
        return "Cursa{" +
                "id=" + id +
                ", idDestinatie=" + idDestinatie +
                ", data=" + data +
                ", nrLocuriDisponibile=" + nrLocuriDisponibile +
                '}';
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

