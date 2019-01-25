using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;

namespace Laborator1AI
{	
	/// <summary>
	/// TBD
	/// </summary>
	public class Tabla : IEquatable<Tabla>, IComparable<Tabla>
	{

		public Coordinates CurrentCoordinates { get; }
		public int marime { get; set; }
		public int[,] tabla;
		private Random random = new Random();
		public Tabla(int marime, int i, int j)
		{
			this.marime = marime;
			tabla = new int[marime*marime, marime*marime];
			CurrentCoordinates = new Coordinates(i,j);
			
		}

		public int[,] getTabla()
		{
			return this.tabla;
		}

		public void PrintTabla()
		{
			for (int i = 0; i < marime*marime; i++)
			{
				for (int j = 0; j < marime*marime; j++)
				{
					Console.Write(tabla[i, j]);
				}
				Console.WriteLine();
			}
		}

		public void populeazaTabla(Tabla tata)
		{
			for (int i = 0; i < marime * marime; i++)
			{
				for (int j = 0; j < marime * marime; j++)
				{
					this.tabla[i, j] = tata.getTabla()[i, j];
				}
				
			}
		}

		public void populeazaRandom()
		{

			tabla[0, 2] = 4;
			tabla[0, 4] = 1;
			tabla[0, 6] = 5;


			tabla[1, 1] = 1;
			tabla[1, 4] = 2;
			tabla[1, 7] = 3;

			tabla[2, 0] = 5;
			tabla[2, 3] = 4;
			tabla[2, 5] = 3;
			tabla[2, 8] = 2;

			tabla[3, 2] = 2;
			tabla[3, 6] = 6;

			tabla[4, 0] = 7;
			tabla[4, 1] = 8;
			tabla[4, 4] = 9;
			tabla[4, 7] = 4;
			tabla[4, 8] = 5;

			tabla[5, 2] = 5;
			tabla[5, 6] = 7;


			tabla[6, 0] = 9;
			tabla[6, 3] = 8;
			tabla[6, 5] = 1;
			tabla[6, 8] = 6;

			tabla[7, 1] = 7;
			tabla[7, 4] = 6;
			tabla[7, 7] = 5;

			tabla[8, 2] = 8;
			tabla[8, 4] = 4;
			tabla[8, 6] = 9;

			CurrentCoordinates.i = 0;
			CurrentCoordinates.j = 0;


//			tabla[0, 0] = 1;
//			tabla[0, 1] = 4;
//			tabla[0, 2] = 3;
//			tabla[0, 3] = 2;
//
//			tabla[1, 0] = 3;
//			tabla[1, 1] = 2;
//			tabla[1, 2] = 4;
//			tabla[1, 3] = 1;
//
//			tabla[2, 0] = 4;
//			tabla[2, 1] = 1;
//			tabla[2, 2] = 2;
//			tabla[2, 3] = 3;
//
//			tabla[3, 0] = 2;
//			tabla[3, 1] = 3;
//			CurrentCoordinates.i = 0;
//			CurrentCoordinates.j = 1;

		}

		public bool isValidSudoku()
		{
			for (var i = 0; i < marime * marime; i++)
			{
				var hashtableRow = new Dictionary<int, bool>();
				var hashtableColumn = new Dictionary<int, bool>();
				var hashtableSquare = new Dictionary<int, bool>();

				for (var j = 0; j < marime * marime; j++)
				{
					var columnValue = tabla[i,j];
					var rowValue = tabla[j,i];
					var squareValue = tabla[marime * (i / marime) + j / marime,marime * (i % marime) + j % marime];
					if (columnValue != 0 && hashtableColumn.ContainsKey(columnValue))
					{
						return false;
					}

					hashtableColumn[columnValue] = true;

					if (rowValue != 0 && hashtableRow.ContainsKey(rowValue))
					{
						return false;
					}

					hashtableRow[rowValue] = true;

					if (squareValue != 0 && hashtableSquare.ContainsKey(squareValue))
					{
						return false;
					}

					hashtableSquare[squareValue] = true;
				}
			}

			return true;
		}

		public class Coordinates
		{
			public Coordinates(int i, int j)
			{
				this.i = i;
				this.j = j;
			}

			public int i  { get; set; }
			public int j { get; set; }
		}

		public void addElement(int elem)
		{
			tabla[CurrentCoordinates.i, CurrentCoordinates.j] = elem;
			increaseCurrentCoordinates();
		}

		public void increaseCurrentCoordinates()
		{
			for (int i = CurrentCoordinates.i; i < marime*marime; i++)
			{
				for (int j = 0 ; j < marime*marime; j++)
				{
					int aux = tabla[i, j];
					if (aux==0)
					{
						CurrentCoordinates.i = i;
						CurrentCoordinates.j = j;
						return;
					}
				}
			}

		}

		public bool checkIfFull()
		{
			int suma = 0 ;
			for (int i = 0; i < marime * marime; i++)
			{
				for (int j = 0; j < marime * marime; j++)
				{
					if (tabla[i, j] != 0)
					{
						suma++;
					}
				}

			}

			return (suma == marime*marime*marime*marime);
		}

		public bool checkIfWin()
		{
			return isValidSudoku() && checkIfFull();
		}

		public bool Equals(Tabla other)
		{
			for (int i = 0; i < marime * marime; i++)
			{
				for (int j = 0; j < marime * marime; j++)
				{
					if (this.tabla[i, j] != other.getTabla()[i, j])
					{
						return false;
					}
				}
			}

			return true;
		}

		public int CompareTo(Tabla other)
		{
			var suma1 = 0;
			var suma2 = 0;
			for (int i = 0; i < marime * marime; i++)
			{
				for (int j = 0; j < marime * marime; j++)
				{
					if (this.tabla[i, j] != other.getTabla()[i, j])
					{
						if (tabla[i, j] != 0)
						{
							suma1++;
						}
						if (other.getTabla()[i, j] != 0)
						{
							suma2++;
						}
					}
				}

			}

			return suma2 - suma1;

		}

		public override int GetHashCode()
		{
			var code = 0;
			var suma = 0;

			for (int i = 0; i < marime * marime; i++)
			{
				for (int j = 0; j < marime * marime; j++)
				{
					suma += tabla[i, j];
					if (tabla[i, j] != 0)
					{
						code += i;
						code += j;
					}
				}
			}

			return code + suma;
		}
	}

}