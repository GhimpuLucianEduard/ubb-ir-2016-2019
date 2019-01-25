#pragma once
#include "car.h"

typedef Car* Element;
typedef struct
{
	Element* elems;
	int len;
	int capacity;
} DynamicList;

DynamicList* createDynamicList();
/*
	Create an empty list 
*/

void destroyDynamicList(DynamicList* list);
/*
	Free memeory
	Post: list - invalid pointer
*/

void addElement(DynamicList* list, Element e);
/*
	Add an element to the list
	Post: e is the last elemen, size is increased
*/

int getSize(DynamicList* list);
/*
	Return the number of elements in the list
*/

void deleteElement(DynamicList* list, int index);


Element getElement(DynamicList* list, int index);
/*
	Return the element for a given index
*/

void testList();
/*
	Tests
*/