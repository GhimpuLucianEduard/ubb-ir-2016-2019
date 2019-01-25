#pragma once
#include "Repo.h"


class Oberver
{
public:
	virtual void update() = 0;
};

class Observable
{
private:
	vector<Oberver*> subs;
public:
	void notificare()
	{
		for (const auto& s : subs)
		{
			s->update();
		}
	}
	void reg(Oberver* sub) { subs.push_back(sub); }
};



class Controller : public Observable
{
private:
	Repository& repo;
public:
	Controller(Repository& repo) : repo{ repo } {  };
	void addCarte(const int& id, const string& titlu, const string& autor, const string& gen);
	vector<Carte> getAllCarti() const;
	int aGen(const string& gen) const;
	int aAutor(const string& autor) const;
	void removeCarte(const int& id);
};
