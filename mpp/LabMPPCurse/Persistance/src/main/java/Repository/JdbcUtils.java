package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtils {

    private Properties jdbcProps;
    public JdbcUtils(Properties props){
        jdbcProps=props;
    }
    private Connection instance=null;

    private Connection getNewConnection(){
        String driver=jdbcProps.getProperty("curseDB.jdbc.driver");
        String url=jdbcProps.getProperty("curseDB.jdbc.url");
        String user=jdbcProps.getProperty("curseDB.jdbc.user");
        String pass=jdbcProps.getProperty("curseDB.jdbc.pass");

        Connection con=null;
        try {
            Class.forName(driver);

            if (user!=null && pass!=null)
                con= DriverManager.getConnection(url,user,pass);
            else
                con=DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {

            System.out.println("Error loading driver "+e);
        } catch (SQLException e) {

            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){

        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }

        return instance;
    }
}