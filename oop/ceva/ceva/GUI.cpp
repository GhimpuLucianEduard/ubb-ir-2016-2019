

#include "GUI.h"
#include "QtWidgets\qmessagebox.h"

void GUI::init()
{
	QGridLayout* ly = new QGridLayout;
	setLayout(ly);

	lst = new QListWidget;
	adaugaL = new QLineEdit;
	adaugaB = new QPushButton;
	filt = new QPushButton;
	ly->addWidget(lst, 1, 1, 4, 4);
	ly->addWidget(adaugaB, 1, 5, 1, 2);
	ly->addWidget(adaugaL, 1, 7, 1, 2);
	ly->addWidget(filt, 2, 5, 1, 2);
}


void GUI::reload()
{
	lst->clear();

	for (const auto& s : ctr.getAllStudenti())
	{
		QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(s.getNume()),lst);
		//item->setText(QString::fromStdString(s.getNume()));
	}

}

void GUI::conect()
{
	QObject::connect(lst, &QListWidget::itemSelectionChanged, [&]() {

		if (lst->selectedItems().isEmpty())
		{
			adaugaL->setText("");
			return;
		}
		
		QListWidgetItem* selectat = lst->selectedItems().at(0);
		adaugaL->setText(selectat->text());

	});

	QObject::connect(adaugaB, &QPushButton::clicked, [&]() {

		try {
			
			ctr.adugaStudent(adaugaL->text().toStdString(), 10);
			reload();
		}
		catch (StudentException& ex)
		{
			QMessageBox::warning(this, "warning", QString::fromStdString(ex.getMsg()));
		}
	});


	QObject::connect(filt, &QPushButton::clicked, [&]() {
	
	
		lst->clear();
		for (const auto& s : ctr.getAllStudenti())
		{
			QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(s.getNume()), lst);
			if (s.getMedie() == 10)
				item->setBackgroundColor(QColor{ Qt::red });
		}
	
	
	});
}