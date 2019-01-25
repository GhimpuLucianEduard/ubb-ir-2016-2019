#pragma once

#include <qwidget.h>
#include <qtablewidget.h>
#include <qpushbutton.h>
#include <qlayout.h>
#include <qgridlayout.h>
#include <qlineedit.h>

#include "Controller.h"


class GUI : public QWidget
{
private:
	Controller& ctr;
	QTableWidget* table;
	void init();
	void reload(vector<Carte> all);
	QPushButton* addB;
	QLineEdit* l1;
	QLineEdit* l2;
	QLineEdit* l3;
	QPushButton* removeB;
	void con();
public:
	GUI(Controller& ctr) : ctr{ ctr }
	{
		init();
		con();
		reload(ctr.getAllCarti());
	}
	
};

class Desen : public QWidget, public Oberver
{
private:
	Controller& ctr;
	void paintEvent(QPaintEvent* ev);
public:
	Desen(Controller& ctr) : ctr{ ctr } { ctr.reg(this); };
	void update()
	{
		repaint();
	}
};