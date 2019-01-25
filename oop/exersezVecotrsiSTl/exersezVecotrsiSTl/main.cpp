#include <iostream>
#include <vector>
#include <string>
#include "Header.h"

Car::Car(int id, string model)
{
	this->id = id;
	this->model = model;
}
int Car::getId() const
{
	return this->id;
}
string Car::getModel() const
{
	return this->model;
}
bool Car::operator==(const Car& ot)
{
	return this->getId() == ot.getId();
}
using namespace std;

int main()
{
	vector<int> nr;
	nr.push_back(1);
	nr.push_back(2);
	nr.push_back(3);
	nr.push_back(4);

	for (const auto& val : nr)
	{
		cout << val << endl;
	}

	cout << "nr cautat" << endl;
	auto value = find(nr.begin(), nr.end(), 3);
	cout << *value;


	vector<Car> cars;
	Car c1{ 1,"bmw" };
	Car c2{ 2,"bmw2" };
	Car c3{ 3,"bmw3" };

	cars.push_back(c1);
	cars.push_back(c2);
	cars.push_back(c3);

	Car cmp{ 2,"bla" };

	auto value2 = find(cars.begin(), cars.end(), cmp);
	Car c90{ *value2 };
	cout << "WOWOWOW" << endl;
	cout << c90.getModel();

	



	return 0;

	

}