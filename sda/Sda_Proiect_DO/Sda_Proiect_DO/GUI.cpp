#include "GUI.h"


void GUI::initGUI()
{
	QGridLayout* ly = new QGridLayout;
	setLayout(ly);

	lst = new QListWidget;
	ly->addWidget(lst, 1, 1, 5, 3);

	addB = new QPushButton;
	removeB = new QPushButton;
	nume = new QLineEdit;
	info = new QTextEdit;
	cauta = new QPushButton;
	cauta->setText("Cauta in Enciclopedie");


	addB->setText("Adauga in Enciclopedie");
	removeB->setText("Sterge din Enciclopedie");


	ly->addWidget(info, 2, 4, 4, 3);
	ly->addWidget(nume, 1, 4, 1, 3);
	ly->addWidget(addB, 6, 1, 1, 3);
	ly->addWidget(removeB, 6, 4, 1, 3);


	label = new QLabel;
	

	ly->addWidget(label, 1, 7, 6, 6);
	addB2 = new QPushButton;
	addB2->setText("Modifica imaginile");
	ly->addWidget(addB2, 7, 5, 1, 2);
	ly->addWidget(cauta, 7, 1, 1, 2);

	modi = new QPushButton;
	modi->setText("Modifica Informatiile");
	ly->addWidget(modi, 7, 3, 1, 2);
}


void GUI::reloadList(const DictionarOrdonat& od) const
{
	lst->clear();
	
	auto it = od.iterator();
	while (it.valid())
	{
		QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(it.element().getNume()), lst);

		it.urmator();
	}


}


void GUI::connections()
{
	QObject::connect(lst, &QListWidget::itemSelectionChanged, [&]() {
		if (lst->selectedItems().isEmpty())
		{
			nume->setText("");
			info->setText("");
			return;
		}

		QListWidgetItem* selItem = lst->selectedItems().at(0);
		QString numes = selItem->text();
		nume->setText(numes);
		
		try {
	
			info->setText(QString::fromStdString(ctr.findEC(numes.toStdString()).getInfo()));
			
		}
		catch (Exceptions& ex)
		{
			QMessageBox::warning(nullptr, "Warning", QString::fromStdString(ex.getMsg()));
		}
	
		string caut = "C:/Users/Deus/Documents/Visual Studio 2015/Projects/Sda_Proiect_DO/Sda_Proiect_DO/Img/";
		caut += numes.toStdString();
		caut += ".jpg";
		QPixmap image(QString::fromStdString(caut));
		label->setPixmap(image);

		
	
	
	
	});

	QObject::connect(addB, &QPushButton::clicked, [&]() {

		try {
			
			string n = nume->text().toStdString();
			string i = info->toPlainText().toStdString();
			i.erase(std::remove(i.begin(), i.end(), '\n'), i.end());
			ctr.add(n, i);
			nume->setText("");
			info->setText("");
			
			reloadList(ctr.getAllE());

		}
		catch (Exceptions& ex)
		{
			QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
		}


	});

	QObject::connect(removeB, &QPushButton::clicked, [&]() {
	
		try {
			ctr.sterge(nume->text().toStdString());
			nume->setText("");
			info->setText("");
			
			reloadList(ctr.getAllE());

		}
		catch (Exceptions& ex)
		{
			QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
		}
	});

	QObject::connect(addB2, &QPushButton::clicked, [&]() {
		
		//QFileDialog::getOpenFileNames(this, tr("Open File"), "/path/to/file/", tr("Mp3 Files (*.mp3)"));
		
	});

	QObject::connect(cauta, &QPushButton::clicked, [&]() {
		
		int i;
		QString numes = nume->text();
		for (i = 0; i < lst->count(); ++i)
		{
			QListWidgetItem* item = lst->item(i);
			if (item->text() == numes)
			{
				lst->setCurrentItem(item);
				break;
			}
		}
		
		if (i == lst->count())
		{
			QMessageBox::warning(this, "Warning", "Entitate  "  + numes + "  nu exista in Enciclopedie!");

		}


	});


	QObject::connect(modi, &QPushButton::clicked, [&]() {


		try {

			string n = nume->text().toStdString();
			string i = info->toPlainText().toStdString();
			i.erase(std::remove(i.begin(), i.end(), '\n'), i.end());

			ctr.modificaE(n,i);
			nume->setText("");
			info->setText("");

			reloadList(ctr.getAllE());

		}
		catch (Exceptions& ex)
		{
			QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
		}

	});


}