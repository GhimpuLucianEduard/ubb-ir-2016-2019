public class ValidatorPersonal implements Validator<Personal> {
    @Override
    public void validate(Personal entity) throws ValidationException {
        String msg = "";

        if (entity.getNume().compareTo("") == 0)
        {
            msg += "nume vid";
        }

        if (entity.getPrenume().compareTo("") == 0)
        {
            msg+="prenumele vid";
        }

        if (entity.getUserName().compareTo("") == 0)
        {
            msg += "username vid";
        }

        if (entity.getPass().compareTo("") == 0)
        {
            msg += "parola vida";
        }

        if (msg.compareTo("") != 0)
        {
            throw new ValidationException(msg);
        }
    }
}
