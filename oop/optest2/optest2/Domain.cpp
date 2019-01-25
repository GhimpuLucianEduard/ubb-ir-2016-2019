#include "Domain.h"
ostream& operator<<(ostream& os, const Carte& ct)
{
	os << ct.getId() << " " << ct.getTitlu() << " " << ct.getAutor() << " " << ct.getGen();
	return os;
}