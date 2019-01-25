#pragma once
#include "Repo.h"


class Observer {
public:
	virtual void update() = 0;
};

class Observable {
protected:
	vector<Observer*> obs;
public:
	void reg(Observer *s) {
		obs.push_back(s);
	}
	void notifyAll() {
		for (auto ob : obs) {
			ob->update();
		}
	}
};

class Controller : public Observable
{
private:
	Repository& repo;
	Validator& vali;
public:
	Controller(Repository& repo, Validator& vali) : repo{ repo }, vali{ vali } {};
	void addNota(const float& value, const string& student, const string& examinator);
	vector<Nota> getAllNote() const { return repo.getAll(); }
	vector<Nota> getSorted() const;
	vector<Nota> getFind(const string& arg) const;
	vector<Aux> getUlti() const;

};