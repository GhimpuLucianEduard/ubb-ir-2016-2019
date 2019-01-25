#ifndef CEVA_H
#define CEVA_H

#include <QtWidgets/QMainWindow>
#include "ui_ceva.h"

class ceva : public QMainWindow
{
	Q_OBJECT

public:
	ceva(QWidget *parent = 0);
	~ceva();

private:
	Ui::cevaClass ui;
};

#endif // CEVA_H
