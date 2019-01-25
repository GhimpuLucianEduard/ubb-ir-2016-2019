package Models.Validators;

import Models.Student;

public class StudentValidator implements Validator<Student> {

    @Override
    public void validate(Student obj) throws ValidationException {
        String err = "";

        if(obj.getId().compareTo("")==0) {
            err = err.concat("Numarul Matricol al studentului nu poate fi vid! ");
        }

        if(obj.getName().compareTo("")==0) {
            err = err.concat("Numele studentului nu poate fi vid! ");
        }

        if(obj.getEmail().compareTo("")==0) {
            err = err.concat("Email-ul studentului nu poate fi vid! ");
        }

        if(obj.getGroup()<=0) {
            err = err.concat("Grupa invalida! ");
        }

        if(err.length()!=0) {
            throw new ValidationException(err);
        }
    }
}
