#pragma once

typedef struct
{
	char* licensePlate;
	char* producer;
	char* model;
	char* type;
	int rent;

} Car;

Car* createCar(char* licensePlate, char* producer, char* model, char* type, int rent);

void destroy(Car* c);

char* getLicensePlate(Car* c);
char* getProducer(Car* c);
char* getModel(Car* c);
char* getType(Car* c);
int getRent(Car* c);