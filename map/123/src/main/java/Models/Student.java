package Models;

public class Student implements HasID<String> {

    private String id;
    private String name;
    private int grupa;
    private String email;
    private String prof;

    public Student(String id, String name, int grupa, String email, String prof) {

        this.id = id;
        this.name = name;
        this.grupa = grupa;
        this.email = email;
        this.prof = prof;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String s) {
        this.id = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return grupa;
    }

    public void setGroup(int group) {
        this.grupa = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    @Override
    public String toString() {
        return id + ";" + name + ";" + grupa + ";" + email + ";" + prof+ ";";
    }
}
