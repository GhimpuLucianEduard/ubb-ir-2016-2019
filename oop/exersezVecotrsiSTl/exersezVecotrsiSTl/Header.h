#pragma once
#include <string>
using namespace std;

class Car
{
private:
	int id;
	string model;
public:
	int Car::getId() const;
	string Car::getModel() const;
	bool Car::operator==(const Car& ot);
	Car(const int id, const string model);
	
};
