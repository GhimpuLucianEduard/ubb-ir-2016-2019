package Repos.FileRepos;

import Models.Tema;
import Models.Validators.Validator;

public class TemaStreamFileRepo extends AbstractStreamFileRepo<Tema,Integer> {
    /**
     * @param vali     validatorul specific obiectului E
     * @param fileName
     */
    public TemaStreamFileRepo(Validator<Tema> vali, String fileName) {
        super(vali, fileName);
    }

    @Override
    Tema buildEntity(String[] fields) {
        Tema tm = new Tema(Integer.parseInt(fields[0]),fields[1],Integer.parseInt(fields[2]));
        return tm;
    }
}
