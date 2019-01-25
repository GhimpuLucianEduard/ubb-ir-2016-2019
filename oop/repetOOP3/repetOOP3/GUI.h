#pragma once

#include "Controller.h"
#include <qpainter.h>
#include <qslider.h>
#include <qwidget.h>
#include <qpushbutton.h>
#include <qgridlayout.h>
#include <qlineedit.h>
#include <qtablewidget.h>
#include <qlayout.h>
#include <qlabel.h>

class GUI :public QWidget
{
private:
	QTableWidget* table;
	void init();
	Controller& ctr;
	void reload(const vector<Produs>& all);
	QLineEdit* linc;
	QPushButton* incB;
	void conect();
	QLabel* total;
	QSlider* slider;
	QLabel* labelSlider;
public:
	GUI(Controller& ctr) : ctr{ ctr } {

		init();
		reload(ctr.getSorted());
		conect();
	}
};


class GUI2 : public QWidget, public Observer 
{

private:
	Controller& ctr;
	void init2();
	QLabel* label;
	void cal();
	
public:
	GUI2(Controller& ctr) : ctr{ ctr } {

		ctr.reg(this);
		init2();
		cal();
	}
	void update()
	{
		cal();
	}


};

class GUI3 : public QWidget, public Observer
{
private:
	Controller& ctr;
	void paintEvent(QPaintEvent* ev) override;
public:
	GUI3(Controller& ctr) : ctr{ ctr } {
		ctr.reg(this);
	}

	
	void update() { repaint(); }
};