#include "Car.h"


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

bool Car::operator==(const Car& ot)
{
	return this->getId() == ot.getId();
}
void Car::operator = (const Car &ot)
{

	id = ot.getId();
	producer = ot.getProducer();
	model = ot.getModel();
	type = ot.getType();

}