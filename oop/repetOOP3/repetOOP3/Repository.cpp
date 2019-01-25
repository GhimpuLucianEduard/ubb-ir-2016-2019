
#include "Repo.h"

#include <algorithm>
#include <iterator>

void Repository::store(const Produs& p)
{
	auto rez = find_if(all.begin(), all.end(), [&](const Produs& pot) {

		return pot.getId() == p.getId();

	});

	if (rez != all.end())
	{
		throw Exceptions("Produsul exista deja in repo!");
	}
	all.push_back(p);
	write();


}
vector<Produs> Repository::getAll() const
{
	return all;
}


void Repository::load()
{
	ifstream in(fName);
	while (!in.eof())
	{
		int id;
		in >> id;
		string desc;
		in >> desc;
		int cont;
		in >> cont;
		float price;
		in >> price;
		if (in.eof())
		{
			break;
		}
		Produs p(id, desc, cont, price);
		all.push_back(p);
	
	}
	in.close();
}
void Repository::write()
{
	ofstream out(fName);
	for (const auto& p : all)
	{
		out << p.getId() << endl;
		out << p.getDes() << endl;
		out << p.getCount() << endl;
		out << p.getPrice() << endl;
	}
	out.close();

}

void Repository::up(const int& id, const int& nr)
{
	
	if (nr < 0)
	{
		throw Exceptions("cantaitea introdusa tre sa fie pozitia");
	}
	
	auto rez = find_if(all.begin(),all.end(), [&](const Produs& p){

		return p.getId() == id;
	
	});

	Produs nou{ id,rez->getDes(),rez->getCount() + nr,rez->getPrice() };
	all.erase(rez);
	all.push_back(nou);
	write();

}
