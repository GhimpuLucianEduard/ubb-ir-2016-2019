#include "repo.h"
#include <algorithm>
#include <fstream>
void Repository::store(const Carte& c)
{
	auto rez = find_if(all.begin(), all.end(), [&](const Carte& carte) {

		return c.getTitlu() == carte.getTitlu();
	});

	if (rez != all.end())
	{
		throw CarteException{ "Carte exista deja in database!" };
	}
	all.push_back(c);
}

void Repository::remove(const Carte& c)
{
	auto rez = find_if(all.begin(), all.end(), [&](const Carte& carte) {

		return c.getTitlu() == carte.getTitlu();
	});

	if (rez == all.end())
	{
		throw CarteException{ "Carte nu exista  in database!" };
	}
	all.erase(rez);
}

Carte Repository::find(const string& titlu) const
{
	auto rez = find_if(all.begin(), all.end(), [&](const Carte& carte) {

		return titlu == carte.getTitlu();
	});

	if (rez == all.end())
	{
		throw CarteException{ "Carte nu exista  in database!" };
	}
	
	return *rez;
}

vector<Carte> Repository::getAll() const
{
	return all;
}

void FileRepo::load()
{
	ifstream in( fileName );

	if (!in.is_open())
	{
		throw CarteException{ "nu a putu fisr rdeschids!" };
	}

	while (!in.eof())
	{
		string titlu;
		in >> titlu;
		string autor;
		in >> autor;
		int nrPag;
		in >> nrPag;

		if (in.eof()) {
			break;
		}
		Carte c{ autor,titlu,nrPag };
		Repository::store(c);
		
	}

	in.close();
}

void FileRepo::write()
{
	ofstream out(fileName);

	if (!out.is_open())
	{
		throw	CarteException{ "Unable to open the file!" };
	}

	for (const auto& carte : Repository::getAll())
	{
		out << carte.getAutor();
		out << endl;
		out << carte.getTitlu();
		out << endl;
		out << carte.getNrPag();
		out << endl;
		
	}
	out.close();
}