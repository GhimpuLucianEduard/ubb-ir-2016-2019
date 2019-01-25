#pragma once
#include "carDynamicList.h"

typedef struct
{
	DynamicList* carList;
} CarRental;

CarRental* createCarRental();
/*
	Create a car rental 
*/	
void deleteCar(CarRental* cr, char* idPlate);

void destroyCarRental(CarRental* cr);
/*
	Free memory
*/

Car* addCar(CarRental* cr, char* idPlate, char* model, char* type, int status);
/*
	Add a car to the car rental
	Pre: Cr - car rental, and all the 
	Post: car added to the cr
*/

DynamicList* getAll(CarRental* cr);
/*
	Return all the cars from the car rental (cr)
*/

Car* findCar(CarRental* cr, char* idPlateTBF);
/*
	Return the car wich idPlate is given by the user, used to search for a specific car
	Pre: cr - car rental, idPlateTBF - char (the id of the car the user want to search)
	Post: return a car pointer, if the car is in the database, return 0
*/

void carUpdate(CarRental* cr, char* oldIdPlate, char* newIdPlate, char* newModel, char* newType, int newStatus);
void statusChanger(CarRental* cr, char* idPlate);
void testRental();

/*

	tests
*/