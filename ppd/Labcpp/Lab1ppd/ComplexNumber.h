#pragma once
#include <iostream>

using namespace std;

class ComplexNumber
{
private:

	float a;
	float b;

public:

	float GetA() const;
	void SetA(const float& a);
	float GetB() const;
	void SetB(const float& b);
	ComplexNumber();
	ComplexNumber(const float& a, const float& b);
	friend ostream& operator<<(ostream& os, const ComplexNumber& number);
	ComplexNumber operator +(const ComplexNumber& right);
	ComplexNumber operator *(const ComplexNumber& right);
	ComplexNumber operator /(const ComplexNumber& right);

};