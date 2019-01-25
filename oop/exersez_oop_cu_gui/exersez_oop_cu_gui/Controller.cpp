#include "Controller.h"
#include <algorithm>
#include <iterator>

void Controller::addNota(const float& value, const string& student, const string& examinator)
{
	Nota n{ value,student,examinator };
	vali.validate(n);
	repo.store(n);
	notifyAll();
}


vector<Nota> Controller::getSorted() const
{
	auto rez = repo.getAll();
	sort(rez.begin(), rez.end(), [&](const Nota& n1, const Nota& n2) {
	
		return n1.getValoare() > n2.getValoare();
	
	});

	return rez;
}


vector<Nota> Controller::getFind(const string& arg) const
{
	vector<Nota> rez;
	/*
	std::copy_if(repo.getAll().begin(), repo.getAll().end(), back_inserter(rez), [&](const Nota& n) {
		
		
		
		if (arg.find(n.getStudent()) != string::npos)
		{
			return true;
		}
		else
		{
			return false;
		}
	});
	*/

	for (const auto& nota : repo.getAll())
	{
		
		
		
		if (nota.getStudent().find(arg) != std::string::npos)
		{
			Nota noua{ nota.getValoare(),nota.getStudent(),nota.getExaminator() };
			rez.push_back(noua);
		}
	}

	return rez;


}



vector<Aux> Controller::getUlti() const
{
	vector<Aux> rez;
	vector<string> profi;
	for (const auto& nota : repo.getAll())
	{
		profi.push_back(nota.getExaminator());
	}
	profi.erase(unique(profi.begin(), profi.end()), profi.end());

	for (const auto& prof : profi)
	{
		Aux a{};
		a.setProf(prof);
		rez.push_back(a);
	}
	
	
	
	for (auto& aux : rez)
	{
		for (const auto& nota : repo.getAll())
		{
			if (nota.getExaminator() == aux.prof)
			{
				aux.incNr();
			}
		}
	}
	
	
	
	
	return rez;
}