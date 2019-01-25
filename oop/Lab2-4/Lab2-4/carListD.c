#include "CarListD.h"
#include <stdlib.h>
#include <assert.h>

DynamicList* createDynamicList()
{
	DynamicList* rez = malloc(sizeof(DynamicList));
	rez->capacity = 5;
	rez->element = malloc(sizeof(CarPointer)*rez->capacity);
	rez->len = 0;
	return rez;
}

void incCapacity(DynamicList* list, int newCap)
{
	CarPointer* newElement = malloc(sizeof(CarPointer)*newCap);
	int i;
	for (i = 0; i < list->capacity; i++)
	{
		newElement[i] = list->element[i];
	}
		
	free(list->element);
	list->element = newElement;
	list->capacity = newCap;
}


void destroyDynamicList(DynamicList* list)
{
	free(list->element);
}


void addElement(DynamicList* list, CarPointer cp) {
	if (list->len == list->capacity) 
	{
		incCapacity(list, list->capacity * 2);
	}
	list->element[list->len] = cp;
	list->len++;
}


int getSize(DynamicList* list) 
{
	return list->len;
}


CarPointer getCarPointer(DynamicList* list, int index) {
	return list->element[index];
}