#include "carDynamicList.h"
#include <stdlib.h>
#include <assert.h>

DynamicList* createDynamicList()
/*
	Create an empty list and allocate space for elements
*/
{
	DynamicList* rez = malloc(sizeof(DynamicList));
	
	rez->capacity = 10;
	rez->elems = malloc(sizeof(Element)*rez->capacity);
	rez->len = 0;
	return rez;
}

void increaseCapacity(DynamicList* list, int newCap) 
/*
	Increase capacity of the list
	Allocate space for new elemets, and copy old ones
	Pre: list - dynamic list newCap - integer (new capacity)
	Post: list capacity increased
*/
{
	Element* newElements = malloc(sizeof(Element)*newCap);
	int i = 0;
	for (i = 0; i < list->capacity; i++)
		newElements[i] = list->elems[i];

	free(list->elems);
	list->elems = newElements;
	list->capacity = newCap;
}

void destroyDynamicList(DynamicList* list)
/*
	Free the memory
	Pre: list - dynamic list
*/
{
	free(list->elems);
}

void addElement(DynamicList* list, Element e)
/*
	Add an element to the list
	Pre: list - dynamic list, e - new element
	Post: e is the last element, increased size
*/
{
	if (list->len == list->capacity)
	{
		increaseCapacity(list, list->capacity * 2);
	}
	
	list->elems[list->len] = e;
	list->len++;
}

int getSize(DynamicList* list)
/*
	Return the number of elements
	Pre: list - dynamic list
	Post: return list->len  int (number of elements)
*/
{
	return list->len;
}

Element getElement(DynamicList* list, int index)
/*
	Return the element from a given index
	Pre: list - dynamic list, index - int (position of the element)
	Post: return l->elems[index]
*/
{
	return list->elems[index];
}


void deleteElement(DynamicList* list, int index)
{
	int i = 0;
	
	for (i = index; i < getSize(list) - 1; i++)
	{
		list->elems[i] = getElement(list, i + 1);
	}

	list->len--;


}
						//TEST AREA\\

void testAdd()
{
	int i = 0;
	DynamicList* list = createDynamicList();
	assert(getSize(list) == 0);
	addElement(list, createCar("gl21", "bmw", "sport", 0));
	assert(getSize(list) == 1);
	
	for (i = 0; i < 10; i++)
	{
		addElement(list, createCar("gl21", "bmw", "sport", 0));
		assert(getSize(list) == i + 2);
	}
	
	destroyDynamicList(list);
	

}

void testList()
{
	testAdd();
}




