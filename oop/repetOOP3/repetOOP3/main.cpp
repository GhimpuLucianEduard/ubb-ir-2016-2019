#include "repetoop3.h"
#include <QtWidgets/QApplication>
#include "Domain.h"
#include <assert.h>
#include "Repo.h"
#include "Controller.h"
#include "GUI.h"

void teste()
{
	Produs p1{ 1,"apa",10,(float)1.2 };

	assert(p1.getId() == 1);
	assert(p1.getDes() == "apa");
	assert(p1.getCount() == 10);
	assert(p1.getPrice() == (float)1.2);


	Repository repo1{ "testCitire.txt" };
	assert(repo1.getAll()[0].getId() == 1);
	//Repository repo2{ "testScriere.txt" };
	//repo2.store(p1);
	Produs perror{ 1,"sdas",3,(float)1.4 };
	try
	{
		repo1.store(perror);
	}
	catch (Exceptions& ex)
	{
		assert(true);
	}

	Validator vali1{};
	Controller ct1{ repo1,vali1 };
	assert(ct1.getAll()[0].getId() == 1);
	auto aux = ct1.getSorted();
	assert(ct1.getSorted()[0].getDes() == "Aaa");
}










int main(int argc, char *argv[])
{
	QApplication a(argc, argv);

	teste();
	Repository repo{ "produse.txt" };
	Validator vali{};
	Controller ctr{ repo,vali };
	GUI gui{ ctr };
	GUI2 gui2{ ctr };
	GUI3 gui3{ ctr };
	gui2.show();
	gui.show();
	gui3.show();



	ctr.exportare("expr.txt");
	return a.exec();
}



