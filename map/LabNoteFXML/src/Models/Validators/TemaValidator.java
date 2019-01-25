package Models.Validators;

import Models.Tema;

public class TemaValidator implements Validator<Tema> {

    @Override
    public void validate(Tema obj) throws ValidationException {
        String err="";

        if(obj.getId()<=0 || obj.getId()>14) {
            err = err.concat("Id-ul temei trebuie sa fie intre 1 si 14! ");
        }

        if(obj.getDeadline()<=0 || obj.getDeadline()>14) {
            err = err.concat("Deadline-ul nu pate fi mai mic de cat 0 sau mai mare de cat 14! ");
        }

        if(obj.getInfo().compareTo("")==0) {
            err = err.concat("Descriere nu trebuie sa fie goala! ");
        }

        if(err.length()!=0) {
            throw new ValidationException(err);
        }
    }
}
