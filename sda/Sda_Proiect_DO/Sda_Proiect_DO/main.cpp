#include "sda_proiect_do.h"
#include <QtWidgets/QApplication>
#include "GUI.h"
int main(int argc, char *argv[])
{
	
	/*
	DictionarOrdonat dict{ 10,relatie };
	dict.adauga( "1","2" );
	dict.adauga("42314", "2");
	dict.adauga("vafds", "2");
	dict.adauga("1fdgdfg", "2");
	dict.adauga("gsfdg", "2");
	dict.adauga("gdfgfd", "2");
	dict.adauga("gsfdgsdd", "2");
	*/
	
	QApplication a(argc, argv);
	Repository repo{ "import.txt" };
	Validator vali{};
	Controller ctr{ repo,vali };
	GUI gui{ ctr };

	gui.show();



	return a.exec();
}
