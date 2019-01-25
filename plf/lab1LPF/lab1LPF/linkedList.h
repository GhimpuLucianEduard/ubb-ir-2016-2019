#pragma once
#include <iostream>
#include <stdlib.h>
using namespace std;


//Struct pentru Nod
template <typename T>
struct Node
{
	T value;
	Node* next;
	
};


//Struct pentru Lista
template <typename T>
struct LinkedList
{
	Node<T>* first = nullptr;
};


//Functie wrapper pentru crearea recursiva
template <typename T>
LinkedList<T> creare()
{	
	LinkedList<T> temp;
	temp.first = createRec<T>();
	return temp;
}

//Creaza recursiv o lista, input de la utilizator
template <typename T>
Node<T>* createRec()
{
	T elem;
	cout << "Type a element: " << endl;
	cin >> elem;

	if (elem == 0)
	{
		return nullptr;
	}
	else
	{
		Node<T>* n = new Node<T>;
		n->value = elem;
		n->next = createRec<T>();
		return n;
	}

}


//Destructor de lista, wrapper function
template <typename T>
void destroyLinkedList(LinkedList<T> l)
{
	destroyRec(l.first);
}

//Destructor recursiv
template <typename T>
void destroyRec(Node<T>* n)
{
	if (n != nullptr)
	{
		destroyRec(n->next);
		delete n;
	}
}

//Wrapper function pentru printare
template <typename T>
void printList(LinkedList<T> l)
{
	printRec(l.first);
	cout << endl;
}

//Printare recursiva
template <typename T>
void printRec(Node<T>* n)
{
	if (n != nullptr)
	{
		cout << n->value << " ";
		printRec(n->next);
	}

}

/*
	Să se determine numărul format prin însumarea elementelor de ordin par
	ale unei liste, din care se scad elementele de ordin impar ale listei.
*/
template <typename T>
int cerinta11a(LinkedList<T> l)
{
	return sumaRec11a(l.first);
}

template <typename T>
int sumaRec11a(Node<T>* n)
{
	if (n == nullptr)
	{
		return 0;
	}
	else if (n->next == nullptr)
	{
		return n->value*(-1);
	}
	else
	{
		return n->value*(-1) + n->next->value + sumaRec11a(n->next->next);
	}
}


//Wrapper pentru cautare
template <typename T>
bool cautare(LinkedList<T> l, T elem)
{
	return cautareRec(l.first, elem);
}

//Cautare recursiva
template <typename T>
bool cautareRec(Node<T>* n, T elem)
{
	if (n == nullptr)
	{
		return false;
	}
	else if (n->value == elem)
	{
		return true;
	}
	else
	{
		return cautareRec(n->next, elem);
	}



}


//Wrapper pentru adaugare, primeste un TElement si creaza un nod care v-a fi adaugat
template <typename T>
LinkedList<T> adaugare(LinkedList<T> l, T elem)
{	
	Node<T>* nou = new Node<T>;
	nou->value = elem;
	nou->next = nullptr;
	adaugareRec<T>(l.first, nou);
	return l;
}

//Wrapper function pentru adaugare, dar returneaza nod, nu lista (folosit pentru a putea face adaugare recursiva in diferenta de multimi)
template <typename T>
Node<T>* adaugareReturn(Node<T>* l, Node<T>* n)
{
	if (l == nullptr)
	{
		l = n;
		return l;
	}
	if (n == nullptr)
	{
		return l;
	}
	else
	{
		adaugareRec(l, n);
		return l;
	}
}

//Adaugare recursiva
template <typename T>
void adaugareRec(Node<T>* n, Node<T>* elem)
{
	if (n == nullptr)
	{
		n = elem;
	}
	else if (n->next == nullptr)
	{

		n->next = elem;
	}
	else
	{
		adaugareRec(n->next, elem);
	}
}

// Să se determine diferența a două mulțimi reprezentate sub formă de lista
template <typename T>
LinkedList<T> diferenta(LinkedList<T> l1, LinkedList<T> l2)
{	
	LinkedList<T> l3;
	l3.first = diferentaRec(l1.first, l2.first);
	return l3;
}

template <typename T>
Node<T>* diferentaRec(Node<T>* l1, Node<T>* l2)
{

	if (l1 == nullptr)
	{
		return nullptr;
	}
	else if (cautareRec(l2, l1->value) == false)
	{

		Node<T>* nou = new Node<T>;
		nou->value = l1->value;
		nou->next = nullptr;

		return adaugareReturn(nou, diferentaRec(l1->next, l2));
	}
	else
	{
		return diferentaRec(l1->next, l2);
	}
}
