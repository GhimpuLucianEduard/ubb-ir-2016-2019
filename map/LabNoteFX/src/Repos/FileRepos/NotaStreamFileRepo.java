package Repos.FileRepos;

import Models.Nota;
import Models.Validators.Validator;

public class NotaStreamFileRepo extends AbstractStreamFileRepo<Nota,String> {
    /**
     * @param vali     validatorul specific obiectului E
     * @param fileName
     */
    public NotaStreamFileRepo(Validator<Nota> vali, String fileName) {
        super(vali, fileName);
    }

    @Override
    Nota buildEntity(String[] fields) {
        Nota nt = new Nota (fields[0],Integer.parseInt(fields[1]),Double.parseDouble(fields[2]),Integer.parseInt(fields[3]));
        return nt;
    }
}
