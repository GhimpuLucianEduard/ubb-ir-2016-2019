#pragma once

typedef struct {
	
	char* idPlate;
	char* model;
	char* type;
	int status;
} Car;

Car* createCar(char* idPlate, char* model, char* type, int status);

void destroyCar(Car* c);

char* getIdPlate(Car* c);

char* getModel(Car* c);

char* getType(Car* c);

int getStatus(Car* c);

