#include "CarListD.h"
#include "CarRental.h"
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <string.h>

CarRental* createCarRental()
{
	CarRental* rez = malloc(sizeof(CarRental));
	rez->carList = createDynamicList();
	return rez;
}

void destroyCarRental(CarRental* cr)
{
	int i = 0;
	for (i = 0; i < getSize(cr->carList); i++)
	{
		destroy(getCarPointer(cr->carList, i));
	}
	destroyDynamicList(cr->carList);
	free(cr);
}

Car* addCar(CarRental* cr, char* licensePlate, char* producer, char* model, char* type, int rent )
{
	Car* c = createCar(licensePlate, producer, model, type, rent);
	addElement(cr->carList, c);
	return c;

}

int findCar(CarRental* cr, char* licensePlateTBF)
{
	int i = 0;
	for (i = 0; i < getSize(cr->carList); i++)
	{
		if ( strcmp(licensePlateTBF, getLicensePlate(getCarPointer(cr->carList, i))) == 0)
			return 1;
		else
			return -1;

	}
}

/*
void deleteCar(CarRental* cr, char* licensePlateTBF)
{
	Car *c = findCar(cr, licensePlateTBF);

	destroy(c);

}
*/

void carUpdate(CarRental* cr, char* oldLicensePlate, char* newLicensePlate, char* producer, char* model, char* type, int rent)
{
	Car *c = findCar(cr, oldLicensePlate);

	
	strcpy(c->licensePlate, newLicensePlate);
	strcpy(c->producer, producer);
	strcpy(c->model, model);
	strcpy(c->type, type);
	c->rent = rent;

}

/*
void showByCrit(CarRental* cr, char* crit, char* crit2)
{
	int i = 0;
	for (i = 0; i < getSize(cr->carList); i++)
	{
		if (strcmp(crit, "model");

	}
}
*/

DynamicList* getAll(CarRental* cr)
{
	return cr->carList;
}

void testRental()
{
	CarRental* cr = createCarRental();

	addCar(cr, "gl21", "bmw", "sport", "dsa", 0);
	assert(getSize(getAll(cr)) == 1);
	addCar(cr, "gl231", "bmw", "sport", "dsa", 0);
	assert(getSize(getAll(cr)) == 2);

	destroyCarRental(cr);





}

int main()
{
	testRental();
	return 0;
}