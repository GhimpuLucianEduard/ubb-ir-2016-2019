#include <ceva.h>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

const string& Repository::find(string st)
{

	auto rez = std::find(all.begin(), all.end(), st);
	return *rez;

}


void Repository::store(string st)
{
	
	all.push_back(st);
}

const vector<string>& Repository::getAll()
{
	return all;
}




void Repository::deletes(string st)
{

	all.erase(std::remove(all.begin(), all.end(), st), all.end());
}

void Repository::update(string vechi, const string nou)
{
	deletes(vechi);
	store(nou);

}