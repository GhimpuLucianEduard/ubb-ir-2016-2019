#pragma once

#include <vector>
#include "student.h"
#include <algorithm>
#include <iterator>
class Repository
{
private:
	vector<Student> all;
public:


	virtual ~Repository() {};
	virtual void store(const Student& s);


	virtual void remove(const Student& s);

	Student find(const string& s);
	vector<Student> getAll();

};


class FileRepo :public Repository
{
private:
	string fileName;
	void load();
	void write();
public:
	
	FileRepo(const string& fileName) : Repository(), fileName{ fileName } { load(); };
	void store(const Student& s) override { Repository::store(s); write(); };
	void remove(const Student& s) override { Repository::remove(s); write(); };

};