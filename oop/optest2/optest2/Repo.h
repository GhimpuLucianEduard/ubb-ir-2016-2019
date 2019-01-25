#pragma once

#include "Domain.h"
#include <iterator>
#include <algorithm>


class Repository
{
private:
	vector<Carte> all;
	string file;
public:
	Repository(const string& file) : file{ file } { load(); }
	void load();
	void write();
	void store(const Carte& ct);
	void remove(const int& id);
	vector<Carte> getAll() const;



};