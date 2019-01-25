#pragma once

#include "Domain.h"
#include <algorithm>
/*
	Clasa care se ocupa de stocare si incarcare din fisier a songurilor
	Necesita ca si parametru in constructor un string, numele fisierului

*/
class Repository
{
private:
	//vector care retine toate songurile
	vector<Song> all;
	//numele fisierul unde se citescre/scire
	string fName;
public:
	//constructor
	Repository(const string& fName) : fName{ fName } { load(); };
	
	//functie care depoziteaza in repo un song
	//date de intrare: song& s
	void store(const Song& s);
	//functie folosite pentru a citi date din fisier
	void load();
	//functie folosita pentru a scrie in fisier
	void write();
	//sterge o song din repo
	//date de intrare int id
	void remove(const int& id);
	//returneaza un vector cu toate songurile din repo
	vector<Song> getAll() const;
	//functie care cauta si retuneza o functie
	//date de intrare: id 
	//date de iesire: songul gasit
	const Song& find(const int& id) const;
	//updateaza un song
	//date de intrare: song nou
	//date de iesire: somgul e updatat
	void Repository::update(const Song& nou);
	
};