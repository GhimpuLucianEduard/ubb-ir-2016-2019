#pragma once
#include "Controller.h"

class Ui
{
private:
	CarController& ctr;

	//add car to ctr
	void addUi();
	//get all method
	void getAllUi(const vector<Car>& cars);
	//delete method Ui
	void deleteUi();
	//
	void updateUi();
	//
	void producerFiltUi();
	void typeFiltUi();
	
public:
	//constructor
	Ui(CarController& ctr) : ctr{ ctr } {};
	
	//copy not allowed
	Ui(const Ui& ot) = delete;

	
	void showMenu();
	void startMenu();

};