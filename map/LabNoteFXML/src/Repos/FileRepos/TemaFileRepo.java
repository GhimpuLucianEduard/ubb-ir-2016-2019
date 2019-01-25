package Repos.FileRepos;

import Models.Tema;
import Models.Validators.Validator;

public class TemaFileRepo extends AbstractFileRepo<Tema,Integer> {

    /**
     * Constructor care cand se apeleaza se incarca date din fiser in memorie
     *
     * @param vali     validatorul specific obiectului E
     * @param fileName calea catre fisierul din care se citeste/scrie
     */
    public TemaFileRepo(Validator<Tema> vali, String fileName) {
        super(vali, fileName);
    }

    @Override
    Tema buildEntity(String[] fields) {
        Tema tm = new Tema(Integer.parseInt(fields[0]),fields[1],Integer.parseInt(fields[2]));
        return tm;
    }
}
