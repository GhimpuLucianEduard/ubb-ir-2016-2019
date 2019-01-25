package Models;

public class Loc {
    private int nrCurent;
    private String NumeClient;
    private String PrenumeClient;

    public int getNrCurent() {
        return nrCurent;
    }

    public void setNrCurent(int nrCurent) {
        this.nrCurent = nrCurent;
    }

    public String getNumeClient() {
        return NumeClient;
    }

    public void setNumeClient(String numeClient) {
        NumeClient = numeClient;
    }

    public String getPrenumeClient() {
        return PrenumeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        PrenumeClient = prenumeClient;
    }

    public Loc(int nrCurent, String numeClient, String prenumeClient) {
        this.nrCurent = nrCurent;
        NumeClient = numeClient;
        PrenumeClient = prenumeClient;
    }
}
