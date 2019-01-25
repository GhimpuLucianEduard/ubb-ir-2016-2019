#pragma once
#include "Repo.h"
#include "Validator.h"


/*
	Pentru sablon observer, clasa pur virtuala
*/
class Observer
{
public:
	virtual void update() = 0;
};
/*
	Pentru sablonul observer, clasa interfata
	Retine un vector de Oberveri

*/
class Observable
{
private:
	vector<Observer*> all;
public:
	//functie folosita pentru a inregista un observer la observable
	void reg(Observer* nou) { all.push_back(nou); }
	//functie care notifica toti observeri registrati
	void notif()
	{
		for (const auto& o : all)
		{
			o->update();
		}
	}
};
/*
	Clasa controller
	Necesita Un repo si un validato trimisi prin referinta
	Face legatura intre GUi si reposiotry
*/
class Controller: public Observable
{
private:
	//parametrii trasmisi prin referinta
	Repository& repo;
	Validator& vali;
public:
	//constructor
	Controller(Repository& repo, Validator& vali) :repo{ repo }, vali{ vali } {};
	//functie pentru adaugarea de song, param de intrare: id,titlu,artist,rank conform validatorului
	void addSong(const int& id, const string& titlu, const string& artist, const int& rank);
	//functie care returneaza un vector de songs, param de iesire: vector<Song>
	vector<Song> getAllSogns() const;
	
	//functie care returneaza un vector de songs sortate dupa rank, param de iesire: vector<Song>
	vector<Song> sortRank() const;
	//functie cate primeste un int rank ca parametru si returneaza numarul de melodii din controller cu acealsi rank
	//param de iesire: int cate (nr de songuri cu acelasi rank)
	//parm de intrare: int rank (rankul dorit)
	int cateLaFel(const int& rank) const;
	//Functie care cauta si returneaza un song
	//Param de intrare: id int, idul songului de cautat
	//Param de isere: song&
	const Song& findSong(const int& id) const;
	//functie pentru a updata un song
	//Param de intrare: int id,, rank nou si titlu nou
	//Param de iesire: song&
	void updateSong(const int& id, const string& tnou, const int& rnou);
	//functie folosita pentru a sterge o melodie
	//parm de intrare: int id - idul songului de ster
	void removeSong(const int& id);
	

};