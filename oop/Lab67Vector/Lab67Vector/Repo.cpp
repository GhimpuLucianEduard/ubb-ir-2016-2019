#include "Repo.h"
#include "Domain.h"
#include <algorithm>

/*
	Find method
	Pre: id -> id o a given object to be found
	Post: return the object itself if the object is in the repo, throw exeption otherwise
*/

const Car& Repository::find(string id)
{
	for (const auto& c : all)
	{
		if (c.getId() == id)
		{
			return c;
		}
	}
		throw RepoException("There is no car with id:" + id + "in the datebase"); }


/*
	Exist method -> checks if a object is already in the repo
	Pre: c -> object to check
	Post: True if it's in the repo, false otherwise
*/

bool Repository::exist(const Car& c)
{
	try
	{
		find(c.getId());
		return true;
	} 
	catch ( RepoException& )
	{
		return false; }
}


/*
	Store method
	Pre: c -> object to store
	Post: c added to the repo, or throw exception if object already in the repo
*/

void Repository::store(const Car& c)
{
	if (exist(c))
	{
		throw RepoException("There is already a object with the id: " + c.getId());
	}
	all.push_back(c);
}

/*
	getAll method -> returns all the stored objects
	Pre: ---
	Post: return the vector of objects T
*/


const vector<Car>& Repository::getAll()
{
	return all;
}

/*
	method used to overload the << operator
*/

ostream& operator<<(ostream& out, const RepoException& ex)
{
	out << ex.errMsg;
	return out;
}
/*
	method used to delete a car from the database

*/
void Repository::erase(string id)
{
	int index = 0;
	Car c = find(id);
	if (exist(c))
	{
		for (const auto& c : all)
		{
			if (c.getId() != id)
			{
				index++;
			}
		}
		all.erase(all.begin() + index);
	}
	
}

void Repository::update(const Car& c)
{
	erase(c.getId());
	store(c);

}