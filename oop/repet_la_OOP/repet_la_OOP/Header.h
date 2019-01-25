#pragma once
#include <iostream>
using namespace std;
#include <sstream>
#include <vector>
#include <ostream>
#include <iterator>
#include <algorithm>


class Smooty
{
private:
	int pret;
public:
	Smooty(int pret) : pret{ pret } {};
	virtual int getPret() const { return pret; }
	virtual void descriere() = 0;
	//virtual ~Smooty();
};


class BasicSmooty : public Smooty
{
private:
	string nume;
public:
	BasicSmooty(string nume, int pret) : Smooty(pret), nume{ nume } {};
	void descriere() override { cout << nume; }
	//~BasicSmooty() {};
};

class DecoratorSmooty : public Smooty
{
private:
	Smooty& smo;
public:
	DecoratorSmooty(Smooty& sm) : smo{ sm }, Smooty(sm.getPret()) {};
	virtual int getPret() { return smo.getPret(); }
	virtual void descriere() { smo.descriere(); }
	//virtual	~DecoratorSmooty() {};
};


class SmootyFrisca : public DecoratorSmooty
{
public:
	SmootyFrisca(Smooty& sm) : DecoratorSmooty(sm) {};
	int getPret() override { return 123; }
	void descriere() {
		DecoratorSmooty::descriere();
		cout << "cu frisca";
	}
	//~SmootyFrisca() {};
};