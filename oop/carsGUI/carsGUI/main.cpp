#include "carsgui.h"
#include <QtWidgets/QApplication>
#include "repo.h"
#include "GUI.h"
#include "controller.h"
int main(int argc, char *argv[])
{
	FileRepo repo{ "cars.txt" };
	Validator vali{};
	Controller ctr{ repo,vali };



	QApplication a(argc, argv);
	
	//GuiMV gmv;
	//gmv.show();

		GUI gui{ ctr };
		secondGUI gui2{ ctr };
		GUI3 gui3{ ctr };
		GUI4 gui4{ ctr };
		gui4.show();
		gui.show();
		gui2.show();
		gui3.show();

	//ctr.addCar("test", "test", "test", "test");
	return a.exec();
}
