#pragma once

#include <QtWidgets\qlistwidget.h>
#include <qwidget.h>
#include "ctr.h"
#include <QtWidgets\qlabel.h>
#include <QtWidgets/qlineedit.h>
#include <QtWidgets\qpushbutton.h>

class GUI :public QWidget
{
private:
	Controller& ctr;
	void initGUI();
	QListWidget* lst;
	void reload(const vector<Carte>& all);
	void connect();
	QLineEdit* autor;
	QLineEdit* titlu;
	QLineEdit* nrPag;
	QPushButton* addB;
	QLineEdit* filt;
	QPushButton* sort;
	QLineEdit* suma;
public:
	GUI( Controller& ctr ) : ctr{ ctr }
	{
		initGUI();
		reload(ctr.getAllCarti());
		connect();
	}

};