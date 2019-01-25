#include "ceva.h"
#include <QtWidgets/QApplication>
#include "GUI.h"
int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	FileRepo repo{ "studenti.txt" };
	Validator vali{};
	Controller ctr{ repo,vali };
	GUI gui{ ctr };
	gui.show();
	return a.exec();
}
