#include "controller.h"

Tractor Controller::add(string marca, int an)
{
	Tractor t{ marca,an };
	v.push_back(t);
	return t;
}