#include "Car.h"
#include <stdlib.h>
#include <string.h>

Car* createCar(char* licensePlate, char* producer, char* model, char* type, int rent)
{
	Car* c = malloc(sizeof(Car));
	
	
	c->licensePlate = malloc(sizeof(char)*strlen(licensePlate));
	strcpy(c->licensePlate, licensePlate);
	c->producer = malloc(sizeof(char)*strlen(producer));
	strcpy(c->producer, producer);
	c->model = malloc(sizeof(char)*strlen(model));
	strcpy(c->model, model);
	c->type = malloc(sizeof(char)*strlen(type));
	strcpy(c->type, type);
	c->rent = rent;

	return c;
	
}

void destroy(Car* c)
{
	free(c->licensePlate);
	free(c->producer);
	free(c->model);
	free(c->type);
	free(c);
}

char* getLicensePlate(Car* c)
{
	return c->licensePlate;
}

char* getProducer(Car* c)
{
	return c->producer;
}

char* getModel(Car* c)
{
	return c->model;
}

char* getType(Car *c)
{
	return c->type;
}

int getRent(Car* c)
{
	return c->rent;
}