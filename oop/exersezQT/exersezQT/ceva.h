#pragma once
#include <iostream>
#include <string>
#include <vector>
using namespace std;
class Repository
{
private:
	vector <string> all;
	
public:

	Repository() = default;
	virtual ~Repository() {};
	Repository(const Repository& ot) = delete;

	virtual void store(const string st);

	const string& find(string st);

	const vector<string>& getAll();

	void deletes(string st);

	void update(string vechi, const string nou);
};