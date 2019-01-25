#pragma once


#include "Domain.h"

class Validator
{
public:
	void validate(const Song& s)
	{
		string err;
		if (s.getId() < 0)
		{
			err += "Id-ul nu pote fi negativ! ";
		}
		if (s.getTitlu().size() == 0)
		{
			err += " Titlu nu poate fi vid! ";
		}
		if (s.getArtist().size() == 0)
		{
			err += " Artistul nu poate fi vid! ";
		}
		if (s.getRank() < 0|| s.getRank()>10)
		{
			err += " Rankul nu poate fi mai mic ca 0 sau mai mare ca 10!";
		}
		if (err.size() > 0)
		{
			throw Exceptions(err);
		}
	
	}
};