#include "Repo.h"
#include <fstream>



void Repository::store(const Carte& ct)
{
	auto rez = find_if(all.begin(), all.end(), [&](const Carte& c) {

		return ct.getId() == c.getId();
	});



	if (rez != all.end())
	{
		throw Exceptions("Cartea deja in repo!");
	}
	all.push_back(ct);
	write();
}

void Repository::remove(const int& id)
{
	auto rez = find_if(all.begin(), all.end(), [&](const Carte& c) {

		return id == c.getId();
	});



	if (rez == all.end())
	{
		throw Exceptions("Cartea nu e in repo!");
	}

	all.erase(rez);
	write();
}






vector<Carte> Repository::getAll() const
{
	return this->all;
}


void Repository::load()
{
	ifstream in(file);
	
	while (!in.eof())
	{
		int id;
		in >> id;
		string titlu;
		in >> titlu;
		string autor;
		in >> autor;
		string gen;
		in >> gen;
		if (in.eof())
		{
			break;
		}
		Carte c{ id, titlu, autor, gen };
		all.push_back(c);
	}
	in.close();

}
void Repository::write()
{
	ofstream out(file);
	for (const auto& c : all)
	{
		out << c.getId();
		out << endl;
		out << c.getTitlu();
		out << endl;
		out << c.getAutor();
		out << endl;
		out << c.getGen();
		out << endl;
	}

	out.close();
}

