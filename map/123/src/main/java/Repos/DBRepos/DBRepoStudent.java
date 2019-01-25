package Repos.DBRepos;

import Models.Student;
import Models.Validators.ValidationException;
import Models.Validators.Validator;

import java.sql.*;
import java.util.Optional;

public class DBRepoStudent  extends AbstractDBRepo<Student, String>  {
    /**
     * @param vali validatorul specific obiectului E
     */
    public DBRepoStudent(Validator<Student> vali) {
        super(vali);
        getData();
    }

    @Override
    public Optional<Student> save(Student entity) throws ValidationException {

        Optional<Student> aux = super.save(entity);
        if(!aux.isPresent()){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
                CallableStatement cStmt = conn.prepareCall("{CALL addStudent(?,?,?,?,?)}");
                cStmt.setString("id", entity.getId());
                cStmt.setString("nume", entity.getName());
                cStmt.setString("email", entity.getEmail());
                cStmt.setString("prof", entity.getProf());
                cStmt.setInt("grupa", entity.getGroup());
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
    public Optional<Student> delete(String s) {
        Optional<Student> aux = super.delete(s);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
            CallableStatement cStmt = conn.prepareCall("{CALL deleteStudent(?)}");
            cStmt.setString("idStuent",s);
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
    public Optional<Student> update(Student entity) throws ValidationException {
        Optional<Student> aux = super.update(entity);
        if (!aux.isPresent()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(DBSettings.getConnectionString(), DBSettings.getUsername(), DBSettings.getPass());
                CallableStatement cStmt = conn.prepareCall("{CALL updateStudent(?,?,?,?,?)}");
                cStmt.setString("id", entity.getId());
                cStmt.setString("nume", entity.getName());
                cStmt.setString("email", entity.getEmail());
                cStmt.setString("prof", entity.getProf());
                cStmt.setInt("grupa", entity.getGroup());
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
            CallableStatement cStmt = conn.prepareCall("{CALL getAllStudenti}");
            cStmt.execute();
            ResultSet rs1 = cStmt.getResultSet();
            while (rs1.next()) {

                Student st = new Student(rs1.getString("id"),rs1.getString("nume"),Integer.parseInt(rs1.getString("grupa")),rs1.getString("email"),rs1.getString("profLab"));
                super.save(st);
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
