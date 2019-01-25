#include "lab12qt.h"
#include <QtWidgets/QApplication>

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	lab12QT w;
	w.show();
	return a.exec();
}
