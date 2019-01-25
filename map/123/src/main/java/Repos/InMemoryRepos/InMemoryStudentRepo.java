package Repos.InMemoryRepos;

import Models.Student;
import Models.Validators.Validator;
import Repos.AbstractRepository;

public class InMemoryStudentRepo extends AbstractRepository<Student, String> {

    /**
     * @param vali validatorul specific obiectului de tip Student
     */
    public InMemoryStudentRepo(Validator<Student> vali) {
        super(vali);
    }
}
