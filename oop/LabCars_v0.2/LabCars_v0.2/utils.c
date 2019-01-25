#include "car.h"
#include "stdlib.h"

void showStatus(Car* c)
{
	if (getStatus(c) == 0)
		printf("Not Rented \n");
	else
		printf("Rented \n");
}

void printCar(Car* c)
{
	printf("%s %s %s ", getIdPlate(c), getModel(c), getType(c));
	printf(" ");
	showStatus(c);
	printf("\n");
}

int validate(Car* c)
{
	if (getIdPlate(c) == " ")
	{
		return 1;
	}
		
	if (getModel(c) == " ")
	{
		return 2;
	}

		
	if (getType(c) == " ")
	{
		return 3;
	}
		

	if (getStatus(c) != 0 && getStatus(c) != 1)
	{
		return 4;
	}
		
	return 0;
}

void errorMsg(int n)
{
	if(n==1)
		printf("Id plate cant be void\n");

	if(n==2)
		printf("Model cant be void\n");

	if(n==3)
		printf("Type cant be void\n");

	if(n==4)
		printf("status can only be 1 (Rented) or 0 (Not Rented)\n");

	
		
}

