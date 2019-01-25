#include "domain.h"


Car::Car(const string& id, const string& producer, const string& model, const string& type)
{
	this->id = id;
	this->producer = producer;
	this->model = model;
	this->type = type;
}

Car& Car::operator=(const Car& ot)
{
	this->id = ot.getId();
	this->producer = ot.getProducer();
	this->model = ot.getModel();
	this->type = ot.getType();
	return *this;
}


bool Car::operator==(const Car& ot)
{
	return this->getId() == ot.getId();
}


const string& Car::getId() const
{
	return this->id;
}
const string& Car::getProducer() const
{
	return this->producer;
}
const string& Car::getModel() const
{
	return this->model;
}
const string& Car::getType() const
{
	return this->type;
}

ostream& operator<<(ostream& os, const Car& c)
{
	os << c.id << " " << c.producer << " " << c.model << " " << c.type;
	return os;
}