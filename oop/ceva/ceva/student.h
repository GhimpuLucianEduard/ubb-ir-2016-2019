#pragma once

#include <iostream>
#include <string>
using namespace std;

class StudentException
{
private:
	string msg;
public:
	
	StudentException(const string& msg) : msg{ msg } {};
	const string& getMsg() const { return msg; }
};

class Student
{
private:
	string nume;
	int medie;
public:

	Student(const string& nume, const int& medie) : nume{ nume }, medie{ medie } {};


	Student& operator=(const Student& ot) = default;
	bool operator==(const Student& ot) { return nume == ot.getNume(); }
	const string& getNume() const { return nume; }
	const int& getMedie() const { return medie;  }
	friend ostream& operator<<(ostream& os, const Student& s);

};

ostream& operator<<(ostream& os, const Student& s);

class Validator
{
public:
	void validate(const Student& s);


};