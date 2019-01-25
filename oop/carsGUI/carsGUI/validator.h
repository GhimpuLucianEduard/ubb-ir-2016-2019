#pragma once

#include "domain.h"
#include <vector>

class Validator
{
public:
	void validate(const Car& c)
	{
		string errMsg;

		if (c.getId().size() == 0) errMsg += "The id plate cant be void!";
		if (c.getProducer().size() == 0) errMsg += "The producer cant be void!";
		if (c.getModel().size() == 0) errMsg += "The model cant be void!";
		if (c.getType().size() == 0) errMsg += "The type cant be void!";

		if (errMsg.size() > 0)
		{
			throw CarException(errMsg);
		}
	}
};