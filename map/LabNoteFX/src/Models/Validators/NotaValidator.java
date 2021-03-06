package Models.Validators;

import Models.Nota;

public class NotaValidator implements Validator<Nota> {

    @Override
    public void validate(Nota obj) throws ValidationException {
        String err="";

        if(obj.getSaptPredare()>14 || obj.getSaptPredare()<=0) {
            err+="Saptamana predarii invalida! ";
        }
        if(obj.getValoare()>10 || obj.getValoare()<=0) {
            err+="Nota invalida! ";
        }
        if(err.length()!=0) {
            throw new ValidationException(err);
        }
    }
}
