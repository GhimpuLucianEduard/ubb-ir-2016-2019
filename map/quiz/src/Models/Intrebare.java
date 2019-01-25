package Models;

public class Intrebare implements HasID<Integer> {

    private int nrIntrebare;
    private String desc;
    private String r1;
    private String r2;
    private String r3;
    private String corect;


    public Intrebare(int nrIntrebare, String desc, String r1, String r2, String r3, String corect) {
        this.nrIntrebare = nrIntrebare;
        this.desc = desc;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.corect = corect;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getCorect() {
        return corect;
    }

    public void setCorect(String corect) {
        this.corect = corect;
    }

    @Override
    public Integer getId() {
        return nrIntrebare;
    }

    @Override
    public void setId(Integer integer) {
        nrIntrebare=integer;
    }

    @Override
    public String toString() {
        return nrIntrebare+";"+desc+";"+r1+";"+r2+";"+r3+";"+corect;
    }
}
