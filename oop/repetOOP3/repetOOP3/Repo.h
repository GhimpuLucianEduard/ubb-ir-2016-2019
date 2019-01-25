#pragma once
#include "Domain.h"
#include <fstream>

class Repository
{
	
private:
	vector<Produs> all;
	string fName;
public:
	Repository(const string& f) : fName{ f } { load(); };


	void store(const Produs& p);
	vector<Produs> getAll() const;
	//vector<Produs> getSort() const;
	void load();
	void write();
	void up(const int& id, const int& nr);




};