#include "Car.h"
#include "CarListD.h"
#include "CarRental.h"
#include "stdio.h"
#include "utils.h"


void printL(CarRental* cr)
{	
	int i = 0;

	for (i = 0; i < getSize(cr->carList); i++)
	{
		printf("%s \n", getLicensePlate(getCarPointer(cr->carList, i)));
	}
	
	
}

void populate(CarRental *cr)
{
	Car* c1 = addCar(cr, "br01boss", "bmw", "x5", "suv", 0);
	Car* c2 = addCar(cr, "gl10dar", "bmw", "x6", "suv", 0);
	Car* c3 = addCar(cr, "if33ddd", "seat", "leon", "family", 0);
	Car* c4 = addCar(cr, "hu03ghi", "audi", "a8", "sport", 0);
	Car* c5 = addCar(cr, "bv90fff", "ferrari", "italia", "smecherie", 0);

}
void readCar(CarRental *cr)
{

	char  licensePlate[20], producer[20], model[20], type[20];
	int rent = 0;

	printf("License plate: \n");
	scanf("%20s", licensePlate);
	

	printf("Producer: \n");
	scanf("%20s", producer);

	printf("Model: \n");
	scanf("%20s", model);

	printf("Type: \n");
	scanf("%20s", type);

	

	Car* c = addCar(cr, licensePlate, producer, model, type, rent);

	printf("%s Added! \n", getLicensePlate(c));

}

void showAllCars(DynamicList* list) {
	int i;
	for (i = 0; i < getSize(list); i++) {
		Car* c = getCarPointer(list, i);
		printf("%s  %s %s %s", getLicensePlate(c), getProducer(c), getModel(c), getType(c));
		printf(" |Status: ");
		showStatus(c);
		printf("\n");
		
	}
}

void findCarUI(CarRental* cr)
{	

	char licensePlateTBF[20];
	printf("License plate of car: \n");
	scanf("%20s", licensePlateTBF);
	printf("%s", licensePlateTBF);
	Car* c = getCarPointer(cr->carList, 4);
	printf("%s", getLicensePlate(c));
	
	
	if (findCar(cr, licensePlateTBF)==1)
	{	
		
		printf("Car has been found: \n");
		Car* c = getCarPointer(cr->carList, findCar(cr, licensePlateTBF));
		printf("%s %s %s %s", getLicensePlate(c), getProducer(c), getModel(c), getType(c));
		printf(" |Status: ");
		showStatus(c);
		printf("\n");
	}
	else
	
		printf("The car is not in the database! \n");
	
		

}
void carUpdateUI(CarRental* cr)
{	
	char oldLicensePlate[20], newLicensePlate[20], producer[20], model[20], type[20];
	printf("Type the license plate of the car to be updated  \n");
	scanf("%20s", oldLicensePlate);

	if (findCar(cr, oldLicensePlate))
	{
		printf("Type new license plate: \n");
		scanf("%20s", newLicensePlate);
		printf("New Producer: \n");
		scanf("%20s", producer);
		printf("New Model: \n");
		scanf("%20s", model);
		printf("New Type: \n");
		scanf("%20s", type);
		carUpdate(cr, oldLicensePlate, newLicensePlate, producer, model, type);
		printf("Updated!");

	}
	else
		printf("The car is not in the database! \n");
}
/*
void deleteCarUi(cr)
{	
	char licensePlateTBF[20];
	printf("Type the license plate of the car to be deleted: \n");
	scanf("%20s", licensePlateTBF);
	if (findCar(cr, licensePlateTBF))
	{
		Car* c = findCar(cr, licensePlateTBF);
		printf("%s Deleted! \n", getLicensePlate(c));
		deleteCar(cr, licensePlateTBF);
	}
	else
		printf("The car is not in the database! \n");
	
}
*/
void showMenu() {
	printf("1.Add Car.\n");
	printf("2.List all Cars.\n");
	printf("3.Find car.\n");
	printf("4.Update car.\n");
	printf("0.Exit.\n");
	
}

/*
int main() {
	
	CarRental* cr = createCarRental();
	while (1) {
		showMenu();
		int cmd;
		scanf("%d", &cmd);
		if (cmd == 0)
			return 0;
		if (cmd == 1)
			readCar(cr);
		if (cmd == 2)
			showAllCars(getAll(cr));
		if (cmd == 3)
			findCarUI(cr);
		if (cmd == 4)
			carUpdateUI(cr);
		if (cmd == 329)
			populate(cr);
		if (cmd == 69)
			printL(cr);


	}
	destroyCarRental(cr);
	return 0;
}
*/