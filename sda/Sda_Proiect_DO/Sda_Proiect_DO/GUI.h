#pragma once
#include <qwidget.h>
#include <QtWidgets/qgridlayout.h>
#include <QtWidgets\qpushbutton.h>
#include <QtWidgets/qlineedit.h>
#include <QtWidgets\qlabel.h>
#include <QtWidgets/qlistwidget.h>
#include <QtWidgets\qmessagebox.h>
#include "Controller.h"
#include <QtWidgets\qtextedit.h>
#include <qpixmap.h>

class GUI :public QWidget
{
private:
	const Controller& ctr;
	QListWidget* lst;
	QPushButton* addB;
	QPushButton* removeB;
	QLineEdit* nume;
	QTextEdit* info;
	QLabel* label;
	QPushButton* addB2;
	QPushButton* cauta;
	QPushButton* modi;


	void reloadList(const DictionarOrdonat& od) const ;
	void initGUI();
	void connections();
public:
	GUI(const Controller& ctr) : ctr{ ctr }
	{
		initGUI();
		connections();
		reloadList(ctr.getAllE());

	
	}


};