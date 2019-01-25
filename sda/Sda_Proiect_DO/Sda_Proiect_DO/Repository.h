#pragma once
#include "Domain.h"

class Repository
{
private:
	DictionarOrdonat all{ 10,relatie };
	string fName;
	void load();
	void write();
public:

	Repository(const string& fName) : fName{ fName } { load(); };

	void store(const Entitate& e);
	void remove(const string& nume);
	const Entitate& findE(const string& nume)const;
	const DictionarOrdonat& getAll() const;
	void modifica(const Entitate& e);


};