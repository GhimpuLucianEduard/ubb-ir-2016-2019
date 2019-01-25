#include "ctr.h"
#include <iterator>
#include <algorithm>

void Controller::storeCarte(const string& autor, const string& titlu, const int& nrPag)
{
	Carte c{ autor,titlu,nrPag };
	vali.validate(c);
	repo.store(c);
}
void Controller::cumparaCarte(const string& titlu)
{
	repo.remove(repo.find(titlu));
}
Carte Controller::findCarte(const string& titlu)
{
	return repo.find(titlu);
}
vector<Carte> Controller::getAllCarti()
{
	return repo.getAll();
}

vector<Carte> Controller::pagFilt(const int& nr)
{
	vector<Carte> rez;
	copy_if(repo.getAll().begin(), repo.getAll().end(), back_inserter(rez), [&](const Carte& c) {
	
		return c.getNrPag() >= nr;

	});
	return rez;
}