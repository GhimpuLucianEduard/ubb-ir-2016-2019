/*1. Genereaza numere prime mai mici decat un numar natural dat.
     presupunem ca 1 si 0 NU sunt numere prime
*/

#include <stdio.h>


int prim(int n)
/*	
	Function that checks if a integer is prime. Returns boolean values 1 or 0.

	Pre: n - int >= 0
	Post: prim = 1 if n is prime, prim = 0 otherwise 
*/
{
	int prim = 1, div = 2;

	if (n == 1 || n == 0)
		return 0;
	
	if (n % 2 == 0)
		return 0;

	for (div; div*div <= n; div++)
	{
		if (n%div == 0)
			prim = 0;
	}

	return prim;
}

void allNr(int n)
/*
	Functions that prints all prime numbers, smaller than a given n
	Pre: n - int > 0 
	Post: ---

*/
{
	
	int aux = n - 1;

	if (n == 1 || n == 0)
		return;
			
	while (aux)
	{
		if (prim(aux) == 1)
		{
			printf("%d\n", aux);
			aux--;
		}
		else
			aux--;
	}	
}

int showMenu()
/*
	UI function, generates menu
*/
{
	int com = 0;
	printf("Menu:\n 1. Genereaza numere prime mai mici de cat un numar dat  \n 2. Exit \n 3. ??? \n Dati comanda: ");
	scanf("%d", &com);
	return com;
}

int readInt()
/*
	Function used to read a natural number. The function will repeat till a natural number is given.
	Pre: ---
	Post: return int n>=0 
*/
{
	int n = 0;
	
	while (1>0)
	{
		printf("da un nr natural \n");
		scanf("%d", &n);
		if (n < 0)
			printf("am zis natural");
		else
			return n;
	}

}
int main()
{
	int com = 0;
	int quit = 329;
	while (quit)
	{
		com = showMenu();
		switch (com)
		{
		case 1: allNr(readInt()); break;
		case 2: printf("mare greseala..."); quit = 0;  break;
		default: printf("Unknown command!\n");
		}

	}
	
	return 0;	

}/*
	while(!aprox(n,g,prag))
		g=alegereNoua(n,g);

	return g;

	g=1

	aprox(u,g,prag)
		return abs(
		
 
 */

