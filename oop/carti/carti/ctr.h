#pragma once
#include "repo.h"
#include "validator.h"

class Controller
{
private:
	Repository& repo;
	Validator& vali;
public:

	Controller(Repository& repo, Validator& vali) : repo{ repo }, vali{ vali } {};

	void storeCarte(const string& autor, const string& titlu, const int& nrPag);
	void cumparaCarte(const string& titlu);
	Carte findCarte(const string& titlu);
	vector<Carte> getAllCarti();
	vector<Carte> pagFilt(const int& nr);
};