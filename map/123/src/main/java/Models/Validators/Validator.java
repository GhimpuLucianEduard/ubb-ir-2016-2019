package Models.Validators;

public interface Validator<E> {

    public void validate(E obj) throws ValidationException;
}
