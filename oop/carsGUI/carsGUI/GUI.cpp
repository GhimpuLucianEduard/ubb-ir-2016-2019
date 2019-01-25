#include "GUI.h"
#include <QtWidgets\qlayout.h>
#include <QtWidgets\qlistwidget.h>
#include <QtWidgets\qpushbutton.h>
#include <QtWidgets\qlabel.h>
#include <QtWidgets\qlineedit.h>
#include <QtWidgets\qmessagebox.h>
#include <qpainter.h>
#include <time.h>

void GUI::initGUI()
{
	QGridLayout* ly = new QGridLayout;
	setLayout(ly);

	lst = new QListWidget;
	ly->addWidget(lst, 1, 1, 6, 6);

	removeB = new QPushButton("Remove");
	ly->addWidget(removeB, 7, 1, 1, 2);

	idSort = new QPushButton("Sort by Id");
	ly->addWidget(idSort, 7, 3, 1, 2);

	sort2B = new QPushButton("Sort by Producer and Model");
	ly->addWidget(sort2B, 7, 5, 1, 2);

	param1L = new QLabel("Id:");
	param2L = new QLabel("Producer");
	param3L = new QLabel("Model");
	param4L = new QLabel("Type");
	ly->addWidget(param1L, 1, 7, 1, 2);
	ly->addWidget(param2L, 2, 7, 1, 2);
	ly->addWidget(param3L, 3, 7, 1, 2);
	ly->addWidget(param4L, 4, 7, 1, 2);
	
	param1 = new QLineEdit;
	param2 = new QLineEdit;
	param3 = new QLineEdit;
	param4 = new QLineEdit;
	ly->addWidget(param1, 1, 9, 1, 4);
	ly->addWidget(param2, 2, 9, 1, 4);
	ly->addWidget(param3, 3, 9, 1, 4);
	ly->addWidget(param4, 4, 9, 1, 4);
	filt1 = new QLineEdit;
	filt2 = new QLineEdit;
	filt3 = new QLineEdit;

	ly->addWidget(filt1, 7, 7, 1, 2);
	ly->addWidget(filt2, 7, 9, 1, 2);
	ly->addWidget(filt3, 7, 11, 1, 2);

	addB = new QPushButton("Add");

	ly->addWidget(addB, 5, 7, 1, 3);

	updateB = new QPushButton("Update");
	ly->addWidget(updateB, 5, 10, 1, 3);

	f1B = new QPushButton("Filt by Produer");
	ly->addWidget(f1B, 6, 7, 1, 2);

	f2B = new QPushButton("filt2");
	ly->addWidget(f2B, 6, 9, 1, 2);

	f3B = new QPushButton("filt3");
	ly->addWidget(f3B, 6, 11, 1, 2);
}

void GUI::connections()
{
	QObject::connect(lst, &QListWidget::itemSelectionChanged, [&]() {
		if (lst->selectedItems().isEmpty())
		{
			//param1->setText("");
			//param2->setText("");
			//param3->setText("");
			//param4->setText("");
			return;
		}
		QListWidgetItem* selItem = lst->selectedItems().at(0);
		QString id = selItem->text();
		param1->setText(id);
		try {
			Car c = ctr.findCar(id.toStdString());
			param2->setText(QString::fromStdString(c.getProducer()));
			param3->setText(QString::fromStdString(c.getModel()));
			param4->setText(QString::fromStdString(c.getType()));
		}
		catch (CarException& ex)
		{
			QMessageBox::warning(nullptr, "Warning", QString::fromStdString(ex.getMsg()));
		}
	});

	QObject::connect(addB, &QPushButton::clicked, this, &GUI::addCarGUI);
	QObject::connect(removeB, &QPushButton::clicked, this, &GUI::deleteCarGUI);
	QObject::connect(updateB, &QPushButton::clicked, this, &GUI::updateCarGUI);
	QObject::connect(idSort, &QPushButton::clicked, [&](){reloadList(ctr.sortById());});
	QObject::connect(sort2B, &QPushButton::clicked, [&]() {
		//reloadList(ctr.sortByProdModel());
	});


	QObject::connect(f1B, &QPushButton::clicked, [&]() {

		lst->clear();
		for (const auto& car : ctr.getAllCars())
		{
			QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(car.getId()), lst);
			if (car.getProducer() == filt1->text().toStdString())
			{
				item->setBackgroundColor(QColor{ Qt::red });
			}
		}
	});



}
void GUI::addCarGUI()
{
	try {
		ctr.addCar(param1->text().toStdString(), param2->text().toStdString(), param3->text().toStdString(), param4->text().toStdString());
		//param1->setText("");
		//param2->setText("");
		//param3->setText("");
		//param4->setText("");
		//reloadList(ctr.getAllCars());
		
	}
	catch (CarException& ex) 
	{
		QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
	}
}

