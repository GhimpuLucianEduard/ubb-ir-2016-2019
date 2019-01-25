#ifndef EXAMEN_OOP_2017_H
#define EXAMEN_OOP_2017_H

#include <QtWidgets/QMainWindow>
#include "ui_examen_oop_2017.h"

class Examen_OOP_2017 : public QMainWindow
{
	Q_OBJECT

public:
	Examen_OOP_2017(QWidget *parent = 0);
	~Examen_OOP_2017();

private:
	Ui::Examen_OOP_2017Class ui;
};

#endif // EXAMEN_OOP_2017_H
