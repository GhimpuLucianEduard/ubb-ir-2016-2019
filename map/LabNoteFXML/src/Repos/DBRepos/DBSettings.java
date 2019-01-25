package Repos.DBRepos;

public class DBSettings {

    public static String username;
    public static String pass;
    public static String connectionString;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DBSettings.username = username;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        DBSettings.pass = pass;
    }

    public static String getConnectionString() {
        return connectionString;
    }

    public static void setConnectionString(String connectionString) {
        DBSettings.connectionString = connectionString;
    }
}
