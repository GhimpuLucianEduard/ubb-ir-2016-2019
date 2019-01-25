#include "Car.h"
#include "stdlib.h"

void showStatus(Car* c)
{
	if (getRent(c) == 0)
		printf("Not Rented \n");
	else
		printf("Rented \n");
}



