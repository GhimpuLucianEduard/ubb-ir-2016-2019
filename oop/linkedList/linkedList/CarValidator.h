#pragma once
#include <string>
#include <vector>
#include "Car.h"
using namespace std;

class ValidatorException
{

private:
	vector<string> errMsg;
public:
	ValidatorException(const vector<string>& errors) : errMsg{ errors } {};
	friend ostream& operator<<(ostream& out, const ValidatorException& ex);

};

ostream& operator<<(ostream& out, const ValidatorException& ex);

class CarValidator
{
public:
	void validate(const Car& c);
};