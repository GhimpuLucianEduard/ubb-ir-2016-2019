package Models;

public class Rezervare implements HasId<Integer> {

    private int id;
    private int idClient;
    private int idCursa;
    private int nrLocuri;

    @Override
    public String toString() {
        return "Rezervare{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", idCursa=" + idCursa +
                ", nrLocuri=" + nrLocuri +
                '}';
    }

    public Rezervare(int idClient, int idCursa, int nrLocuri) {
        this.idClient = idClient;
        this.idCursa = idCursa;
        this.nrLocuri = nrLocuri;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdCursa() {
        return idCursa;
    }

    public void setIdCursa(int idCursa) {
        this.idCursa = idCursa;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public Rezervare(int id, int idClient, int idCursa, int nrLocuri) {
        this.id = id;
        this.idClient = idClient;
        this.idCursa = idCursa;
        this.nrLocuri = nrLocuri;
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
