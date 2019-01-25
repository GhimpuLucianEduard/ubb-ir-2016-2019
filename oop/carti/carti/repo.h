#pragma once


#include "carte.h"
#include <vector>

class Repository
{
private:
	vector<Carte> all;
public:

	virtual ~Repository() {};
	virtual void store(const Carte& c);
	virtual void remove(const Carte& c);
	Carte find(const string& titlu) const;

	vector<Carte> getAll() const;
};

class FileRepo : public Repository
{
private:
	string fileName;
	void load();
	void write();
public:
	FileRepo(const string& fileName) : Repository(), fileName{ fileName } { load(); };
	
	void store(const Carte& c) override
	{
		Repository::store(c);
		write();
	}
	
	void remove(const Carte& c) override
	{
		Repository::remove(c);
		write();
	}


};