#include "domain.h"
#include <math.h>



int DictionarOrdonat::hash(string cheie) const
{
	unsigned int code = 0;
	unsigned int i;

	for (i = 0; i < cheie.size(); i++)
	{
		code = code + int((cheie[i] * pow(13, cheie.size() - i)));

	}

	return code%m;
}

int relatie(const Entitate& e1, const Entitate& e2)
{
	/*
	int n1, n2;
	n1 = e1.getNume().size();
	n2 = e2.getNume().size();
	n1--;
	n2--;
	if (e1.getNume()[n1] < e2.getNume()[n2])
	{
		return 1;
	}
	if (e1.getNume()[n1] > e2.getNume()[n2])
	{
		return -1;
	}
	*/


	
	if (e1.getNume().size() < e2.getNume().size())
	{
		return 1;
	}
	if (e1.getNume().size() > e2.getNume().size())
	{
		return -1;
	}
	

	

	/*
	string a = e1.getNume();
	string b = e2.getNume();

	if (a[2] < b[2] )
	{
		return 1;
	}
	if (a[2] > b[2])
	{
		return -1;
	}
	*/

	//returnam 0 daca sunt egale
	return 0;
}

void DictionarOrdonat::adauga(const string& cheie, const  string& valoare)
{
	int index = hash(cheie);
	Entitate e{ cheie,valoare };
	pNod n = new Nod{ e };

	if (liste[index] == nullptr)
	{
		n->setUrmator(liste[index]);
		liste[index] = n;
		return;
	}


	if (relatie(n->getElement(), liste[index]->getElement()) == 1)
	{
		n->setUrmator(liste[index]);
		liste[index] = n;
		return;
	}
	else
	{
		pNod cur = liste[index];
		while (cur->getUrmator() != nullptr && relatie(cur->getUrmator()->getElement(), n->getElement()) == 1)
		{
			cur = cur->getUrmator();
		}
		n->setUrmator(cur->getUrmator());
		cur->setUrmator(n);
	}


}


void DictionarOrdonat::sterge(string cheie)
{
	int index = hash(cheie);

	pNod pre = liste[index];

	pNod toDel = nullptr;

	if (pre->getElement().getNume() == cheie)
	{
		toDel = liste[index];

		liste[index] = liste[index]->getUrmator();
		delete toDel;
		return;
	}

	pre = liste[index];
	toDel = liste[index]->getUrmator();

	while (toDel != nullptr)
	{
		if (toDel->getElement().getNume() == cheie)
		{
			pre->setUrmator(toDel->getUrmator());
			delete toDel;
			break;
		}

		pre = toDel;
		toDel = toDel->getUrmator();;
	}

}




bool DictionarOrdonat::find(const string& cheie) const
{
	int index = hash(cheie);
	pNod cur = liste[index];

	if (cur == nullptr)
	{
		return false;

	}

	if (cur->getElement().getNume() == cheie)
	{
		return true;
	}

	while (cur != nullptr)
	{
		if (cur->getElement().getNume() == cheie)
			return true;
		cur = cur->getUrmator();

	}

	return false;

}

const Entitate& DictionarOrdonat::find2(const string& cheie)const
{
	int index = hash(cheie);
	pNod cur = liste[index];
	Entitate* rez;
	if (cur == nullptr)
	{
		throw Exceptions{ "nu e in enciclopedie" };

	}

	if (cur->getElement().getNume() == cheie)
	{
		rez = &cur->getElement();
	}

	while (cur != nullptr)
	{
		if (cur->getElement().getNume() == cheie)
			rez = &cur->getElement();
		cur = cur->getUrmator();

	}

	return *rez;
}

int DictionarOrdonat::dim()
{
	int n = 0;
	pNod cur;
	for (int i = 0; i < m; i++)
	{
		if (liste[i] != nullptr)
		{
			cur = liste[i];
			n++;
			while (cur->getUrmator() != nullptr)
			{
				n++;
				cur = cur->getUrmator();
			}
		}


	}

	return n;
}




pNod merge(pNod n1, pNod n2)
{
	pNod rez;

	if (n1 == nullptr)
		return(n2);
	if (n2 == nullptr)
		return(n1);


	
	if (relatie(n1->getElement(),n2->getElement())==1)
	{
		rez = n1;
		rez->setUrmator(merge(n1->getUrmator(), n2));
	}
	else
	{
		rez = n2;
		rez->setUrmator(merge(n1, n2->getUrmator()));
	}
	
	
	/*
	if (n1->getElement().getNume() <= n2->getElement().getNume())
	{
		rez = n1;
		rez->setUrmator(merge(n1->getUrmator(), n2));
	}
	else
	{
		rez = n2;
		rez->setUrmator(merge(n1, n2->getUrmator()));
	}
	*/
	return rez;
}





pNod copieLista(pNod head)
{

	if (head == nullptr)
	{
		return nullptr;
	}


	pNod prim = new Nod{ head->getElement() };

	pNod cur = prim;
	pNod next = head->getUrmator();

	while (next != nullptr)
	{
		cur->setUrmator(new Nod{ next->getElement() });
		next = next->getUrmator();
		cur = cur->getUrmator();

	}

	return prim;
}


Iterator DictionarOrdonat::iterator() const
{
	return Iterator{ *this };
}


Iterator::~Iterator()
{
	pNod curent = first;

	while (curent != nullptr)
	{
		pNod urm = curent->getUrmator();
		delete(curent);
		curent = urm;
	}
}


DictionarOrdonat::~DictionarOrdonat()
{

	
	
	pNod first = nullptr;
	for (int i = 0; i < m; i++)
	{

		first = merge(liste[i], first);
	}

	pNod curent = first;

	while (curent != nullptr)
	{
		pNod urm = curent->getUrmator();
		delete(curent);
		curent = urm;
	}

};



ostream& operator<<(ostream& os, const Entitate& e)
{
	os << e.nume << ":" << endl;
	os << e.info;

	return os;
}