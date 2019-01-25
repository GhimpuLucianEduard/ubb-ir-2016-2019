#pragma once
#include <iostream>
#include <string>

using namespace std;

class Car
{
private:
	string id;
	string producer;
	string model;
	string type;
public:
	Car(const string id, const string producer, const string model, const string type);
	//copy constructor
	Car(const Car& ot) : id{ ot.id }, producer{ ot.producer }, model{ ot.model }, type{ ot.type } {};
	void operator = (const Car &ot);
	string getId() const;
	string getProducer() const;
	string getModel() const;
	string getType() const;
	bool operator==(const Car& ot);
};