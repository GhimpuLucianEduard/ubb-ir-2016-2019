package dto;

import java.io.Serializable;

public class PersonalDTO  implements Serializable {

    private String userName;
    private String pass;

    public PersonalDTO(String username, String password) {
        this.userName=username;
        this.pass=password;
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
}

