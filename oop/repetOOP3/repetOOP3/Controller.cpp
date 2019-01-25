
#include "Controller.h"
#include <algorithm>
#include <iterator>
void Controller::addProdus(const int& id, const string & des, const int& count, const float& price)
{
	Produs p{ id,des,count,price };
	vali.validate(p);
	repo.store(p);
	noti();
}
vector<Produs> Controller::getAll() const
{
	return repo.getAll();
}
vector<Produs> Controller::getSorted() const
{
	auto rez = repo.getAll();
	sort(rez.begin(), rez.end(), [&](const Produs& p1, const Produs& p2) {

		return p1.getDes() < p2.getDes();

	});
	return rez;
}

void Controller::inc(const int& id, const int& nr)
{
	
	repo.up(id, nr);
	noti();
}

vector<Produs> Controller::getFilt(const int& filt) const
{
	vector<Produs> rez;
	copy_if(repo.getAll().begin(), repo.getAll().end(), back_inserter(rez), [&](const Produs& p) {

		return p.getCount() < filt; 
	
	
	});
	return rez;
}

void Controller::exportare(const string& fileName) const
{
	ofstream out(fileName);
	for (const auto& p : repo.getAll())
	{
		out << p.getId() << endl;
		out << p.getDes() << endl;
		out << p.getCount() << endl;
		out << p.getPrice() << endl;
	}
	out.close();
}