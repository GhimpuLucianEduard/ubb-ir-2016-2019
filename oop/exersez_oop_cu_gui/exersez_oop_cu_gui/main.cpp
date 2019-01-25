#include "exersez_oop_cu_gui.h"
#include <QtWidgets/QApplication>
#include "teste.h"
#include "GUI.h"
int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	//exersez_oop_cu_gui w;
	
	teste();

	Repository repo("Text.txt");
	Validator vali{};
	Controller ctr{ repo,vali };
	GUI gui{ ctr };
	//gui.smecherie(ctr.getUlti());




	gui.show();


	//w.show();
	return a.exec();
}
