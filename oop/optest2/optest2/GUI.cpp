#include "GUI.h"
#include <qlayout.h>
#include <qmessagebox.h>
#include <qpainter.h>
void GUI::init()
{
	QGridLayout* ly = new QGridLayout;
	setLayout(ly);

	table = new QTableWidget;
	table->setRowCount(100);
	table->setColumnCount(6);
	ly->addWidget(table, 1, 1, 4, 4);
	addB = new QPushButton("ADD");
	l1 = new QLineEdit;
	l2 = new QLineEdit;
	l3 = new QLineEdit;

	ly->addWidget(addB, 1, 5, 1, 2);
	ly->addWidget(l1, 2, 5, 1, 2);
	ly->addWidget(l2, 3, 5, 1, 2);
	ly->addWidget(l3, 4, 5, 1, 2);
	removeB = new QPushButton("Remove");
	ly->addWidget(removeB, 5, 5, 1, 2);

}

void GUI::con()
{
	QObject::connect(addB, &QPushButton::clicked, [&]() {
		
		
		int id;
		id = ctr.getAllCarti().size()  + 1 ;
		
		try
		{
			ctr.addCarte(id,l1->text().toStdString(), l2->text().toStdString(),l3->text().toStdString());
		}
		catch (Exceptions& ex)
		{
			QMessageBox::warning(nullptr, "Warning", QString::fromStdString(ex.getErr()));
		}
		reload(ctr.getAllCarti());
	});

	QObject::connect(removeB, &QPushButton::clicked, [&]() {

		int id;
		id = table->selectedItems().at(0)->text().toInt();
		try {
			ctr.removeCarte(id);
		}
		catch(Exceptions& ex)
		{
			QMessageBox::warning(nullptr, "Warning", QString::fromStdString(ex.getErr()));
		}
		reload(ctr.getAllCarti());
	});



}







void GUI::reload(vector<Carte> all)
{
	table->clear();
	int row = 0;
	for (const auto& carte : all)
	{
		
		QTableWidgetItem* i1 = new QTableWidgetItem;
		QTableWidgetItem* i2 = new QTableWidgetItem;
		QTableWidgetItem* i3 = new QTableWidgetItem;
		QTableWidgetItem* i4 = new QTableWidgetItem;
		QTableWidgetItem* i5 = new QTableWidgetItem;
		QTableWidgetItem* i6 = new QTableWidgetItem;

		i1->setText(QString::number(carte.getId()));
		i2->setText(QString::fromStdString(carte.getTitlu()));
		i3->setText(QString::fromStdString(carte.getAutor()));
		i4->setText(QString::fromStdString(carte.getGen()));
		i5->setText(QString::number(ctr.aGen(carte.getGen())));
		i6->setText(QString::number(ctr.aAutor(carte.getAutor())));

		table->setItem(row, 0, i1);
		table->setItem(row, 1, i2);
		table->setItem(row, 2, i3);
		table->setItem(row, 3, i4);
		table->setItem(row, 4, i5);
		table->setItem(row, 5, i6);

		row++;

	}
}


void Desen::paintEvent(QPaintEvent* ev)
{
	QPainter p{ this };

	QPoint punct1{ 1,1 };
	QPoint punct2{ this->size().height(),this->size().width() };
	QPoint punct3{ 1,this->size().width() };
	QPoint punct4{ this->size().height(),1 };
	int x1 = 5;
	int x2 = 5;
	
	for (const auto& c : ctr.getAllCarti())
	{
		p.drawEllipse(punct1, x1, x2);
		p.drawEllipse(punct2, x1, x2);
		p.drawEllipse(punct3, x1, x2);
		p.drawEllipse(punct4, x1, x2);
		x1 += 10;
		x2 += 10;
	}
}