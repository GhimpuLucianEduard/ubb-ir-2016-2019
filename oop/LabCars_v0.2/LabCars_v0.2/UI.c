#include "car.h"
#include "carDynamicList.h"
#include "carRental.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "utils.h"

void populate(CarRental *cr)
{
	Car* c1 = addCar(cr, "br01boss", "bmw",  "suv", 0);
	Car* c2 = addCar(cr, "gl10dar", "bmw",  "suv", 0);
	Car* c3 = addCar(cr, "if33ddd", "seat",  "family", 0);
	Car* c4 = addCar(cr, "hu03ghi", "audi",  "sport", 0);
	Car* c5 = addCar(cr, "bv90fff", "ferrari",  "smecherie", 0);

}

void addCarUI(CarRental* cr)
{
	int status=0;
	char idPlate[20], model[20], type[20];

	printf("Id plate: \n");
	scanf("%20s", idPlate);
	printf("Model: \n");
	scanf("%20s", model);
	printf("Type: \n");
	scanf("%20s", type);

	Car* c = addCar(cr, idPlate, model, type, status);
	printf("ADAUGAT!\n");

}
void findCarUI(CarRental* cr)
{
	char idPlateTBF[20];
	
	printf("Type the id plate of the car: \n");
	scanf("%20s", idPlateTBF);
	
	if (findCar(cr, idPlateTBF) != 0)
	{
		Car* c = findCar(cr, idPlateTBF);
		printf("Car is in the database: \n");
		printCar(c);

	}
	else
		printf("The car is not in the database \n");
}

void carRentUI(CarRental* cr)
{
	char idPlate[20];

	printf("Type the id plate of the car: \n");
	scanf("%20s", idPlate);

	if (findCar(cr, idPlate) != 0)
	{
		Car* c = (findCar(cr, idPlate));
		if (getStatus(c) == 1)
		{
			printf("Car already rented!\n");
			return;
		}
		else
		{
			statusChanger(cr, idPlate);
			printf("Car Rented!\n");
		}
	}
	else
		printf("The car is not in the database!\n");
		
}
void deleteCarUI(CarRental* cr)
{
	char idPlate[20];

	printf("Type the id plate of the car: \n");
	scanf("%20s", idPlate);

	if (findCar(cr, idPlate) != 0)
	{
		deleteCar(cr, idPlate);
		printf("Car deleted!\n");
	}
	else
		printf("Car is not in the database!\n");
}
void carReturnUI(CarRental* cr)
{
	char idPlate[20];

	printf("Type the id plate of the car: \n");
	scanf("%20s", idPlate);

	if (findCar(cr, idPlate) != 0)
	{
		Car* c = (findCar(cr, idPlate));
		if (getStatus(c) == 0)
		{
			printf("Car already returned!\n");
			return;
		}
		else
		{
			statusChanger(cr, idPlate);
			printf("Car Returned!\n");
		}
	}
	else
		printf("The car is not in the database!\n");

}

void carUpdateUI(CarRental* cr)
{
	int newStatus = 0;
	char oldIdPlate[20], newIdPlate[20], newModel[20], newType[20];
	printf("Type the id plate of the car:");
	scanf("%20s", oldIdPlate);
	if (findCar(cr, oldIdPlate) != 0)
	{
		printf("New id plate: \n");
		scanf("%20s", newIdPlate);
		printf("New model: \n");
		scanf("%20s", newModel);
		printf("New type: \n");
		scanf("%20s", newType);
		printf("New status: (1 for rented or 0 for not rented) \n");
		scanf("%d", &newStatus);

		carUpdate(cr, oldIdPlate, newIdPlate, newModel, newType, newStatus);

		printf("Car has been updated! \n");

	}
	else
		printf("The car is not in the database! \n");
	
}
void getAllUI(DynamicList* list)
{
	int i = 0;
	for (i = 0; i < getSize(list); i++)
	{
		printCar(getElement(list,i));

	}
}
void showMenu()
{
	printf("########SUPER CONSOLE v0.2 (NOW IN C/C++)#########\n");
	printf("1. Add car \n");
	printf("2. Show all cars \n");
	printf("3. Find a car \n");
	printf("4. Update a car \n");
	printf("5. Rent a car \n");
	printf("6. Return a car \n");
	printf("7. Delete car \n");
	printf("0. Exit\n");

}

int main()
{
	CarRental* cr = createCarRental();
	testList();
	testRental();
	while (1)
	{
		showMenu();
		int cmd = 0;
		scanf("%d", &cmd);
		if (cmd == 0)
			return 0;
		if (cmd == 1)
			addCarUI(cr);
		if (cmd == 2)
			getAllUI(cr->carList);
		if (cmd == 3)
			findCarUI(cr);
		if (cmd == 4)
			carUpdateUI(cr);
		if (cmd == 5)
			carRentUI(cr);
		if (cmd == 6)
			carReturnUI(cr);
		if (cmd == 7)
			deleteCarUI(cr);
		if (cmd == 329)
			populate(cr);

		

	}
	destroyCarRental(cr);
	return 0;
}