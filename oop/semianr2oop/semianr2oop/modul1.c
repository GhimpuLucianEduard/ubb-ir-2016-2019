#define _CRTDBG_MAP_ALLOC
#include <stdio.h>
#include <stdlib.h>
#include <crtdbg.h>



int** creazaBrad(int inaltime) 
{	
	int i = 0, creanga = 1;
	int** brad = malloc(sizeof(int*)*inaltime);
	for (i = 0; i < inaltime; i++)
	{
		brad[i] = malloc(sizeof(int*)*creanga);
		creanga = creanga * 2;
		if (creanga >= 8)
			creanga = 1;
	}

	return brad;
}


void impodobeste(int** brad, int inaltime)
{
	int i = 0, j=0, creanga = 1;
	
	for (i = 0; i < inaltime; i++)
	{
		for (j = 0; j < creanga; j++)
		{
			printf("baga nr:\n");
			scanf("%d", &brad[i][j] );
		}
		creanga = creanga * 2;
		if (creanga >= 8)
			creanga = 1;
	}
}

void tipareste(int** brad, int inaltime)
{
	int i = 0, j = 0, creanga = 1;

	for (i = 0; i < inaltime; i++)
	{
		for (j = 0; j < creanga; j++)
		{
			printf("%d",brad[i][j]);
			
		}
		printf("\n");
		
		creanga = creanga * 2;
		if (creanga >= 8)
			creanga = 1;
	}
}


void distruge(int** brad, int inaltime)
{
	int i;
	for (i = 0; i < inaltime; i++)
	{
		free(brad[i]);
	}
		

	free(brad);
}

int main()
{	

	int numar;
	printf("baga u  numar\n");
	scanf("%d", &numar);
	int** bradul = creazaBrad(numar);
	impodobeste(bradul, numar);
	tipareste(bradul, numar);
	//distruge(bradul, numar);

	return 0;
}