#include "CarController.h"

using namespace std;

/*
Add method, adds a car to the repo, throw exeption if car already in the repo
Pre: 4 strings ( the atributs of the car)
Post: car added to repo, or exception
*/
void CarController::addCar(const string& id, const string& producer, const string& model, const string& type)
{
	Car c{ id,  producer, model,  type };
	vali.validate(c);
	repo.store(c);
}

/*
specificatii
*/
const LinkedList<Car>& CarController::getAll()
{
	return repo.getAll();
}

/*
specifitcatii
*/
void CarController::deleteCar(string id)
{
	repo.erase(id);
}

const Car& CarController::findCar(string id)
{
	return repo.find(id);


}

void CarController::update(string id, string producer, string model, string type)
{

	Car cnew{ id,producer,model ,type };
	vali.validate(cnew);
	repo.update(cnew);

}

LinkedList<Car> CarController::producerFilt(string filt)
{

	LinkedList<Car> rez;
	for (const auto& car : repo.getAll())
	{
		if (car.getProducer() == filt)
		{
			rez.push_back(car);
		}
	}
	return rez;
}

LinkedList<Car> CarController::typeFilt(string filt)
{
	LinkedList<Car> rez;
	for (const auto& car : repo.getAll())
	{
		if (car.getType() == filt)
		{
			rez.push_back(car);
		}
	}
	return rez;
}

LinkedList<Car> CarController::generalSort(bool(*lesser)(const Car&, const Car&))
{
	LinkedList<Car> rez{ repo.getAll() };
	for (size_t i = 0; i < rez.size(); i++)
	{
		for (size_t j = i + 1; j < rez.size(); j++)
		{
			if (!lesser(rez[i], rez[j]))
			{
				Car aux = rez[i];
				rez[i] = rez[j];
				rez[j] = aux;
			}
		}
	}

	return  rez;
}

LinkedList<Car> CarController::sortById()
{
	return generalSort([](const Car& c1, const Car& c2) {

		return c1.getId() < c2.getId();
	});
}

LinkedList<Car> CarController::sortByProducatorModel()
{
	return generalSort([](const Car& c1, const Car& c2) {

		if (c1.getProducer() == c2.getProducer())
		{
			return c1.getModel() < c2.getModel();
		}
		return c1.getProducer() < c2.getProducer();
	});
}