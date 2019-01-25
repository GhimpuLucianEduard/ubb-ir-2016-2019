#pragma once
#include "CarListD.h"

typedef struct
{
	DynamicList* carList;
} CarRental;

CarRental* createCarRental();

void destroyCarRental(CarRental* cr);

Car* addCar(CarRental* rental, char* licensePlate, char* producer, char* model, char* type, int rent);

int findCar(CarRental* rental, char* licensePlate);

//void deleteCar(CarRental* rental, char* licensePlate);

void carUpdate(CarRental* rental, char* oldLicensePlate, char* newLicensePlate, char* producer, char* model, char* type);

void showByCrit(CarRental* rental, char* crit, char* crit2);

DynamicList* getAll(CarRental* cr);


