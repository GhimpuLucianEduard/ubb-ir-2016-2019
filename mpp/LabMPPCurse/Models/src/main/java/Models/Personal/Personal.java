package Models.Personal;

import Models.HasId;

public class Personal implements HasId<Integer> {

    private int id;
    private String userName;
    private String pass;
    private String nume;
    private String prenume;

    public Personal(String username, String password) {
        this.userName=username;
        this.pass=password;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id=integer;
    }

    public Personal(int id, String userName, String pass, String nume, String prenume) {
        this.id = id;
        this.userName = userName;
        this.pass = pass;
        this.nume = nume;
        this.prenume = prenume;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", pass='" + pass + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
}
