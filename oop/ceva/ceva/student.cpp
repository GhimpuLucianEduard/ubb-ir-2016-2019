#include "student.h"
using namespace std;


ostream& operator<<(ostream& os, const Student& s)
{
	os << s.getNume() << " " << s.getMedie();
	return os;
}






void Validator::validate(const Student& s)
{

	 string err;

	 if (s.getMedie() < 0)
		 err += "media nu poate fi <0";
	 if (s.getNume().size() == 0)
		 err += "numele nu paote fi vid";

	 if (err.size() > 0)
		 throw StudentException(err);
}
