package Models.Client;

import Models.ValidationException;
import Models.Validator;

public class ValidatorClient implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ValidationException {

        String msg = "";

        if (entity.getNume().compareTo("")==0)
        {
            msg += "Nume vid";
        }

        if (entity.getPrenume().compareTo("")==0)
        {
            msg += "Prenume vid";
        }

        if (msg.compareTo("") != 0)
        {
            throw new ValidationException(msg);
        }
    }
}
