﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CurseMPP.Models
{
	public interface HasId<T>
	{
		T Id { get; set; }
	}
}
