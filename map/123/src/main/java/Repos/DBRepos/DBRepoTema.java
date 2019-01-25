package Repos.DBRepos;

import Models.Tema;
import Models.Validators.ValidationException;
import Models.Validators.Validator;

import java.sql.*;
import java.util.Optional;

public class DBRepoTema extends AbstractDBRepo<Tema, Integer> {
    /**
     * @param vali validatorul specific obiectului E
     */
    public DBRepoTema(Validator<Tema> vali) {
        super(vali);
        getData();
    }

    @Override
    public void getData() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
            CallableStatement cStmt = conn.prepareCall("{CALL getAllTeme}");
            cStmt.execute();
            ResultSet rs1 = cStmt.getResultSet();
            while (rs1.next()) {

                Tema tm = new Tema(Integer.parseInt(rs1.getString("id")),rs1.getString("info"),Integer.parseInt(rs1.getString("deadline")));
                super.save(tm);
            }
            rs1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Tema> save(Tema entity) throws ValidationException {
        Optional<Tema> aux = super.save(entity);
        if(!aux.isPresent()){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
                CallableStatement cStmt = conn.prepareCall("{CALL addTema(?,?,?)}");
                cStmt.setInt("id", entity.getId());
                cStmt.setInt("deadline", entity.getDeadline());
                cStmt.setString("info", entity.getInfo());
                cStmt.execute();
                conn.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aux;
    }

    @Override
    public Optional<Tema> delete(Integer integer) {
        Optional<Tema> aux = super.delete(integer);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
            CallableStatement cStmt = conn.prepareCall("{CALL deleteTema(?)}");
            cStmt.setInt("idTema",integer);
            cStmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return aux;
    }

    @Override
    public Optional<Tema> update(Tema entity) throws ValidationException {
        Optional<Tema> aux = super.update(entity);
        if(!aux.isPresent()){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
                CallableStatement cStmt = conn.prepareCall("{CALL updateTema(?,?,?)}");
                cStmt.setInt("id", entity.getId());
                cStmt.setInt("deadline", entity.getDeadline());
                cStmt.setString("info", entity.getInfo());
                cStmt.execute();
                conn.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aux;
    }
}
