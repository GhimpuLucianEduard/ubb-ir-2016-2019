using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Domain.Validators
{
    interface IValidator<T>
    {
        void Validate(T entity);
    } 
}
