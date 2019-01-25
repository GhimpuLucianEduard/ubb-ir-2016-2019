package Repos.FileRepos;

import Models.Nota;
import Models.Validators.Validator;

public class NotaFileRepo extends AbstractFileRepo<Nota,String> {
    /**
     * Constructor care cand se apeleaza se incarca date din fiser in memorie
     *
     * @param vali     validatorul specific obiectului E
     * @param fileName calea catre fisierul din care se citeste/scrie
     */
    public NotaFileRepo(Validator<Nota> vali, String fileName) {
        super(vali, fileName);
    }

    @Override
    Nota buildEntity(String[] fields) {
        Nota nt = new Nota(fields[0],Integer.parseInt(fields[1]),Double.parseDouble(fields[2]),Integer.parseInt(fields[3]));
        return nt;
    }
}
