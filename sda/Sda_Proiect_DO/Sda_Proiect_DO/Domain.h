#pragma once
#include <iostream>
#include <string>
using namespace std;
#include <functional>
//forward declaration
class Nod;
class Iterator;
class Entitate;

//pointer la Nod (il folosesc des ca sa nu stau sa scriu mereu *
typedef Nod *pNod;
//numarul maxim pe care il poate lua m ( m este marimea tabelei de dispersie )
#define max 20

//functie pentru a combina 2 liste inlantuite
//pre: n1,n2 capetele la 2 liste
//post: rez -> pointer la primul nod din lista obtinuta
pNod merge(pNod n1, pNod n2);

//Functie folosita pentru a creea copie unei liste inlantuite
//Pre: head -> pointer la capatul listei
//Post: head' -> pointer la capatul listei noi
pNod copieLista(pNod head);

//Functie care face ceva
ostream& operator<<(ostream& os, const Entitate& e);

int relatie(const Entitate& e1, const Entitate& e2);

using compare = int(const Entitate& , const Entitate& );

/*
	Clasa pentru exceptii
*/
class Exceptions
{
private:
	string msg;
public:
	Exceptions(const string& msg) : msg{ msg } {};
	const string& getMsg() const { return msg; }
};



/*
	Clasa entitate (TElement)
	Contine cheie si valoare
	Cheia = string name
	Valoare = string info
*/

class Entitate
{
private:
	string nume;
	string info;
public:
	//constructor
	Entitate(const string& nume, const string& info) : nume{ nume }, info{ info } {};
	
	//gettere
	const string& getNume() const { return this->nume; };
	const string& getInfo() const { return this->info; };

	//diferiti operatori (de care aparent nu prea am avut nevoie)
	Entitate& operator=(const Entitate& ot) { this->nume = ot.getNume(); this->info = ot.getInfo(); return *this; };

	friend ostream& operator<<(ostream& os, const Entitate& e);
	bool operator==(const Entitate& ot)
	{
		return this->getNume() == ot.getNume();
	};

};

/*
	Clasa Nod
	Contine informatie si pointer la urmatorul nod
*/
class Nod
{
private:
	Entitate element;
	pNod urmator;
public:
	//constructor (setez urmator la nullptr by default)
	Nod(Entitate element) : element{ element }, urmator{ nullptr } {};

	//setter pentru urmator (puteam sa fac restu claselor friend da tarziu mi-am dat seama)
	void setUrmator(pNod ptr) { urmator = ptr; };
	
	//gettere
	pNod getUrmator() { return urmator; };
	Entitate& getElement() { return element; };


};



/*
	Clasa Dictionar Ordonat
	Contine:
	m->numarul maxim de locatii
	pNod liste[max]-> lista cu pointer la fiecare prim pointer din listele independete
*/


class DictionarOrdonat
{
private:
	//numarul maxim de locatii din tabela
	int m;
	//vector de pointeri la primul nod din fiecare lista independenta
	pNod liste[max];
	compare* relatie;
public:
	//functia de dispersie
	friend class Iterator;
	int hash(string cheie) const;
	//friend int relatie(const Entitate& e1, const Entitate& e2);
	
	
	DictionarOrdonat(int m, compare* functie) : relatie(functie) 
	{	
		
		this->m = m;
		//pNod* liste = new pNod[m];
		for (int i = 0; i < m; i++)
		{
			liste[i] = nullptr;
		}
	};
	


	Iterator iterator() const;

	void adauga(const string& chiei, const  string& valoare);
	void sterge(string cheie);
	bool find(const string& cheie)const;
	const Entitate& find2(const string& cheie)const;
	int dim();
	
	~DictionarOrdonat();

	//void afis();
};


class Iterator
{
private:
	const DictionarOrdonat& od;
	pNod curent;
	pNod first;
public:
	friend class DictionarOrdonat;
	
	Iterator(const DictionarOrdonat& od) : od{ od }
	{

		first = nullptr;
		for (int i = od.m-1; i >= 0; i--)
		{

			first = merge(copieLista(od.liste[i]), first);
		}

		curent = first;
	};


	/*
	Iterator(const DictionarOrdonat& od) : od{ od }
	{

		first = nullptr;
		for (int i = 0; i < od.m; i++)
		{

			first = merge(copieLista(od.liste[i]), first);
		}

		curent = first;
	};
	*/
	bool valid()
	{
		return (curent != nullptr);
	}

	void urmator()
	{
		curent = curent->getUrmator();
	}

	Entitate element()
	{
		return curent->getElement();
	}

	~Iterator();

};


class Validator
{
public:
	void validate(const Entitate& e)
	{
		string err;

		if (e.getNume().size() == 0)
		{
			err += "Numele nu poate fi vid! ";
		}
		if (e.getInfo().size() == 0)
		{
			err += "Informatia nu poate fi vida! ";
		}

		if (err.size() > 0)
		{
			throw Exceptions(err);
		}


	}
};

