using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Domain.Validators
{
    class ValidatorTema : IValidator<Tema>
    {
        public void Validate(Tema entity)
        {
            string error = null;
            if (entity.id <= 0)
            {
                error += "Id invalid!";
            }

            if (entity.Info.CompareTo("") == 0)
            {
                error += "Descrierea nu poate fi vida!";
            }

            if (entity.Deadline <= 0 || entity.Deadline > 14)
            {
                error += "Deadline-ul unei tema trebuie sa fie mai mare ca 0 si mai mic ca 15!";
            }

            if (error != null)
            {
                throw new ValidationException(error);
            }
        }
    }
}
