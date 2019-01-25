#pragma once
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

class Exceptions
{
private:
	string err;
public:
	Exceptions(const string& err) :err { err } {};
	const string& getErr() const { return err; };
};

class Carte
{
private:
	int id;
	string titlu;
	string autor;
	string gen;
public:
	Carte(const int& id, const string& titlu, const string& autor, const string& gen) : id{ id }, titlu{ titlu }, autor{ autor }, gen{ gen } {};
	const string& getTitlu()  const { return titlu; }
	const string& getAutor() const { return autor;  }
	const string& getGen() const { return gen; }
	const int& getId() const { return id; }
	bool operator==(const Carte& ot) { return id == ot.getId(); }
	friend ostream& operator<<(ostream& os, const Carte& ct);
};



ostream& operator<<(ostream& os, const Carte& ct);