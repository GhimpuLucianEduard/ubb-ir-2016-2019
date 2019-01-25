using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Domain.Validators
{
    class ValidatorNota : IValidator<Nota>
    {
        public void Validate(Nota entity)
        {
            string error = null;
            if(entity.IdStudent.CompareTo("")==0)
            {
                error += "Id student nu paote fi vid!";
            }

            if (entity.IdTema <= 0)
            {
                error += "Idul temei nu poate fi negativ!";
            }

            if (entity.Valoare <= 0 || entity.Valoare > 10)
            {
                error += "Nota nu poate fi negativa sau mai mare ca zece!";
            }

            if (error != null)
            {
                throw new ValidationException(error);
            }
        }

    }
}
