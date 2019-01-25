package Models.Validators;

import Models.Student;

public class StudentValidator implements Validator<Student> {

    @Override
    public void validate(Student obj) throws ValidationException {
        String err = "";

        if(obj.getId().compareTo("")==0) {
            err = err.concat("Id student vid! ");
        }

        if(obj.getName().compareTo("")==0) {
            err = err.concat("Nume student vid! ");
        }

        if(obj.getEmail().compareTo("")==0) {
            err = err.concat("Email student vid! ");
        }

        if(obj.getGroup()<=0) {
            err = err.concat("Grupa invalida! ");
        }

        if(err.length()!=0) {
            throw new ValidationException(err);
        }
    }
}
