#pragma once

#include "ctr.h"
#include "QtWidgets\qlistwidget.h"
#include "qwidget.h"
#include "QtWidgets\qgridlayout.h"
#include "QtWidgets\qlayout.h"
#include "QtWidgets\qpushbutton.h"
#include "QtWidgets\qlineedit"
class GUI:public QWidget
{
private:
	Controller& ctr;
	QListWidget* lst;
	QLineEdit* adaugaL;
	QPushButton* adaugaB;
	QPushButton* filt;
	void reload();
	void init();
	void conect();
public:
	GUI(Controller& ctr) : ctr{ctr}
	{
		init();
		conect();
		reload();
	}

};