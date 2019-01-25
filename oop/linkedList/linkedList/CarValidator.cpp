#include "CarValidator.h"


void CarValidator::validate(const Car& c)
{
	vector <string> errMsg;

	if (c.getId().size() == 0) errMsg.push_back("The id plate cant be void!");
	if (c.getProducer().size() == 0) errMsg.push_back("The producer cant be void!");
	if (c.getModel().size() == 0) errMsg.push_back("The model cant be void!");
	if (c.getType().size() == 0) errMsg.push_back("The type cant be void!");

	if (errMsg.size() > 0)
	{
		throw ValidatorException(errMsg);
	}

}

ostream& operator<<(ostream& out, const ValidatorException& ex)
{
	for (const auto& msg : ex.errMsg)
	{
		out << msg << "  ";
	}
	return out;
}