#include "Controller.h"


void Controller::add(const string& nume, const string& info)const
{
	Entitate e{ nume,info };
	vali.validate(e);
	repo.store(e);
}

void Controller::sterge(const string& nume) const
{
	repo.remove(nume);
}

const DictionarOrdonat& Controller::getAllE() const
{
	return repo.getAll();
}

const Entitate& Controller::findEC(const string& nume) const
{
	return repo.findE(nume);
}

void Controller::modificaE(const string& nume, const string& info)const
{ 
	Entitate e{ nume,info };
	vali.validate(e);
	repo.modifica(e);
}