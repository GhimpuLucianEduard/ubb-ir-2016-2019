package Repository;

import Models.Rezervare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepoDbRezervari implements IRepository<Integer, Rezervare> {
    private JdbcUtils jdbcUtils;


    public RepoDbRezervari(Properties props) {

        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Rezervari")) {
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
    public void save(Rezervare entity) {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Rezervari values (?,?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setInt(2,entity.getIdClient());
            preStmt.setInt(3,entity.getIdCursa());
            preStmt.setInt(4,entity.getNrLocuri());

            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public void delete(Integer integer) {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Rezervari where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public void update(Integer integer, Rezervare entity) {


        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Rezervari set IdClient=?, IdCursa=?, NrLocuri=? WHERE Id=?")){
            preStmt.setInt(4,entity.getId());
            preStmt.setInt(1,entity.getIdClient());
            preStmt.setInt(2,entity.getIdCursa());
            preStmt.setInt(3,entity.getNrLocuri());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public Rezervare findOne(Integer integer) {

        Connection con=jdbcUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from Rezervari where Id=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("Id");
                    int idCl = result.getInt("IdClient");
                    int idCu = result.getInt("IdCursa");
                    int nr = result.getInt("NrLocuri");
                    Rezervare r = new Rezervare(id,idCl,idCu,nr);

                    return r;
                }
            }
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }


        return null;
    }

    @Override
    public Iterable<Rezervare> findAll() {

        Connection con=jdbcUtils.getConnection();
        List<Rezervare> rez=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Rezervari")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("Id");
                    int idCl = result.getInt("IdClient");
                    int idCu = result.getInt("IdCursa");
                    int nr = result.getInt("NrLocuri");
                    Rezervare r = new Rezervare(id,idCl,idCu,nr);
                    rez.add(r);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }


        return rez;
    }



}
