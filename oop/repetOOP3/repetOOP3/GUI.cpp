#include "GUI.h"
#include "qmessagebox.h"
#include <qmessagebox.h>
void GUI::init()
{

	QGridLayout* ly = new QGridLayout;
	setLayout(ly);
	table = new QTableWidget;
	table->setRowCount(100);
	table->setColumnCount(4);
	ly->addWidget(table, 1, 1, 5, 5);
	incB = new QPushButton("incrementeaza produs");
	linc = new QLineEdit;
	linc->setPlaceholderText("type here the quantity");
	ly->addWidget(incB, 1, 6, 1, 2);
	ly->addWidget(linc, 2, 6, 1, 2);
	
	labelSlider = new QLabel;
	slider = new QSlider;
	labelSlider->setText("0");
	ly->addWidget(slider, 3, 6, 2, 2);
	ly->addWidget(labelSlider, 3, 8, 1, 2);
	
	
	

}






void GUI::reload(const vector<Produs>& all)
{
	table->clear();
	int row=0;
	for (const auto& p : all)
	{
		QTableWidgetItem* i1 = new QTableWidgetItem;
		QTableWidgetItem* i2 = new QTableWidgetItem;
		QTableWidgetItem* i3 = new QTableWidgetItem;
		QTableWidgetItem* i4 = new QTableWidgetItem;
		
		i1->setText(QString::number(p.getId()));
		i2->setText(QString::fromStdString(p.getDes()));
		i3->setText(QString::number(p.getCount()));
		i4->setText(QString::number(p.getPrice()));
		
		table->setItem(row, 0, i1);
		table->setItem(row, 1, i2);
		table->setItem(row, 2, i3);
		table->setItem(row, 3, i4);
		row++;
	}
}


void GUI::conect()
{
	QObject::connect(incB, &QPushButton::clicked, [&]() {

		//QTableWidgetItem* select = new QTableWidgetItem;
		int id;
		
		
		id = table->selectedItems().at(0)->text().toInt();
		if (table->selectedItems().empty())
		{
			QMessageBox::warning(nullptr, "warning", "Selectati un produs!");
			return;

		}
		

		try {
			ctr.inc(id, linc->text().toInt());
		}
		catch(Exceptions& ex)
		{
			QMessageBox::warning(nullptr, "warning", QString::fromStdString(ex.getEx()));
		}
		reload(ctr.getSorted());
	
	
	});


	QObject::connect(slider, &QSlider::sliderMoved, [&]() {
	
		int poz;
		poz = slider->value();
		labelSlider->setText(QString::number(poz));
		
			
		table->clear();
		int row = 0;
		
		
		for (const auto& p : ctr.getAll())
		{
			QTableWidgetItem* i1 = new QTableWidgetItem;
			QTableWidgetItem* i2 = new QTableWidgetItem;
			QTableWidgetItem* i3 = new QTableWidgetItem;
			QTableWidgetItem* i4 = new QTableWidgetItem;

			i1->setText(QString::number(p.getId()));
			i2->setText(QString::fromStdString(p.getDes()));
			i3->setText(QString::number(p.getCount()));
			i4->setText(QString::number(p.getPrice()));

			table->setItem(row, 0, i1);
			table->setItem(row, 1, i2);
			table->setItem(row, 2, i3);
			table->setItem(row, 3, i4);
			
			if (p.getCount() < poz)
			{
				i1->setBackgroundColor(Qt::red);
				i2->setBackgroundColor(Qt::red);
				i3->setBackgroundColor(Qt::red);
				i4->setBackgroundColor(Qt::red);
			}
				
			
			
			row++;
		}
		


	});






}


void GUI2::init2()
{
	QGridLayout* ly = new QGridLayout;
	setLayout(ly);
	label = new QLabel;
	ly->addWidget(label, 1, 1, 2, 2);
	this->setWindowTitle("PRET TOTAL");
}

void GUI2::cal()
{	
	int suma = 0;
	for (const auto& p : ctr.getAll())
	{
		suma = suma + (p.getCount()*p.getPrice());
	}

	label->setText(QString::number(suma));

}

void GUI3::paintEvent(QPaintEvent* ev)
{
	QPainter p{ this };

	int x = 1;
	int y = 1;
	

	//p.fillRect(100, 100, 10, 10, Qt::BrushStyle::Dense1Pattern);

	for (const auto& pr : ctr.getAll())
	{
		p.fillRect(x, y, 30, 10 * pr.getCount(),Qt::red);
		x = x + 35;
	
	}

}