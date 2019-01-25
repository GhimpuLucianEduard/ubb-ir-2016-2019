#include "controller.h"
#include <algorithm>
#include <iterator>
using namespace std;


void Controller::addCar(const string& id, const string& producer, const string& model, const string& type)
{
	Car c{ id,producer,model,type };
	vali.validate(c);
	repo.store(c);
	notifyAll();
}


const Car& Controller::findCar(const string& id)
{
	return repo.find(id);
}



void Controller::removeCar(const string& id)
{
	
	repo.remove(repo.find(id));
	cos.stergeDinCos(id);
	
	notifyAll();
}

void Controller::updateCar(const string& id)
{
	repo.update(repo.find(id));
	//notifyAll();
}

vector<Car> Controller::getAllCars()
{
	return repo.getAll();
}

vector<Car> Controller::sortById()
{
	auto rez = repo.getAll();
	sort(rez.begin(),rez.end(),[](const Car& c1,const Car& c2){
		return c1.getId() < c2.getId();
	});
	return rez;
}
vector<Car> Controller::Controller::sortByProdModel()
{
	auto rez = repo.getAll();
	sort(rez.begin(), rez.end(), [](const Car& c1, const Car& c2) {
		if (c1.getProducer() == c2.getProducer())
		{
			return c1.getModel() < c2.getModel();
		}
		return c1.getProducer() < c2.getProducer();
	});
	return rez;
}

vector<Car> Controller::filtProducer(const string& producer) 
{
	vector<Car> rez;
	std::copy_if(repo.getAll().begin(), repo.getAll().end(), back_inserter(rez), [&](const Car& c) {return c.getProducer() == producer; });
	return rez;
}

void Controller::addCosC(const string& id)
{
	
	cos.addCos(repo.find(id));
	notifyAll();
	

}


void Controller::stergeCosC()
{
	cos.stergeCos();
	notifyAll();
}

void Controller::fillC(int nr)
{
	cos.fill(nr);
	notifyAll();
}


vector<Car> Controller::getCosC() const
{
	return cos.getCos();
}


