#ifndef CARTI_H
#define CARTI_H

#include <QtWidgets/QMainWindow>
#include "ui_carti.h"

class carti : public QMainWindow
{
	Q_OBJECT

public:
	carti(QWidget *parent = 0);
	~carti();

private:
	Ui::cartiClass ui;
};

#endif // CARTI_H
