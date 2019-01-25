#pragma once
#include "Repository.h"

class Controller
{
private:
	Repository& repo;
	Validator& vali;
public:
	Controller(Repository& repo, Validator& vali) : repo{ repo }, vali{ vali } {};

	void add(const string& nume, const string& info)const;
	void sterge(const string& nume) const;
	const DictionarOrdonat& getAllE() const;
	const Entitate& findEC(const string& nume) const;
	void modificaE(const string& nume, const string& info) const;
};