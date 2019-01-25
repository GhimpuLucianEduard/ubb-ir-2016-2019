#pragma once

#include "repo.h"


class Controller
{
private:
	Repository& repo;
	Validator& vali;

public:

	Controller(Repository& repo, Validator& vali) : repo{ repo }, vali{ vali } {};
	void adugaStudent(const string& nume, const int& medie);
	void stergeStudent(const string& nume);
	Student gasesteStudent(const string& nume);
	vector<Student> getAllStudenti();
};