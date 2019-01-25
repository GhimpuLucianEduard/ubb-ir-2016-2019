#include "lab12.h"
#include <QtWidgets/QApplication>
#include "mod1.h"
#include <QtWidgets>

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	QLabel *label = new QLabel("dsadsadsadasdsadsa");
	label->show();
	
	Lab12 w;
	w.show();
	return a.exec();
}
