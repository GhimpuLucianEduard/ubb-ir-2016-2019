package Repository;



import Models.Client.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepoDbClienti implements IRepository<Integer,Client> {
    private JdbcUtils jdbcUtils;

    public RepoDbClienti(Properties props) {
        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {
        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Clienti")) {
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
    public void save(Client entity) {
        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Clienti values (?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getNume());
            preStmt.setString(3, entity.getPrenume());

            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public void delete(Integer integer) {
        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Clienti where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){ ;
            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public void update(Integer integer, Client entity) {
        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Clienti set Nume=?, Prenume=? WHERE Id=?")){
            preStmt.setInt(3,entity.getId());
            preStmt.setString(1,entity.getNume());
            preStmt.setString(2, entity.getPrenume());

            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public Client findOne(Integer integer) {
        Connection con=jdbcUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from Clienti where id=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("Id");
                    String nume = result.getString("Nume");
                    String prenume = result.getString("Prenume");
                    Client c = new Client(id,nume,prenume);
                    return c;
                }
            }
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
        }

        return null;
    }

    @Override
    public Iterable<Client> findAll() {
        Connection con=jdbcUtils.getConnection();
        List<Client> cls=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Clienti")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("Id");
                    String nume = result.getString("Nume");
                    String prenume = result.getString("Prenume");
                    Client c = new Client(id,nume,prenume);
                    cls.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }

        return cls;
    }
}
