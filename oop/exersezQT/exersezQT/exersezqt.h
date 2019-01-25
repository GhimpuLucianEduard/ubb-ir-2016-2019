#ifndef EXERSEZQT_H
#define EXERSEZQT_H

#include <QtWidgets/QMainWindow>
#include "ui_exersezqt.h"

class exersezQT : public QMainWindow
{
	Q_OBJECT

public:
	exersezQT(QWidget *parent = 0);
	~exersezQT();
	void showMenu();

private:
	Ui::exersezQTClass ui;
};

#endif // EXERSEZQT_H
