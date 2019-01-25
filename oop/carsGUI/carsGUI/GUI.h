#pragma once

#include "domain.h"
#include "controller.h"
#include <qwidget.h>
#include <QtWidgets/qgridlayout.h>
#include <QtWidgets\qpushbutton.h>
#include <QtWidgets/qlineedit.h>
#include <QtWidgets\qlabel.h>
#include <QtWidgets/qlistwidget.h>
#include <QtWidgets\qtablewidget.h>
#include <QtWidgets\qmessagebox.h>
#include <qdebug.h>
using namespace std;


class GUI :public QWidget, public Observer
{
private:
	Controller& ctr;
	void initGUI();
	QListWidget* lst;
	QPushButton* removeB;
	QPushButton* idSort;
	QPushButton* sort2B;
	QLabel* param1L;
	QLabel* param2L;
	QLabel* param3L;
	QLabel* param4L;
	QLineEdit* param1;
	QLineEdit* param2;
	QLineEdit* param3;
	QLineEdit* param4;
	QPushButton* addB;
	QPushButton* updateB;
	QPushButton* f1B;
	QPushButton* f2B;
	QPushButton* f3B;
	QLineEdit* filt1;
	QLineEdit* filt2;
	QLineEdit* filt3;
	void connections();
	void reloadList(const vector<Car>& all);
	void addCarGUI();
	void deleteCarGUI();
	void updateCarGUI();
	
	
public:
	GUI(Controller& ctr) :ctr{ ctr } {

		initGUI();
		connections();
		reloadList(ctr.getAllCars());
		ctr.reg(this);
	}
	
	void update() override
	{
		reloadList(ctr.getAllCars());
		//QMessageBox::warning(this, "Warning", "am fost notificatt");


	}

};


class secondGUI :public QWidget, public Observer
{
private:
	Controller& ctr;
	void initGUI2();
	QPushButton* desen;
	QListWidget* lst2;
	QPushButton* random;
	//QPushButton* removeFromCos;
	void connections2();
	void reloadList2(const vector<Car>& all);
	//void paintEvent(QPaintEvent* ev) override;
	QWidget* w;




public:
	secondGUI(Controller& ctr) :ctr{ ctr } {

		initGUI2();
		connections2();
		reloadList2(ctr.getCosC());
		ctr.reg(this);
	}

	void update() override
	{
		reloadList2(ctr.getCosC());
		//QMessageBox::warning(this, "Warning", "am fost notificatt");
	}
};

class GUI3 :public QWidget, public Observer
{
private:
	Controller& ctr;
	void initGUI3();
	void paintEvent(QPaintEvent* ev) override;
public:

	GUI3(Controller& ctr) :ctr{ ctr } { ctr.reg(this); };
	
	void update() override
	{
		repaint();
		//QMessageBox::warning(this, "Warning", "am fost notificatt");
	}
};


/*

class MyTableModel : public QAbstractTableModel {
	vector<Car> cars;
public:
	MyTableModel(QObject *parent, vector<Car> cars) :cars{ cars } {
		
	}
	/**
	
	int rowCount(const QModelIndex &parent = QModelIndex()) const override {
		return cars.size();
	}

	int columnCount(const QModelIndex &parent = QModelIndex()) const override {
		return 2;
	}
	
	QVariant data(const QModelIndex &index, int role = Qt::DisplayRole) const override {
		qDebug() << index.row() << " " << index.column();
		if (role == Qt::DisplayRole) {
			if (index.column() == 0) {
				return QString::fromStdString(cars[index.row()].getProducer());
			}
		}
		return QVariant();
	}
};


class GuiMV :public QWidget {
	QTableView* tabel = new QTableView;
	Controller& ctr;
public:
	GuiMV(Controller& ctr) {
		QHBoxLayout* l = new QHBoxLayout;
		setLayout(l);
		l->addWidget(tabel);
		MyTableModel* m = new MyTableModel{ this,ctr.getAllCars() };
		tabel->setModel(m);
	}

};
*/



class GUI4 :public QWidget, public Observer
{
private:
	Controller& ctr;
	void initGUI4();
	QTableWidget* table;
	void reloadTable(vector<Car> all);
	QLineEdit l1;

public:
	GUI4(Controller& ctr) :ctr{ ctr } {

		initGUI4();
		reloadTable(ctr.getAllCars());
		ctr.reg(this);
	}

	void update() override
	{
		
		//QMessageBox::warning(this, "Warning", "am fost notificatt");
		reloadTable(ctr.getAllCars());

	}

};