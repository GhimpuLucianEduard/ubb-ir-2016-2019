#pragma once
#include <vector>
#include "clase.h"


class Controller {

	vector<Tractor> v;
	
public:
		Controller() = default; //apeleaza contructorul implicit al vectorului

	Tractor add(string marca, int an); // controller ctrl; ctrl.add("JCB",2014); 

	vector <Tractor> getAll() { return v; }
};