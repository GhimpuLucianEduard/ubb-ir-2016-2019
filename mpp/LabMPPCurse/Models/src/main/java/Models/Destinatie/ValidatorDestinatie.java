package Models.Destinatie;

import Models.ValidationException;
import Models.Validator;

public class ValidatorDestinatie implements Validator<Destinatie>{


    @Override
    public void validate(Destinatie entity) throws ValidationException {

        String msg = "";
        if (entity.getNume().compareTo("") == 0)
        {
            msg += "numele e vid";
        }

        if (msg.compareTo("") != 0)
        {
            throw new ValidationException(msg);
        }
    }
}
