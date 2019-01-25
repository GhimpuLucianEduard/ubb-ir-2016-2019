#pragma once
#include "Controller.h"
#include "qwidget.h"
#include "qlistwidget.h"
#include "qlayout.h"
#include "qgridlayout.h"
#include <qlineedit.h>
#include <qpushbutton.h>
#include <qlabel.h>
class GUI : public QWidget, public Observer
{	
private:
	Controller& ctr;
	void initGUI();
	QListWidget* lst;
	QLineEdit* textValue;
	QLineEdit* textStu;
	QLineEdit* textExaminator;
	QPushButton* addB;
	QLineEdit* search;
	
	
	void loadList(const vector<Nota> all);
	void con();
	
public:
	void smecherie(vector<Aux> rez);
	GUI(Controller& ctr) : ctr{ctr}
	{	
		ctr.reg(this);
		initGUI();
		loadList(ctr.getSorted());
		con();
		smecherie(ctr.getUlti());
	}
	void update() {
		smecherie(ctr.getUlti());
	}

};


class GUI2 : public QWidget
{
private:
	
	//QLabel* nrL;
	void initGUI2();
	
public:
	QLabel* nrL;
	GUI2()
	{
		initGUI2();
	}
};