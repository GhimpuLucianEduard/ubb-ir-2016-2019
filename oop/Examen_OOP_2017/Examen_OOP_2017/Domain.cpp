#include "Domain.h"


ostream& operator<<(ostream& os, const Song& s)
{
	os << s.getId() << " " << s.getTitlu() << " " << s.getArtist() << " " << s.getRank();
	return os;
}

