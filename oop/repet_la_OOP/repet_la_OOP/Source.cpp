#include <iostream>
using namespace std;
#include <sstream>
#include <vector>
#include <ostream>
#include <iterator>
#include <algorithm>

#include "Header.h"



int main()
{
	Smooty* cap = new BasicSmooty("Cu capsuni", 10);
	Smooty* kiwi = new BasicSmooty("Cu kiwi", 12);
	Smooty* s1 = new SmootyFrisca(*cap);


	vector<Smooty*> rez;
	rez.push_back(cap);
	rez.push_back(kiwi);
	rez.push_back(s1);
	for (const auto& s : rez)
	{
		s->descriere();
		cout << " " << "costa: " << s->getPret();
	}
}