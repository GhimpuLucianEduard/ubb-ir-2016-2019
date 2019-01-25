#include "Ui.h"
#include <iostream>
#include <string>

using namespace std;


void Ui::deleteUi()
{	
	string id;
	cout << "Type the id of the car to be deleted:  " << endl;
	cin >> id;
	ctr.deleteCar(id);
	cout << "Car deleted!" << endl;
}
void Ui::addUi()
{
	string id, producer, model, type;

	cout << "Type the id of the car:  " << endl;
	cin >> id;
	cout << "Type the producer of the car:  " << endl;
	cin >> producer;
	cout << "Type the model of the car:  " << endl;
	cin >> model;
	cout << "Type the type of the car:  " << endl;
	cin >> type;
	ctr.addCar(id, producer, model, type);
	cout << "Car has been stored!" << endl;
}

void Ui::getAllUi(const vector<Car>& cars)
{
	cout << "ID:     Producer:     Model:     Type:    " << endl;
	for (const auto& car : cars)
	{
		cout << car.getId() << "    " << car.getProducer() << "    " << car.getModel() << "     " << car.getType();
		cout << endl;
	}
}

void Ui::updateUi()
{
	string id, producer, model, type;

	cout << "Type the id of the car to be updated:  " << endl;
	cin >> id;
	cout << "Type the new producer of the car:  " << endl;
	cin >> producer;
	cout << "Type the new model of the car:  " << endl;
	cin >> model;
	cout << "Type the new type of the car:  " << endl;
	cin >> type;
	ctr.update(id, producer, model, type);
	cout << "Car has been updated!" << endl;
}


void Ui::producerFiltUi()
{
	vector<Car> rez;
	string filt;
	cout << "Type the producer: " << endl;
	cin >> filt;
	rez = ctr.producerFilt(filt);
	cout << "ID:     Producer:     Model:     Type:    " << endl;
	for (const auto& car : rez)
	{
		cout << car.getId() << "    " << car.getProducer() << "    " << car.getModel() << "     " << car.getType();
		cout << endl;
	}

}

void Ui::typeFiltUi()
{
	vector<Car> rez;
	string filt;
	cout << "Type the producer: " << endl;
	cin >> filt;
	rez = ctr.typeFilt(filt);
	cout << "ID:     Producer:     Model:     Type:    " << endl;
	for (const auto& car : rez)
	{
		cout << car.getId() << "    " << car.getProducer() << "    " << car.getModel() << "     " << car.getType();
		cout << endl;
	}

}
void Ui::showMenu()
{
	cout << "#################SUPER CONSOLE 4.0b (or 4.0c not sure)###################" << endl;
	cout << "1. Add a car to the database" << endl;
	cout << "2. Print all the cars on the database" << endl;
	cout << "3. Delete a car " << endl;
	cout << "4. Update a car " << endl;
	cout << "5. Show cars by a given producer " << endl;
	cout << "6. Show cars by a given type" << endl;
	cout << "7. Sort by id " << endl;
	cout << "8. sort by producer and model " << endl;
	cout << "0. Exit" << endl;
	cout << "Please insert a cmd: " << endl;

}

void Ui::startMenu()
{
	while (true)
	{
		showMenu();
		int cmd;
		cin >> cmd;
		try
		{
			switch (cmd)
			{
			case 1:
				addUi();
				break;
			case 2:
				getAllUi(ctr.getAll());
				break;
			case 3:
				deleteUi();
				break;
			case 4:
				updateUi();
				break;
			case 5:
				producerFiltUi();
				break;
			case 6:
				typeFiltUi();
				break;
			case 7:
				getAllUi(ctr.sortById());
				break;
			case 8:
				getAllUi(ctr.sortByProducatorModel());
				break;
			case 0:
				return;
			default:
				cout << "Invalid cmd!" << endl;
			}
		}
		catch (ValidatorException& ex)
		{
			cout << ex << endl;
		}
		catch (RepoException& ex)
		{
			cout << ex << endl;
		}
		
	}
}