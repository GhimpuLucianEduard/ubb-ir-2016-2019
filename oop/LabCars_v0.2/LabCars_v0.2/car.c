#include "car.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

Car* createCar(char* idPlate, char* model, char* type, int status)
{
	Car* c = malloc(sizeof(Car));
	
	c->idPlate = malloc(sizeof(char)*(strlen(idPlate)+1));
	strcpy(c->idPlate, idPlate);

	c->model = malloc(sizeof(char)*(strlen(model)+1));
	strcpy(c->model, model);

	c->type = malloc(sizeof(char)*(strlen(type)+1));
	strcpy(c->type, type);

	c->status = status;

	return c;
}

void destroyCar(Car* c)
{
	free(c->idPlate);
	free(c->model);
	free(c->type);
	free(c);
}

char*  getIdPlate(Car* c)
{
	return c->idPlate;
}

char* getModel(Car* c)
{
	return c->model;
}

char* getType(Car* c)
{
	return c->type;
}

int getStatus(Car* c)
{
	return c->status;
}