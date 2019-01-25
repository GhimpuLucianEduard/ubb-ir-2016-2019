#ifndef LAB12_H
#define LAB12_H

#include <QtWidgets/QMainWindow>
#include "ui_lab12.h"

class Lab12 : public QMainWindow
{
	Q_OBJECT

public:
	Lab12(QWidget *parent = 0);
	~Lab12();

private:
	Ui::Lab12Class ui;
};

#endif // LAB12_H
