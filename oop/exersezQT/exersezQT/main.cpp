#include "exersezqt.h"
#include <QtWidgets/QApplication>
#include <QtWidgets\qformlayout.h>
#include <QtWidgets\qgridlayout.h>
#include <QtWidgets/qlineedit.h>
#include <QtWidgets/qlistwidget.h>
#include <QtWidgets\qpushbutton.h>
#include <QtWidgets\qlabel.h>
#include <QObject>

#include <QtWidgets\qlineedit.h>
#include "ceva.h"

void showMenu()
{
	
	
	Repository repo{};
	repo.store("string1");
	repo.store("string2");
	repo.store("string3");



	QWidget* wnd = new QWidget;
	QGridLayout* ly = new QGridLayout;
	wnd->setLayout(ly);

	QListWidget* list = new QListWidget;
	ly->addWidget(list,1,1,6,6);
	list->addItem("Item1");
	list->addItem("Item1");
	list->addItem("Item1");
	

	QPushButton* removeB = new QPushButton("Remove");
	ly->addWidget(removeB, 7, 1, 1, 2);
	
	QPushButton* sort1B = new QPushButton("Sort1");
	ly->addWidget(sort1B, 7, 3, 1, 2);
	
	QPushButton* sort2B = new QPushButton("Sort2");
	ly->addWidget(sort2B, 7, 5, 1, 2);
	
	
	QLabel* param1L = new QLabel("param1");
	QLabel* param2L = new QLabel("param2");
	QLabel* param3L = new QLabel("param3");
	QLabel* param4L = new QLabel("param4");
	ly->addWidget(param1L, 1, 7, 1, 2);
	ly->addWidget(param2L, 2, 7, 1, 2);
	ly->addWidget(param3L, 3, 7, 1, 2);
	ly->addWidget(param4L, 4, 7, 1, 2);
	

	QLineEdit* param1 = new QLineEdit;
	QLineEdit* param2 = new QLineEdit;
	QLineEdit* param3 = new QLineEdit;
	QLineEdit* param4 = new QLineEdit;
	ly->addWidget(param1, 1, 9, 1, 4);
	ly->addWidget(param2, 2, 9, 1, 4);
	ly->addWidget(param3, 3, 9, 1, 4);
	ly->addWidget(param4, 4, 9, 1, 4);
	

	QPushButton* addB = new QPushButton("Add");
	
	ly->addWidget(addB, 5, 7, 1, 3);

	QPushButton* updateB = new QPushButton("Update");
	ly->addWidget(updateB, 5, 10, 1, 3);

	QPushButton* f1B = new QPushButton("filt1");
	ly->addWidget(f1B, 6, 7, 1, 2);

	QPushButton* f2B = new QPushButton("filt2");
	ly->addWidget(f2B, 6, 9, 1, 2);

	QPushButton* f3B = new QPushButton("filt3");
	ly->addWidget(f3B, 6, 11, 1, 2);

	


	wnd->show();
}






int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	
	showMenu();
	return a.exec();

}
