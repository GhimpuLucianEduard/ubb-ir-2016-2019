#pragma once

#include "repo.h"
#include "validator.h"
#include <random>    
#include <chrono>    



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







class Cos {
	Repository& rep;
	vector<Car> masini;
public:
	Cos(Repository& rep) :rep{ rep } {};

	~Cos(){}

	void addCos(const Car& c) 
	{
		masini.push_back(c);
	}
	

	void stergeDinCos(const string& id)
	{
		auto found = find_if(masini.begin(), masini.end(), [&](const Car& car)
		{
			return car.getId() == id;
		});
		masini.erase(found);
	}
	void stergeCos() {
		masini.clear();
	}
	

	void fill(int nr) 
	{
		
		int seed = std::chrono::system_clock::now().time_since_epoch().count();
		vector<Car> all = rep.getAll();
		std::shuffle(all.begin(), all.end(), std::default_random_engine(seed));
		while (masini.size() < nr && all.size()>0) {
			masini.push_back(all.back());
			all.pop_back();
		}

	}

	vector<Car> getCos() const {
		return masini;
	}

};

class Controller : public Observable
{
private:
	Repository& repo;
	Validator& vali;
	Cos cos;
public:

	Controller(Repository& repo, Validator& vali) :repo{ repo }, vali{ vali }, cos{ repo } {};

	void addCar(const string& id, const string& producer, const string& model, const string& type);
	void removeCar(const string& id);
	void updateCar(const string& id);
	const Car& findCar(const string& id);
	vector<Car> getAllCars();
	vector<Car> sortById();
	vector<Car> Controller::sortByProdModel();
	vector<Car> filtProducer(const string& producer);

	
	void addCosC(const string& id);
	void stergeCosC();
	void fillC(int nr);
	vector<Car> getCosC() const;
	

};


