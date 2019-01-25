#include "optest2.h"
#include <QtWidgets/QApplication>
#include "GUI.h"
int main(int argc, char *argv[])
{
	QApplication a(argc, argv);

	Repository repo{"carti.txt"};
	
	
	Controller ctr(repo);
	GUI gui(ctr);
	Desen d{ ctr };
	d.show();
	gui.show();
	
	return a.exec();
}
