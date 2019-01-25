package Repository;

import Models.Cursa.Cursa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepoDBCurse implements IRepository<Integer,Cursa> {
    private JdbcUtils jdbcUtils;


    public RepoDBCurse(Properties props) {

        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Curse")) {
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
    public void save(Cursa entity) {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Curse values (?,?,?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setInt(2,entity.getIdDestinatie());
            preStmt.setString(3, entity.getData());
            preStmt.setInt(4,entity.getNrLocuriDisponibile());
            preStmt.setString(5,entity.getLocatiePlecare());

            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public void delete(Integer integer) {

        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Curse where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public void update(Integer integer, Cursa entity) {


        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Curse set Destinatie=?, DataPlecare=?, NrLocuri=?, Plecare=? WHERE Id=?")){
            preStmt.setInt(5,entity.getId());
            preStmt.setInt(1,entity.getIdDestinatie());
            preStmt.setString(2, entity.getData());
            preStmt.setInt(3,entity.getNrLocuriDisponibile());
            preStmt.setString(4,entity.getLocatiePlecare());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public Cursa findOne(Integer integer) {

        Connection con=jdbcUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from Curse where id=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("Id");
                    int idD = result.getInt("Destinatie");
                    String data = result.getString("DataPlecare");
                    int nr = result.getInt("NrLocuri");
                    String locatiePlecare = result.getString("Plecare");
                    Cursa d = new Cursa(id,idD,data, locatiePlecare, nr);

                    return d;
                }
            }
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }


        return null;
    }

    @Override
    public Iterable<Cursa> findAll() {

        Connection con=jdbcUtils.getConnection();
        List<Cursa> curse=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Curse")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("Id");
                    int idD = result.getInt("Destinatie");
                    String data = result.getString("DataPlecare");
                    int nr = result.getInt("NrLocuri");
                    String locatiePlecare = result.getString("Plecare");
                    Cursa d = new Cursa(id,idD,data, locatiePlecare, nr);
                    curse.add(d);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }

        return curse;
    }
}
