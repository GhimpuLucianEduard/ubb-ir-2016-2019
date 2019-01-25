package Repository;

import Models.Destinatie.Destinatie;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepoDbDestinatie implements IRepository<Integer,Destinatie>{

    private JdbcUtils jdbcUtils;


    public RepoDbDestinatie(Properties props) {

        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Destinatii")) {
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
    public void save(Destinatie entity) {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Destinatii values (?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getNume());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }


    }

    @Override
    public void delete(Integer integer) {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Destinatii where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public void update(Integer integer, Destinatie entity) {


        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Destinatii set Nume=? WHERE Id=?")){
            preStmt.setInt(2,entity.getId());
            preStmt.setString(1,entity.getNume());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public Destinatie findOne(Integer integer) {

        Connection con=jdbcUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from Destinatii where id=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("Id");
                    String nume = result.getString("Nume");
                    Destinatie d = new Destinatie(id,nume);

                    return d;
                }
            }
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }


        return null;
    }

    @Override
    public Iterable<Destinatie> findAll() {

        Connection con=jdbcUtils.getConnection();
        List<Destinatie> tasks=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Destinatii")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("Id");
                    String nume = result.getString("Nume");
                    Destinatie d = new Destinatie(id,nume);
                    tasks.add(d);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }


        return tasks;
    }
}
