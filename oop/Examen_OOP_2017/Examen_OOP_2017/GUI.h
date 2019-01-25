#pragma once
#include "Controller.h"
#include <qwidget.h>
#include <qtabwidget.h>
#include <qlayout.h>
#include <qgridlayout.h>
#include <qpushbutton.h>
#include <qlineedit.h>
#include <qtablewidget.h>
#include <qpainter.h>
class GUI :public QWidget, public Observer
{
private:
	QTableWidget* table;
	Controller& ctr;
	void load(const vector<Song>& all);
	void init();
	QLineEdit* ltitlu;
	QLineEdit* lrank;
	QPushButton* updateB;
	void con();
	QPushButton* removeB;

	void paintEvent(QPaintEvent* ev)override;
public:
	GUI(Controller& ctr) : ctr{ ctr }
	{
		ctr.reg(this);
		init();
		load(ctr.sortRank());
		con();
	}
	void update() override
	{
		repaint();
	}

};

/*
class GUI2 :public QWidget, public Observer
{
private:
	Controller& ctr;
	void init2();
	void paintEvent(QPaintEvent* ev) override;
	
public:
	GUI2(Controller& ctr) : ctr{ ctr }
	{
		ctr.reg(this);
		init2();
	}
	void update() override
	{
		repaint();
	}
};
*/