package Repos.FileRepos;

import Models.Student;
import Models.Validators.Validator;

public class StudentFileRepo extends AbstractFileRepo<Student,String> {

    /**
     * Constructor care cand se apeleaza se incarca date din fiser in memorie
     *
     * @param vali     validatorul specific obiectului E
     * @param fileName calea catre fisierul din care se citeste/scrie
     */
    public StudentFileRepo(Validator<Student> vali, String fileName) {
        super(vali, fileName);
    }

    @Override
    Student buildEntity(String[] fields) {
        Student st = new Student(fields[0],fields[1],Integer.parseInt(fields[2]),fields[3],fields[4]);
        return st;
    }
}
