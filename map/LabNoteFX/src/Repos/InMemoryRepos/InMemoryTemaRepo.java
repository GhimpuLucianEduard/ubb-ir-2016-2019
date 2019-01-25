package Repos.InMemoryRepos;

import Models.Tema;
import Models.Validators.Validator;
import Repos.AbstractRepository;

public class InMemoryTemaRepo extends AbstractRepository<Tema,Integer> {
    /**
     * @param vali validatorul specific obiectului de tip Tema
     */
    public InMemoryTemaRepo(Validator<Tema> vali) {
        super(vali);
    }
}
