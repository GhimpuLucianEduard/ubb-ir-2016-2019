#include "carte.h"
using namespace std;


Carte::Carte(const string& autor, const string& titlu, const int& nrPag)
{
	this->autor = autor;
	this->titlu = titlu;
	this->nrPag=nrPag;

}

const string Carte::getAutor() const
{
	return this->autor;
}
const string Carte::getTitlu() const
{
	return this->titlu;
}
const int Carte::getNrPag() const
{
	return this->nrPag;
}

bool Carte::operator==(const Carte& ot)
{
	return this->titlu == ot.getTitlu();
}


ostream& operator<<(ostream& os, const Carte& c)
{
	os <<  c.getAutor() << " " << c.getTitlu() + " ";
	os << c.getNrPag();
	return os;
}