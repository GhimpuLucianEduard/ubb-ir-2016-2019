#pragma once
#include "Repo.h"


class Observer
{
public:
	virtual void update() = 0;
};

class Observable
{
private:
	vector<Observer*> all;
public:
	void reg(Observer* obs) { all.push_back(obs); }
	void noti()
	{
		for (const auto& o : all)
		{
			o->update();
		}
	}
};


class Controller :public Observable
{
private:
	Repository& repo;
	Validator& vali;
public:
	Controller(Repository& repo, Validator& vali) : repo{ repo }, vali{ vali } {};


	void addProdus(const int& id, const string & des, const int& count, const float& price);
	vector<Produs> getAll() const;
	vector<Produs> getSorted() const;
	void inc(const int& id, const int& nr);
	vector<Produs> getFilt(const int& filt) const;
	void exportare(const string& fileName) const;

};