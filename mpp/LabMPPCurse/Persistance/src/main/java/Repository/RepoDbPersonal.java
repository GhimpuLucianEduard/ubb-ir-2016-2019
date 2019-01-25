package Repository;

import Models.Personal.Personal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepoDbPersonal implements IRepository<Integer,Personal> {

    private JdbcUtils jdbcUtils;


    public RepoDbPersonal(Properties props) {

        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Personal")) {
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {

                    return result.getInt("SIZE");
                }
            }
        }catch(SQLException ex){

            System.out.println("Error DB "+ex);
        }

        return 0;
    }

    @Override
    public void save(Personal entity) {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Personal values (?,?,?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getUserName());
            preStmt.setString(3, entity.getPass());
            preStmt.setString(4,entity.getNume());
            preStmt.setString(4,entity.getPrenume());

            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public void delete(Integer integer) {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Personal where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public void update(Integer integer, Personal entity) {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Personal set UserName=?,Password=?,Nume=?,Prenume=? WHERE Id=?")){
            preStmt.setInt(5,entity.getId());
            preStmt.setString(1,entity.getUserName());
            preStmt.setString(2, entity.getPass());
            preStmt.setString(3,entity.getNume());
            preStmt.setString(4,entity.getPrenume());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }


    }

    @Override
    public Personal findOne(Integer integer) {

        Connection con=jdbcUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from Personal where id=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("Id");
                    String user = result.getString("UserName");
                    String pass = result.getString("Password");
                    String nume = result.getString("Nume");
                    String prenume = result.getString("Prenume");
                    Personal p = new Personal(id,user,pass,nume,prenume);

                    return p;
                }
            }
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }


        return null;
    }

    @Override
    public Iterable<Personal> findAll() {

        Connection con=jdbcUtils.getConnection();
        List<Personal> pers=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Personal")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("Id");
                    String user = result.getString("UserName");
                    String pass = result.getString("Password");
                    String nume = result.getString("Nume");
                    String prenume = result.getString("Prenume");
                    Personal p = new Personal(id,user,pass,nume,prenume);
                    pers.add(p);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }

        return pers;
    }
}
