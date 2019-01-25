#ifndef LAB12QT_H
#define LAB12QT_H

#include <QtWidgets/QMainWindow>
#include "ui_lab12qt.h"

class lab12QT : public QMainWindow
{
	Q_OBJECT

public:
	lab12QT(QWidget *parent = 0);
	~lab12QT();

private:
	Ui::lab12QTClass ui;
};

#endif // LAB12QT_H
