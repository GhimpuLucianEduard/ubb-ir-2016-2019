package sample.domain;

import java.sql.Date;

public class Mesaj {
    private String mesaj;
    private String data;
    private String exp;
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Mesaj(String mesaj, String data, String exp) {
        this.mesaj = mesaj;
        this.data = data;
        this.exp = exp;
        this.id=exp+data+mesaj;


    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getData() {
        return data;
    }

//    public String setData(String data) {
//        this.data = data;
//    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return exp+";"+mesaj+";"+data+";";
    }
}
