#pragma once

#include "Domain.h"
#include "Repo.h"
#include "Validator.h"
#include <vector>
#include <string>
using namespace std;

class CarController
{
private:
	Repository& repo;
	CarValidator& vali;

public:
	//constructor
	CarController(Repository& repo, CarValidator& vali) : repo{ repo }, vali{ vali } {};

	//no copy allowed
	CarController(const CarController& ot) = delete;

	//add method
	void addCar(const string& id, const string& producer, const string& model, const string& type);
	
	//getAll method
	const vector<Car>& getAll();

	//delete car method
	void deleteCar(string id);

	const Car& findCar(string id);
	
	//update method
	void  update(string id,string producer,string model,string type);

	//filt
	vector<Car> producerFilt(string filt);
	vector<Car> typeFilt(string filt);

	//sort methods
	vector<Car> generalSort(bool(*lesser)(const Car&, const Car&));
	vector<Car> sortById();
	vector<Car> sortByProducatorModel();
};