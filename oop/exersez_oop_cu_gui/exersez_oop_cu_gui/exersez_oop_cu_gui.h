#ifndef EXERSEZ_OOP_CU_GUI_H
#define EXERSEZ_OOP_CU_GUI_H

#include <QtWidgets/QMainWindow>
#include "ui_exersez_oop_cu_gui.h"

class exersez_oop_cu_gui : public QMainWindow
{
	Q_OBJECT

public:
	exersez_oop_cu_gui(QWidget *parent = 0);
	~exersez_oop_cu_gui();

private:
	Ui::exersez_oop_cu_guiClass ui;
};

#endif // EXERSEZ_OOP_CU_GUI_H
