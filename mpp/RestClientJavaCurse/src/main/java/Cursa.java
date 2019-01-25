import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.corba.se.spi.ior.Identifiable;
import org.omg.CORBA_2_3.portable.OutputStream;

import java.io.Serializable;
import java.util.Date;


public class Cursa implements Serializable{

    private int id;
    private int idDestinatie;
    @JsonProperty("locPlecare")
    private String locPlecare;
    private int nrLocuriDisponibile;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @JsonProperty("dataPlecare")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date  data;
    public String getLocPlecare() {
        return locPlecare;
    }

    public void setLocPlecare(String locPlecare) {
        this.locPlecare = locPlecare;
    }

    public Cursa() {

    }

    public int getIdDestinatie() {
        return idDestinatie;
    }

    public void setIdDestinatie(int idDestinatie) {
        this.idDestinatie = idDestinatie;
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
                ", data='" + data + '\'' +
                ", locPlecare='" + locPlecare + '\'' +
                ", nrLocuriDisponibile=" + nrLocuriDisponibile +
                '}';
    }

    public int getId() {
        return this.id;
    }

    public void setId(Integer integer) {
        this.id=integer;
    }

}