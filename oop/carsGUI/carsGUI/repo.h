#pragma once


#include "domain.h"
#include <algorithm>
#include <vector>

class Repository
{
private:
	vector<Car> all;
public:
	
	Repository() = default;

	virtual ~Repository() {  };
	
	virtual void store(const Car& c);
	virtual void remove(const Car& c);

	const Car&  find(const string& id) const;
	vector<Car> getAll() const;
	void update(const Car& c);


};

class FileRepo :public Repository
{
private:
	string fName;
	void load();
	void write();
public:
	FileRepo(const string& fName) : Repository(), fName{ fName } { load(); };

	void store(const Car&c) override
	{
		Repository::store(c);
		write();
	}
	void remove(const Car&c) override
	{
		Repository::remove(c);
		write();
	}

};