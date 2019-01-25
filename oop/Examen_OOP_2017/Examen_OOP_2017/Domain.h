#pragma once


#include <iostream>
#include <string>
#include <vector>
using namespace std;



/*
	Clasa pentru toate exeptiile
	Primeste un string in conusctuctor pecare il returneaza in caz de exceptie
*/

class Exceptions
{
private:
	string ex;
public:
	Exceptions(const string& s) : ex{ s } {};
	const string& getEx() { return ex; }
};


/*
	Song class
	id - int
	titlu - strin
	artist - string
	rank - int
*/
class Song
{
private:
	int id;
	string titlu;
	string artist;
	int rank;
public:
	//song constructor
	Song(const int& id, const string& titlu, const string& artist, const int& rank) : id{ id }, titlu{ titlu }, artist{ artist }, rank{ rank } {};
	//geter id: renturneaza int
	const int& getId() const { return id; }
	//getter titlu, returneaza titlu string
	const string& getTitlu() const { return titlu; };
	//getter artist, returneaza artist string
	const string& getArtist() const { return artist; };
	//getter rank, returnaza rank int
	const int& getRank() const { return rank; };
	//fuctie pt a comapra 2songuri, returneaza true daca sunt egale, false in caz contrar
	bool operator==(const Song& ot) const { return ot.getId() == id;}
	//nici n-am folosito
	friend ostream& operator<<(ostream & os, const Song& s);
	/*
	void setTitlu(const string& nou) { titlu = nou; }
	void setRank(const int& nou) { rank = nou; }
	*/

};


