#pragma once

#include <iostream>
#include <string>
#include <ostream>
using namespace std;

class CarException
{
private:
	string msg;
public:
	CarException(const string& msg) :msg{ msg } {};
	const string& getMsg() const { return msg;  }
};

class Car
{
private:
	string id;
	string producer;
	string model;
	string type;
public:
	//constructor
	Car(const string& id, const string& producer, const string& model, const string& type);
	
	//copy constructor for c1 = c2
	Car(const Car& ot):id{ ot.id }, producer{ ot.producer }, model{ ot.model }, type{ ot.type } {};
	
	// = operator
	Car& operator=(const Car& ot);
	
	//getters
	const string& getId() const;
	const string& getProducer() const;
	const string& getModel() const;
	const string& getType() const;
	
	// == operator
	bool operator==(const Car& ot);
	friend ostream& operator<<(ostream& os, const Car& c);
};

ostream& operator<<(ostream& os, const Car& c);