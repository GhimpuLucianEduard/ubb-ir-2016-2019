#include "Repository.h"
#include <fstream>


void Repository::store(const Entitate& e)
{
	if (all.find(e.getNume()) == true)
	{
		throw Exceptions{ "Entitate exista deja in Enciclopedie!" };
	}

	all.adauga(e.getNume(), e.getInfo());
	write();

}

void Repository::remove(const string& nume)
{
	if (all.find(nume) == false)
	{
		throw Exceptions{ "Entitate nu exista in Enciclopedie!" };
	}

	all.sterge(nume);
	write();

}


const DictionarOrdonat& Repository::getAll() const
{
	return all;
}

void Repository::load()
{
	ifstream in(fName);
	if (!in.is_open())
	{
		throw Exceptions{ "Fisierul nu poate fi deschis!" };
	}

	while (!in.eof())
	{
		string nume;
		getline(in, nume);
		if (nume == "")
		{
			break;
		}
		string info;
		getline(in, info);

		//if (in.eof()) {
			//break;
		//}

		Entitate e{ nume,info };
		Repository::store(e);
	}
	in.close();
}

void Repository::write()
{
	ofstream out(fName);

	if (!out.is_open())
	{
		throw	Exceptions{ "Fisierul nu poate fi deschis!" };
	}

	auto it = all.iterator();

	while (it.valid())
	{
		out << it.element().getNume();
		out << endl;
		out << it.element().getInfo();
		out << endl;
		it.urmator();

	}

	out.close();
}

const Entitate& Repository::findE(const string& nume) const
{
	return all.find2(nume);

}


void Repository::modifica(const Entitate& e)
{
	all.sterge(e.getNume());
	all.adauga(e.getNume(),e.getInfo());
	write();
}
