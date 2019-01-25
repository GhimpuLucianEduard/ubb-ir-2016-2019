#include "linkedList.h"
#include <stdlib.h>












int main()
{	
	//test create, print 
	LinkedList<int> l1;
	LinkedList<int> l2;
	cout << "Introdu prima lista:" << endl;
	l1 = creare<int>();
	cout << "Introdu a 2 a lista:" << endl;
	l2 = creare<int>();
	
	//test cerinta 11a
	printList(l1);
	printList(l2);
	int n11 = cerinta11a(l1);
	cout << "Suma nr poz pare, dif nr poz impare pt l1: " << n11 << endl;
	
	//test cautare
	cout << cautare(l1, 99) << endl;

	//test adaugare
	l1=adaugare(l1, 56);
	l1 = adaugare(l1, 57);
	l1 = adaugare(l1, 58);
	l1 = adaugare(l1, 59);
	printList(l1);
	printList(l2);

	//test diferenta (11b)
	LinkedList<int> l3;
	l3 = diferenta(l1, l2);
	printList(l3);


	destroyLinkedList(l1);
	destroyLinkedList(l2);
	destroyLinkedList(l3);
	
	





	return 0;
}