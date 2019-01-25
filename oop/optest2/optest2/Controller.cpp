#include "Controller.h"



void Controller::addCarte(const int& id, const string& titlu, const string& autor, const string& gen)
{
	Carte c{ id,titlu,autor,gen };
	repo.store(c);
	notificare();
}
vector<Carte> Controller::getAllCarti() const
{
	return repo.getAll();
}
int Controller::aGen(const string& gen) const
{	
	int rez=0;
	for (const auto& c : repo.getAll())
	{
		if (c.getGen() == gen)
		{
			rez++;
		}
	}
	return rez;

}
int Controller::aAutor(const string& autor) const
{
	int rez = 0;
	for (const auto& c : repo.getAll())
	{
		if (c.getAutor() == autor)
		{
			rez++;
		}
	}
	return rez;
}

void Controller::removeCarte(const int& id)
{
	repo.remove(id);
	notificare();
}