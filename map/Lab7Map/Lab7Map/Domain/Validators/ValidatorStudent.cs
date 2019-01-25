using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Domain.Validators
{
    class ValidatorStudent : IValidator<Student>
    {
        public void Validate(Student entity)
        {
            string error = null;

            if (entity.id.CompareTo("") == 0)
            {
                error += " Id-ul nu poate fi vid!";
            }

            if (entity.Nume.CompareTo("") == 0)
            {
                error += " Numele nu poate fi vid!";
            }

            if (entity.Grupa <= 0)
            {
                error += " Grupa invalida!";
            }

            if (entity.Prof.CompareTo("") == 0)
            {
                error += " Prof invalid!";
            }

            if (entity.Email.CompareTo("") == 0)
            {
                error += " Email invalid!";
            }

            if (error != null)
            {
                throw new ValidationException(error);
            }
            
        }
    }
}
