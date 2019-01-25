package Repos.DBRepos;

import Models.Nota;
import Models.Student;
import Models.Validators.ValidationException;
import Models.Validators.Validator;

import java.sql.*;
import java.util.Optional;

public class DBRepoNota extends AbstractDBRepo<Nota, String> {

    /**
     * @param vali validatorul specific obiectului E
     */
    public DBRepoNota(Validator<Nota> vali) {
        super(vali);
        getData();
    }

    @Override
    public Optional<Nota> save(Nota entity) throws ValidationException {
        Optional<Nota> aux = super.save(entity);
        if(!aux.isPresent()){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
                CallableStatement cStmt = conn.prepareCall("{CALL addNota(?,?,?,?)}");
                cStmt.setString("idStudent", entity.getIdStudent());
                cStmt.setInt("saptPred", entity.getSaptPredare());
                cStmt.setInt("idTema", entity.getIdTema());
                cStmt.setFloat("valoare",Float.parseFloat(String.valueOf(entity.getValoare())));
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
    public Optional<Nota> delete(String s) {
        Optional<Nota> aux = super.delete(s);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
            CallableStatement cStmt = conn.prepareCall("{CALL deleteNota(?)}");
            cStmt.setString("idNota",s);
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
    public Optional<Nota> update(Nota entity) throws ValidationException {
        Optional<Nota> aux = super.update(entity);
        if(!aux.isPresent()){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
                CallableStatement cStmt = conn.prepareCall("{CALL updateNota(?,?,?,?)}");
                cStmt.setString("idStudent", entity.getIdStudent());
                cStmt.setInt("saptPred", entity.getSaptPredare());
                cStmt.setInt("idTema", entity.getSaptPredare());
                cStmt.setFloat("valoare",Float.parseFloat(String.valueOf(entity.getValoare())));
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
    public void getData() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
            CallableStatement cStmt = conn.prepareCall("{CALL getAllNote}");
            cStmt.execute();
            ResultSet rs1 = cStmt.getResultSet();
            while (rs1.next()) {

                Nota nt = new Nota(rs1.getString("idStudent"),Integer.parseInt(rs1.getString("idTema")),Double.parseDouble(rs1.getString("valoare")),Integer.parseInt(rs1.getString("saptPredare")));
                super.save(nt);
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
}
