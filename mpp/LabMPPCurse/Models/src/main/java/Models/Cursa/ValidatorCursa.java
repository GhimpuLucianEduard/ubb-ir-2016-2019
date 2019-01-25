package Models.Cursa;

import Models.ValidationException;
import Models.Validator;

public class ValidatorCursa implements Validator<Cursa> {
    @Override
    public void validate(Cursa entity) throws ValidationException {

        String msg = "";
        if (entity.getNrLocuriDisponibile()< 0 || entity.getNrLocuriDisponibile() > 18)
        {
            msg += "numar locuri invalid";
        }

        if (entity.getIdDestinatie() <= 0)
        {
            msg += "destinatie invalida";
        }

        if (msg.compareTo("") != 0)
        {
            throw new ValidationException(msg);
        }
    }
}
