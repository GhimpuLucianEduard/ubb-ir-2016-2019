#include "ctr.h"




void Controller::adugaStudent(const string& nume, const int& medie)
{
	Student s{ nume,medie };
	vali.validate(s);
	repo.store(s);
}

void Controller::stergeStudent(const string& nume)
{
	repo.remove(repo.find(nume));
}

Student Controller::gasesteStudent(const string& nume)
{
	return repo.find(nume);
}

vector<Student> Controller::getAllStudenti()
{
	return repo.getAll();
}