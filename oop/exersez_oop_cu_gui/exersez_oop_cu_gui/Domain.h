#pragma once
#include <iostream>
#include <fstream>
#include <ostream>
#include <string>
#include <vector>
using namespace std;


class Aux
{
public:
	string prof;
	int nr;
	Aux() { nr = 0; }
	void incNr() { nr++; }
	void setProf(const string& st) { prof = st; }
};

class Exceptions
{
private:
	string ex;
public:
	Exceptions(const string& ex) : ex{ ex } {};
	const string& getEx() { return ex; };
};
class Nota
{
private:
	float valoare;
	string student;
	string examinator;
public:
	Nota(const float& v, const string& s, const string& e) : valoare{ v }, student{ s }, examinator{ e } {};
	
	const float& getValoare() const { return valoare; }
	const string& getStudent() const { return student; }
	const string& getExaminator() const { return  examinator; }


	bool operator ==(const Nota& ot)
	{
		return valoare == ot.getValoare();
	}
};



class Validator
{
public:
	void validate(const Nota& n)
	{
		string err;

		if (n.getStudent().size() == 0)
		{
			err += "Numele studentului nu poate fi vid!";
		}
		if (n.getExaminator().size() == 0)
		{
			err += "Numele examiantorului nu poate fi vid!";
		}
		if (n.getValoare() < 0 || n.getValoare() > 10)
		{
			err += " Valoare tre sa fie intre 0 si 10.0";
		}
		
		if (err.size() > 0)
		{
			throw Exceptions(err);
		}
	
	}
};