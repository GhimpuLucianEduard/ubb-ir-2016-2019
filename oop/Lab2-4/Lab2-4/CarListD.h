#pragma once
#include "Car.h"

typedef Car* CarPointer;
typedef struct
{
	CarPointer* element;
	int len;
	int capacity;
} DynamicList;

DynamicList* createDynamicList();

void destroyDynamicList(DynamicList* list);


void addElement(DynamicList* list, CarPointer cp);

int getSize(DynamicList* list);

CarPointer getCarPointer(DynamicList* list, int index);





