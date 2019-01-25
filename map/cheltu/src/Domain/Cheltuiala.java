package Domain;

import java.util.Date;

public class Cheltuiala implements HasID<String> {

    private String idChelt;
    private TipCheltuiala tip;
    private double suma;
    private String desc;
    private String efectuatDe;
    private Date data;

    public Cheltuiala(TipCheltuiala tip, double suma, String desc, String efectuatDe, Date data) {
        this.tip = tip;
        this.suma = suma;
        this.desc = desc;
        this.efectuatDe = efectuatDe;
        this.data = data;
        this.idChelt=efectuatDe+data.toString()+suma;
    }

    @Override
    public String getId() {
        return idChelt;
    }

    @Override
    public void setId(String integer) {
        idChelt=integer;
    }


    public TipCheltuiala getTip() {
        return tip;
    }

    public void setTip(TipCheltuiala tip) {
        this.tip = tip;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEfectuatDe() {
        return efectuatDe;
    }

    public void setEfectuatDe(String efectuatDe) {
        this.efectuatDe = efectuatDe;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return tip+";"+suma+";"+desc+";"+efectuatDe+";"+data+";";
    }
}
