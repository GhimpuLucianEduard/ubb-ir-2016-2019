package Repos.InMemoryRepos;

import Models.Nota;
import Models.Validators.Validator;
import Repos.AbstractRepository;

public class InMemoryNotaRepo extends AbstractRepository<Nota,String> {

    /**
     * @param vali validatorul specific obiectului de tip Nota
     */
    public InMemoryNotaRepo(Validator<Nota> vali) {
        super(vali);
    }
}
