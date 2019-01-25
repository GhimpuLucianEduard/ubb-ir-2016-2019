package Repos.FileRepos;

import Models.Student;
import Models.Validators.Validator;

public class StudentStreamFileRepo extends AbstractStreamFileRepo<Student,String> {


    /**
     * @param vali     validatorul specific obiectului E
     * @param fileName
     */
    public StudentStreamFileRepo(Validator<Student> vali, String fileName) {
        super(vali, fileName);
    }

    @Override
    Student buildEntity(String[] fields) {
        Student st = new Student(fields[0],fields[1],Integer.parseInt(fields[2]),fields[3],fields[4]);
        return st;
    }
}
