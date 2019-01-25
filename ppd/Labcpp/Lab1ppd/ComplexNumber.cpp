#include "pch.h"
#include "ComplexNumber.h"
#include "Constants.h"

float ComplexNumber::GetA() const
{
	return this->a;
}

void ComplexNumber::SetA(const float& a)
{
	this->a = a;
}

float ComplexNumber::GetB() const
{
	return this->b;
}

void ComplexNumber::SetB(const float& b)
{
	this->b = b;
}

ComplexNumber::ComplexNumber()
{
}

ComplexNumber::ComplexNumber(const float& a, const float& b)
{
	this->a = a;
	this->b = b;
}

ComplexNumber ComplexNumber::operator+(const ComplexNumber& right)
{
	return ComplexNumber(this->a + right.GetA(), this->b + right.GetB());
}

ComplexNumber ComplexNumber::operator*(const ComplexNumber & right)
{
	float real = (this->a * right.a) - (this->b * right.b);
	float img = (this->a * right.b) + (this->b * right.a);
	return ComplexNumber(real, img);
}

ComplexNumber ComplexNumber::operator/(const ComplexNumber & right)
{
	float numi = (right.a * right.a) + (right.b * right.b);
	float real = ((this->a * right.a) + (this->b * right.b)) / numi;
	float img = ((this->b * right.a) + (this->a * right.b * (-1))) / numi;
	return ComplexNumber(real, img);
}

ostream & operator<<(ostream & os, const ComplexNumber & number)
{
	return os << number.GetA() << SPACE_STRING << number.GetB() << "i";
}
