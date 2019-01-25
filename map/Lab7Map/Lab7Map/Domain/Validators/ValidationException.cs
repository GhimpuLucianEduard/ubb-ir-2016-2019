using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Domain.Validators
{
    class ValidationException : ApplicationException
    {
        public ValidationException(string message) : base(message)
        {
        }
    }
}
