#pragma once
#include <iostream>
#include <string>
#include <vector>
using namespace std;


class Exceptions
{
private:
	string ex;
public:
	Exceptions(const string& ex) : ex{ ex } {};
	const string& getEx() const { return ex; }
};


class Produs
{
private:
	int id;
	string des;
	int count;
	float price;
public:
	Produs(const int& id, const string & des, const int& count, const float& price) : id{id},des{des},count{count},price{price} {}
	const int& getId() const { return id; }
	const string& getDes() const { return des; }
	const int& getCount() const { return count; }
	const float& getPrice() const { return price; }
	void incCount(const int& nr) { count = count + nr; }
	//friend ostream& operator << (ostream& os, const Produs& p);

};


class Validator
{
public:
	void validate(const Produs& p)
	{
		string err;
		if (p.getId() < 0)
		{
			err += "Id-ul nu poate fi engativ";
		}
		if (p.getDes().size() == 0)
		{
			err += "Descriere nu paote fi vida";
		}
		if (p.getPrice() <= 0)
		{
			err += "Pretul nu poate fi negativ sau mai mica ca 0";
		}
		if (p.getCount() < 0)
		{
			err += "Nr de produse nu poate fi negativ!";
		}


		if (err.size() > 0)
		{
			throw Exceptions(err);
		}
	}
};