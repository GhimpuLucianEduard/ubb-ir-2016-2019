#ifndef OPTEST2_H
#define OPTEST2_H

#include <QtWidgets/QMainWindow>
#include "ui_optest2.h"

class optest2 : public QMainWindow
{
	Q_OBJECT

public:
	optest2(QWidget *parent = 0);
	~optest2();

private:
	Ui::optest2Class ui;
};

#endif // OPTEST2_H
