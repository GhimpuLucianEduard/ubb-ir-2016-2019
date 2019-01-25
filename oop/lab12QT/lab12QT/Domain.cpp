#include "Domain.h"

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

bool Car::operator==(const Car& ot)
{
	return this->getId() == ot.getId();
}

const Car& Car::operator=(const Car& ot)
{
	this->id = ot.getId();
	this->producer = ot.getProducer();
	this->model = ot.getModel();
	this->type = ot.getType();

	return *this;
}