#include "gui.h"
#include <QtWidgets\qlayout.h>
#include <QtWidgets\qlistwidget.h>
#include <QtWidgets\qmessagebox.h>
void GUI::initGUI()
{
	QGridLayout* ly = new QGridLayout;
	setLayout(ly);
	autor = new QLineEdit;
	titlu = new QLineEdit;
	nrPag = new QLineEdit;
	lst = new QListWidget;
	lst->setSelectionMode(QAbstractItemView::ExtendedSelection);
	addB = new QPushButton;
	addB->setText("adauga");
	filt = new QLineEdit;
	sort = new QPushButton;
	sort->setText("filtrare");

	suma = new QLineEdit;
	ly->addWidget(lst, 1, 1, 4, 4);
	ly->addWidget(autor, 1, 5, 2, 1);
	ly->addWidget(titlu, 2, 5, 2, 1);
	ly->addWidget(nrPag, 3, 5, 2, 1);
	ly->addWidget(addB, 5, 1, 1, 2);
	ly->addWidget(filt, 5, 3, 1, 2);
	ly->addWidget(sort, 5, 5, 1, 2);
	ly->addWidget(suma, 6, 1, 1, 3);
}

void GUI::reload(const vector<Carte>& all)
{
	lst->clear();

	for (const auto& c : all)
	{
		QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(c.getTitlu()),lst);
	}

}

void GUI::connect()
{
	QObject::connect(lst, &QListWidget::itemSelectionChanged, [&]() {
		
		

		
		if (lst->selectedItems().isEmpty())
		{
			autor->setText("");
			titlu->setText("");
			nrPag->setText("");
			suma->setText("");
			return;
		}
		else
		{
			int sumaS = 0;
			for (int row = 0; row < lst->selectedItems().count(); row++)
			{
				int nrp;
				QListWidgetItem* curent = lst->selectedItems().at(row);
				Carte c = ctr.findCarte(curent->text().toStdString());;
				nrp = c.getNrPag();
				sumaS = sumaS + nrp;

			}
			suma->setText(QString::number(sumaS));
		}
		

		QListWidgetItem* selectedItem = lst->selectedItems().at(0);
		try {
			Carte c = ctr.findCarte(selectedItem->text().toStdString());
			autor->setText(QString::fromStdString(c.getAutor()));
			titlu->setText(QString::fromStdString(c.getTitlu()));
			nrPag->setText(QString::number(c.getNrPag()));
		}
		catch (CarteException& ex)
		{
			QMessageBox::warning(nullptr, "Warning", QString::fromStdString(ex.getMsg()));
		}
	});

	QObject::connect(addB, &QPushButton::clicked, [&]() {
		
		try {
			ctr.storeCarte(autor->text().toStdString(), titlu->text().toStdString(), nrPag->text().toInt());
			autor->setText("");
			titlu->setText("");
			nrPag->setText("");
		}
		catch (CarteException& ex)
		{
			QMessageBox::warning(nullptr, "Warning", QString::fromStdString(ex.getMsg()));
		}
		
		reload(ctr.getAllCarti());
	});


	QObject::connect(sort, &QPushButton::clicked, [&]() {

		lst->clear();

		for (const auto& carte : ctr.getAllCarti())
		{
			QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(carte.getTitlu()), lst);
			if (carte.getNrPag() >= filt->text().toInt())
			{
				item->setBackgroundColor(QColor{ Qt::red });
			}
		}

	});





}