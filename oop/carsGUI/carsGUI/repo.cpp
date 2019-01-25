#include "repo.h"
#include <fstream>
void Repository::store(const Car& c)
{
	auto found = find_if(all.begin(), all.end(), [c](const Car& car) 
	{
		return car.getId()==c.getId();
	});

	if (found != all.end())
	{
		throw CarException{ "The car it's already in the database!" };

	}
	
	all.push_back(c);
}

void Repository::remove(const Car& c)
{
	auto found = find_if(all.begin(), all.end(), [c](const Car& car)
	{
		return car.getId() == c.getId();
	});

	if (found == all.end())
	{
		throw CarException{ "The car is not the database!" };
	}
	
	all.erase(found);
}

const Car& Repository::find(const string& id) const
{
	auto found = find_if(all.begin(), all.end(), [id](const Car& car)
	{
		return car.getId() == id;
	});

	if (found == all.end())
	{
		throw CarException{ "The car is not in the database!" };
	}
	return *found;
}
void Repository::update(const Car& c)
{
	remove(c);
	store(c);
}

vector<Car> Repository::getAll() const 
{
	return all;
}

void FileRepo::load()
{
	ifstream in(fName);
	if (!in.is_open())
	{
		throw CarException{ "Unable to open the file" };
	}
	
	while (!in.eof())
	{
		string id;
		in >> id;
		string producer;
		in >> producer;
		string model;
		in >> model;
		string type;
		in >> type;
		if (in.eof()) {	
			break;
		}
		Car c{ id,producer,model,type };
		Repository::store(c);
	}
	in.close();
}

void FileRepo::write()
{
	ofstream out(fName);

	if (!out.is_open())
	{
		throw	CarException{ "Unable to open the file!" };
	}

	for (const auto& car : getAll())
	{
		out << car.getId();
		out << endl;
		out << car.getProducer();
		out << endl;
		out << car.getModel();
		out << endl;
		out << car.getType();
		out << endl;

	}
	out.close();
}