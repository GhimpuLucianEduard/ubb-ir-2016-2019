#ifndef SDA_PROIECT_DO_H
#define SDA_PROIECT_DO_H

#include <QtWidgets/QMainWindow>
#include "ui_sda_proiect_do.h"

class Sda_Proiect_DO : public QMainWindow
{
	Q_OBJECT

public:
	Sda_Proiect_DO(QWidget *parent = 0);
	~Sda_Proiect_DO();

private:
	Ui::Sda_Proiect_DOClass ui;
};

#endif // SDA_PROIECT_DO_H
