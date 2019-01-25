#pragma once

#include <string>
using namespace std;

template <typename T>
/*
	Node structure
	T value -> value of the object to store
	Node* next -> pointer to the next node of the list
*/
struct Node
{
	T value;
	Node* next;
	Node(T v) : value{ v }, next{ nullptr } {};
	Node(T v, Node* next) : value{ v }, next{ next } {};
};

template <typename T>
class Iterator;

template <typename T>
class LinkedList
{	
	//pointer tot the first node of the list
	Node<T>* first;

	
	//dealloc memory used for the nodes of the list
	void deleteNodes();

	//
	Node<T>* copy(Node<T>*  current);

public:

	//constructor
	LinkedList() : first{ nullptr } {};

	//copy constructor
	LinkedList(const LinkedList& ot);

	//assigment operator
	void operator=(const LinkedList& ot);

	//desctructor
	~LinkedList();

	//return the number of stored objects
	size_t size() const;

	//add to the start of the list
	void addStart(T v);
	
	//add to the end of the list
	void push_back(T v);
	
	//return index of a given object
	int index(T v);
	
	//delete node
	void deleteNode( T v);

	//return the first stored object
	T& firstNode() const;

	//return iteratorat at the first element
	Iterator<T> begin() const;

	//return iteratorat at the last element
	Iterator<T> end() const;

	//index operator []
	T& operator[](int index); //

};


/*
	Copy constructor
*/
template <typename T>
LinkedList<T>::LinkedList(const LinkedList& ot)
{
	first = copy(ot.first);
}

/*
	Asigment constructor
*/
template <typename T>
void LinkedList<T>::operator=(const LinkedList& ot)
{
	deleteNodes();
	first = copy(ot.first);
}

/*
	Destructor
*/
template <typename T>
LinkedList<T>::~LinkedList()
{
	deleteNodes();
}

/*
	Functions that makes copy of a list
*/
template <typename T>
Node<T>* LinkedList<T>::copy(Node<T>* current)
{
	if (current == nullptr)
	{
		return nullptr;
	}

	auto n = new Node<T>{ current->value };
	n->next = copy(current->next);
	return n;

}

template <typename T>
int LinkedList<T>::index(T v)
{
	auto currentNode = first;
	int index = 0;
	while (currentNode->value != v)
	{
		index++;
		currentNode = currentNode->next;
	}
	return index;
}

/*
	Delete a node from the list
*/
template <typename T>
void LinkedList<T>::deleteNode(T v)
{
	Node<T>* pre = first;
	Node<T>* toDel = nullptr;

	if (pre->value == v) 
	{
		toDel = pre;
		first = toDel->next;
		delete toDel;
		return;
	}

	pre = first;
	toDel = first->next;

	while (toDel != nullptr) 
	{
		if (toDel->value == v) 
		{
			pre->next = toDel->next;
			delete toDel; 
			break; 
		}

		pre = toDel;
		toDel = toDel->next;
	}
}


/*
	Add a object to the start of the list
*/
template <typename T>
void LinkedList<T>::addStart(T v)
{
	Node<T>* n = new Node<T>{ v, first };
	first = n;
}

/*
	Add a object to the end of the list
*/
template <typename T>
void LinkedList<T>::push_back(T v)
{
	auto currentNode = first;
	while (currentNode != nullptr && currentNode->next != nullptr)
	{
		currentNode = currentNode->next;
	}

	if (currentNode == nullptr)
	{
		first = new Node<T>{ v,nullptr };
	}
	else {
		currentNode->next = new Node<T>{ v,nullptr };
	}

}	
/*
	Return the number of objects in the list
*/
template <typename T>
size_t LinkedList<T>::size() const
{
	auto currentNode = first;
	int size = 0;
	while (currentNode != nullptr)
	{
		size++;
		currentNode = currentNode->next;
	}
	return size;
}

/*
	[] operator
*/
template <typename T>
T& LinkedList<T>::operator[](int index)
{
	auto currentNode = first;
	int len = 0;
	while (len < index)
	{
		len++;
		currentNode = currentNode->next;
	}

	return currentNode->value;
}

/*
	return the first node of the list
*/
template <typename T>
T& LinkedList<T>::firstNode() const
{
	return first->value;
}

/*
	delete all the nodes
*/
template <typename T>
void LinkedList<T>::deleteNodes()
{
	auto currentNode = first;
	while (currentNode != nullptr)
	{
		auto aux = currentNode->next;
		delete currentNode;
		currentNode = aux;
	}
	
	first = nullptr;
}


/*
	Iterator
*/

template <typename T>
class Iterator
{

	//current position
	Node<T>* current;
public:

	//constructor
	Iterator(Node<T>* c) : current{ c } {}

	//!= operator
	bool operator!=(const Iterator& ot);

	//++ operator
	void operator++();

	T& operator*();
};


/*
	begin() points a iterator the the first node
*/
template <typename T>
Iterator<T> LinkedList<T>::begin() const
{
	return Iterator<T>{first};
}

/*
	points a iterator the the last node
*/
template <typename T>
Iterator<T> LinkedList<T>::end() const 
{
	return Iterator<T>{ nullptr };
}

/*
	!= operator
*/
template <typename T>
bool Iterator<T>::operator!=(const Iterator& ot)
{

	return current != ot.current;

}

/*
	++ operator
*/
template <typename T>
void Iterator<T>::operator++()
{
	current = current->next;
}

/*
	* operator for 
*/
template <typename T>
T& Iterator<T>::operator*()
{
	return current->value;
}
