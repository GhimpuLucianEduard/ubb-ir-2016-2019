#ifndef CARSGUI_H
#define CARSGUI_H

#include <QtWidgets/QMainWindow>
#include "ui_carsgui.h"

class carsGUI : public QMainWindow
{
	Q_OBJECT

public:
	carsGUI(QWidget *parent = 0);
	~carsGUI();

private:
	Ui::carsGUIClass ui;
};

#endif // CARSGUI_H