void GUI::deleteCarGUI()
{
	try {
		ctr.removeCar(param1->text().toStdString());
		//param1->setText("");
		//param2->setText("");
		//param3->setText("");
		//param4->setText("");
		//reloadList(ctr.getAllCars());

	}
	catch (CarException& ex)
	{
		QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
	}
}

void GUI::updateCarGUI()
{
	try {
		ctr.removeCar(param1->text().toStdString());
		ctr.addCar(param1->text().toStdString(), param2->text().toStdString(), param3->text().toStdString(), param4->text().toStdString());
		param1->setText("");
		param2->setText("");
		param3->setText("");
		param4->setText("");
		//reloadList(ctr.getAllCars());

	}
	catch (CarException& ex)
	{
		QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
	}
}

void GUI::reloadList(const vector<Car>& all)
{	

	lst->clear();
	
	for (const auto& car : all)
	{
		QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(car.getId()), lst);
		
	}
	
}

void secondGUI::initGUI2()
{
	QGridLayout* ly = new QGridLayout;
	setLayout(ly);
	random=new QPushButton("random");
	
	desen= new QPushButton("deseneaza");

	lst2 = new QListWidget;
	ly->addWidget(lst2, 1, 1, 6, 6);
	ly->addWidget(random, 3, 7, 1, 2);
	ly->addWidget(desen, 4, 7, 1, 2);




}



void secondGUI::connections2()
{
	QObject::connect(random, &QPushButton::clicked, [&]() {

		ctr.stergeCosC();
		ctr.fillC(4);
		reloadList2(ctr.getCosC());
	});
}




void secondGUI::reloadList2(const vector<Car>& all)
{



	lst2->clear();

	for (const auto& car : all)
	{
		QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(car.getId()), lst2);

	}



}


void GUI3::initGUI3()
{




}




void GUI3::paintEvent(QPaintEvent* ev)
{
	QPainter p{ this };
	

	srand(time(NULL));

	for (const auto& c : ctr.getCosC())
	{
		
		int a = rand() % 400;
		int b = rand() % 400;
		p.fillRect(a,b,10,10, Qt::BrushStyle::Dense1Pattern);
		
	}


}



void GUI4::initGUI4()
{
	QVBoxLayout* vl = new QVBoxLayout;
	

	table = new QTableWidget;
	table->setRowCount(10);
	table->setColumnCount(4);
	vl->addWidget(table);
	setLayout(vl);
}


void GUI4::reloadTable(vector<Car> all)
{	
	int row = 0;
	for (const auto& car : all)
	{
		
		QTableWidgetItem* newItem1 = new QTableWidgetItem;
		QTableWidgetItem* newItem2 = new QTableWidgetItem;
		QTableWidgetItem* newItem3 = new QTableWidgetItem;
		QTableWidgetItem* newItem4 = new QTableWidgetItem;
		newItem1->setText(QString::fromStdString(car.getId()));
		newItem2->setText(QString::fromStdString(car.getProducer()));
		newItem3->setText(QString::fromStdString(car.getModel()));
		newItem4->setText(QString::fromStdString(car.getType()));
		table->setItem(row, 0, newItem1);
		table->setItem(row, 1, newItem2);
		table->setItem(row, 2, newItem3);
		table->setItem(row, 3, newItem4);
		row++;
	}
}