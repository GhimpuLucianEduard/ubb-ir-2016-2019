#pragma once

#include "carte.h"
class Validator
{
public:
	void validate(const Carte& c)
	{
		string errMsg;

		if (c.getAutor() == "")
			errMsg += "Numele autorului nu poate fi vid!";
		if (c.getTitlu() == "")
			errMsg += "Titlul nu poate fi vid";
		if (c.getNrPag() <= 0)
			errMsg += "Numarul de pagini trebuie sa fie un numar strict mai mare ca 0";

		if (errMsg.size() > 0)
		{
			throw CarteException(errMsg);
		}
	}
};