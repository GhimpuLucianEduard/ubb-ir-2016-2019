#pragma once

#include <string>
#include <iostream>
using namespace std;
class Carte
{
private:
	string autor;
	string titlu;
	int nrPag;

public:

	Carte(const string& autor, const string& titlu, const int& nrPag);
	
	const string getAutor() const;
	const string getTitlu() const;
	const int getNrPag() const;
	
	bool operator==(const Carte& ot);

	friend ostream operator<<(const ostream& os, const Carte& c);
};

ostream& operator<<(ostream& os, const Carte& c);

class CarteException
{
private:
	string msg;
public:
	CarteException(const string& msg) : msg{ msg } {};
	string getMsg() const { return msg; }
};

