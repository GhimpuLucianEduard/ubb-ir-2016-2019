package dto;

import java.io.Serializable;

public class RezervareDTO implements Serializable{
    private int idCursa;
    private String Nume;
    private String Prenume;

    public RezervareDTO(int idCursa, String nume, String prenume, int nrLocuri) {
        this.idCursa = idCursa;
        Nume = nume;
        Prenume = prenume;
        this.nrLocuri = nrLocuri;
    }

    public int getIdCursa() {

        return idCursa;
    }

    public void setIdCursa(int idCursa) {
        this.idCursa = idCursa;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    private int nrLocuri;
}
