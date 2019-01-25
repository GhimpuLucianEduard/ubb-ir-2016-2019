#pragma once
#include <string.h>
using namespace std;
class Tractor
{
	string marca;
	int an;

public:

	string getMarca() { return marca; }
	int getAn() { return an; }

	Tractor(string marca, int an) : marca{ marca }, an{ an }, {};
	//Tractor t{"scb",2015};
};