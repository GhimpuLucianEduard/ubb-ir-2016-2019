#include "carti.h"
#include <QtWidgets/QApplication>
#include "ctr.h"
#include "gui.h"
int main(int argc, char *argv[])
{
	FileRepo repo{ "carte.txt" };
	Validator vali{};
	Controller ctr{ repo,vali };
	
	
	QApplication a(argc, argv);
	GUI gui{ ctr };
	gui.show();
	return a.exec();
}
