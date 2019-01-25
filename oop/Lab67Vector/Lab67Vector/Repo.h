#pragma once
#include "Domain.h"
#include <vector>
#include <string>
using namespace std;


class Repository
{	

private:
	//vector with all the values of a given "T" object
	vector<Car> all;

	//method used to verify if a car is already in the repo
	bool exist(const Car& t);

public:
	//default constructor
	Repository() = default;

	//no copy allowed
	Repository(const Repository& ot) = delete;

	//store method
	void store(const Car& t);


	//find method
	const Car& find(string id);


	//get all method
	const vector<Car>& getAll();

	//delete method;
	void erase(string id);

	//update method
	void update(const Car& c);
};

class RepoException
{
private:
	string errMsg;
public:
	//constructor
	RepoException(string errMsg) : errMsg{ errMsg } {};
	friend ostream& operator<<(ostream& out, const RepoException& ex);

};

ostream& operator<<(ostream&, const RepoException& ex);

