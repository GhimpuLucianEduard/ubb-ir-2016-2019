using System.Collections;
using System.Collections.Generic;
using System.Linq;
using CurseMPP.Models;

namespace LabMppCurseWPF.NET_Edition
{	
	/// <summary>
	/// clasa care ajuta la handle-uirea unor aspecte din db
	/// precum identity si altele...
	/// </summary>
	public static class DbRepoUtils
	{	
		/// <summary>
		/// Functie care cauta ultimul id dintr-o colectie
		/// si returneaza un id valid pentru adaugare
		/// </summary>
		/// <typeparam name="TId">tipul id-ului</typeparam>
		/// <typeparam name="TE">obiectul continuta</typeparam>
		/// <param name="collection">colectia</param>
		/// <returns>Int id valid</returns>
		public static int getMaxId<TE>(IEnumerable<TE> collection) where TE :HasId<int>
		{
			var rez = 0;

			collection.ToList().ForEach(c =>
			{
				if (c.Id > rez)
				{
					rez = c.Id;
				}
			});

			return rez + 2;
		}
	}
}