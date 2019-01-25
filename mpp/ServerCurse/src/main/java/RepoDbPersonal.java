import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepoDbPersonal implements IRepository<Integer,Personal> {

    private JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public RepoDbPersonal(Properties props) {
        logger.info("Initializing personal Repo with properties: {} ",props);
        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Personal")) {
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    logger.traceExit(result.getInt("SIZE"));
                    return result.getInt("SIZE");
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }

        return 0;
    }

    @Override
    public void save(Personal entity) {
        logger.traceEntry("saving cpersonal {} ",entity);
        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Personal values (?,?,?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getUserName());
            preStmt.setString(3, entity.getPass());
            preStmt.setString(4,entity.getNume());
            preStmt.setString(4,entity.getPrenume());

            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }

        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("deleting personal with {}",integer);
        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Personal where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }

        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Personal entity) {

        logger.traceEntry("upating cursa with {}",integer);
        Connection con=jdbcUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Personal set UserName=?,Password=?,Nume=?,Prenume=? WHERE Id=?")){
            preStmt.setInt(5,entity.getId());
            preStmt.setString(1,entity.getUserName());
            preStmt.setString(2, entity.getPass());
            preStmt.setString(3,entity.getNume());
            preStmt.setString(4,entity.getPrenume());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }

        logger.traceExit();
    }

    @Override
    public Personal findOne(Integer integer) {
        logger.traceEntry("finding personal with id {} ",integer);
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
                    logger.traceExit(p);
                    return p;
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit("No cursa found with id {}", integer);

        return null;
    }

    @Override
    public Iterable<Personal> findAll() {
        logger.traceEntry();
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
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(pers);

        return pers;
    }
}
