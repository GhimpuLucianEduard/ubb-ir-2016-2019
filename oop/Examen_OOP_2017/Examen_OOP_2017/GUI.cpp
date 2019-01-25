#include "GUI.h"

#include <qtablewidget.h>
#include <qpainter.h>
#include <qmessagebox.h>
void GUI::init()
{
	
	
	
	QGridLayout* ly = new QGridLayout;
	setLayout(ly);
	
	table = new QTableWidget;
	table->setRowCount(100);
	table->setColumnCount(5);
	table->setFixedHeight(400);
	ly->addWidget(table, 1, 1, 5, 5);
	ltitlu = new QLineEdit;
	lrank = new QLineEdit;
	ltitlu->setPlaceholderText("Type a new name here");
	lrank->setPlaceholderText("Type new rank here");
	updateB = new QPushButton("Update song");
	ly->addWidget(ltitlu, 1, 6, 1, 3);
	ly->addWidget(lrank, 2, 6, 1, 3);
	ly->addWidget(updateB, 3, 6, 1, 3);
	removeB = new QPushButton("Remove song");
	ly->addWidget(removeB, 4, 6, 1, 3);
	

}

void GUI::load(const vector<Song>& all)
{
	table->clear();
	int row = 0;
	for (const auto& s : all)
	{
		QTableWidgetItem* i0 = new QTableWidgetItem;
		QTableWidgetItem* i1 = new QTableWidgetItem;
		QTableWidgetItem* i2 = new QTableWidgetItem;
		QTableWidgetItem* i3 = new QTableWidgetItem;
		QTableWidgetItem* i4 = new QTableWidgetItem;
		
		i0->setText(QString::number(s.getId()));
		i1->setText(QString::fromStdString(s.getTitlu()));
		i2->setText(QString::fromStdString(s.getArtist()));
		i3->setText(QString::number(s.getRank()));
		i4->setText(QString::number(ctr.cateLaFel(s.getRank())));

		table->setItem(row, 0, i0);
		table->setItem(row, 1, i1);
		table->setItem(row, 2, i2);
		table->setItem(row, 3, i3);
		table->setItem(row, 4, i4);
		row++;
		
	
	}
}

void GUI::con()
{
	QObject::connect(table,&QTableWidget::itemSelectionChanged, [&]() {

		if (table->selectedItems().isEmpty())
		{
			ltitlu->setPlaceholderText("Type a new name here");
			lrank->setPlaceholderText("Type new rank hereweqeqw");
			return;
		}

		QString id = table->selectedItems().at(0)->text();
		

		//ltitlu->setText(id);
		
		Song s = ctr.findSong(id.toInt());
		ltitlu->setText(QString::fromStdString(s.getTitlu()));
		lrank->setText(QString::number(s.getRank()));


	});

	
	QObject::connect(updateB, &QPushButton::clicked, [&]() {

		try {

			QString id = table->selectedItems().at(0)->text();
			int idC = id.toInt();
			ctr.updateSong(idC, ltitlu->text().toStdString(), lrank->text().toInt());
			load(ctr.sortRank());

		}
		catch (Exceptions& ex)
		{
			QMessageBox::warning(nullptr, "AI FACUT O PROSTIE!", QString::fromStdString(ex.getEx()));
		}
		

	});
	

	QObject::connect(removeB, &QPushButton::clicked, [&]() {


		QString id = table->selectedItems().at(0)->text();
		ctr.removeSong(id.toInt());
		load(ctr.sortRank());

	});



}


/*
void GUI2::init2()
{
	QHBoxLayout* ly2 = new QHBoxLayout;
	setLayout(ly2);
}

void GUI2::paintEvent(QPaintEvent* ev)
{
	QPainter p(this);
	this->setFixedHeight(500);
	int i = 0;
	int lungime = 30;
	int inaltime = 0;
	int x = 5;
	int y = 490;
	for (i = 0; i < 10; i++)
	{
		p.drawRect(x, y, lungime, (-1)*ctr.cateLaFel(i) * 10);
		x += 40;

	}
}
*/

void GUI::paintEvent(QPaintEvent* ev)
{
	QPainter p(this);
	this->setFixedHeight(800);
	int i = 0;
	int lungime = 30;
	int inaltime = 0;
	int x = 5;
	int y = 790;
	for (i = 0; i < 10; i++)
	{
		p.drawRect(x, y, lungime, (-1)*ctr.cateLaFel(i) * 10);
		x += 40;

	}
}