#include "carRental.h"
#include "carDynamicList.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "utils.h"

CarRental* createCarRental()
/*
	Create a empty car rental
	Post - rez -> memory allocated for the car rental
*/
{
	CarRental* rez = malloc(sizeof(CarRental));
	rez->carList = createDynamicList();
	return rez;
}

void destroyCarRental(CarRental* cr)
/*
	Free the memory
*/
{
	int i = 0;
	for (i = 0; i < getSize(cr->carList); i++)
	{
		destroyCar(getElement(cr->carList, i));
	}
		

	destroyDynamicList(cr->carList);
	free(cr);
}

Car* addCar(CarRental* cr, char* idPlate, char* model, char* type, int status)
/*
	Add a car to the rental
	Pre: cr - car rental, char* model, type, idPlate and int status (camps to be used to add the car)
	Post: return c (car created)
*/
{
	Car* c = createCar(idPlate, model, type, status);
	if (validate(c))
	{
		errorMsg(validate(c));
		return 0;
	}
	addElement(cr->carList, c);
	return c;
}

DynamicList* getAll(CarRental* cr)
/*
	Return all the cars in the rental
	Pre: cr - car rental
	Post: return the list with all the cars
*/
{
	return cr->carList;
}

Car* findCar(CarRental* cr, char* idPlateTBF)
/*
	Return the car wich idPlate is given by the user, used to search for a specific car
	Pre: cr - car rental, idPlateTBF - char (the id of the car the user want to search)
	Post: return a car pointer, if the car is in the database, return 0 elsewhise
*/
{
	int i = 0;
	for (i = 0; i < getSize(cr->carList); i++)
	{
		if (strcmp(idPlateTBF, getIdPlate(getElement(cr->carList, i))) == 0)
		{
			Car* c = getElement(cr->carList, i);
			return c;
		}
		
	}

	return 0;
}

void deleteCar(CarRental* cr, char* idPlate)
{
	int i = 0, indexToDelete=0;
	Car* c = findCar(cr, idPlate);
	
	for (i = 0; i < getSize(cr->carList); i++)
	{
		if (getIdPlate(getElement(cr->carList, i)) == idPlate);
		indexToDelete = i;
		break;
		
	}
	destroyCar(c);
	
	deleteElement(cr->carList, indexToDelete);
	
	//destroyCar(cr->carList->elems[cr->carList->len - 1]);
	
	
}


void carUpdate(CarRental* cr, char* oldIdPlate, char* newIdPlate, char* newModel, char* newType, int newStatus)
/*
	Updates a existing car
	Pre: cr - car rental, oldIdPlate char to find the car to be updated
	Post: uptade the car
*/
{
	Car* c = findCar(cr, oldIdPlate);

	strcpy(c->idPlate, newIdPlate);
	strcpy(c->model, newModel);
	strcpy(c->type, newType);
	c->status = newStatus;
}

void statusChanger(CarRental* cr, char* idPlate)
/*
	Change the status of a car from rented to not rented or vice versa
	Pre: cr - car rental, idPlate - char*  - id plate of the car to be changed
	Post: car has the status changed
*/
{
	Car* c = findCar(cr, idPlate);

	if (getStatus(c) == 0)
		c->status = 1;
	else
		c->status = 0;

}


							//TEST AREA\\

void testRental()
{
	CarRental* cr = createCarRental();

	addCar(cr, "gl21", "bmw", "sport", 0);
	assert(getSize(getAll(cr)) == 1);
	addCar(cr, "gl231", "bmw", "sport", 0);
	assert(getSize(getAll(cr)) == 2);

	destroyCarRental(cr);
}




