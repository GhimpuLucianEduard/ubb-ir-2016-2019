#ifndef REPETOOP3_H
#define REPETOOP3_H

#include <QtWidgets/QMainWindow>
#include "ui_repetoop3.h"

class repetOOP3 : public QMainWindow
{
	Q_OBJECT

public:
	repetOOP3(QWidget *parent = 0);
	~repetOOP3();

private:
	Ui::repetOOP3Class ui;
};

#endif // REPETOOP3_H
