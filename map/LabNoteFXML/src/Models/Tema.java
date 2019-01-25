package Models;

public class Tema implements HasID<Integer> {

    private int id;
    private String info;
    private int deadline;

    public Tema(int id, String info, int deadline) {
        this.id = id;
        this.info = info;
        this.deadline = deadline;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return id + ";" + info + ";" + deadline + ";";
    }
}
