#include "Domain.h"


Car::Car(string id, string producer, string model, string type)
{
	this->id = id;
	this->producer = producer;
	this->model = model;
	this->type = type;
}

string Car::getId() const
{
	return this->id;
}
string Car::getProducer() const
{
	return this->producer;
}
string Car::getModel() const
{
	return this->model;
}
string Car::getType() const
{
	return this->type;
}
/*
bool cmpId(const Car& c1, const Car& c2)
{
	return c1.getId() < c2.getId();
}
bool cmpType(const Car& c1, const Car& c2)
{
	return c1.getType() < c2.getType();
}
bool cmpModel(const Car& c1, const Car& c2)
{
	return c1.getModel() < c2.getModel();
}
bool cmpProducer(const Car& c1, const Car& c2)
{
	return c1.getProducer() < c2.getProducer();
}
*/