#include "GUI.h"
#include "qmessagebox.h"
void GUI::initGUI()
{
	QGridLayout* ly = new QGridLayout;
	setLayout(ly);

	lst = new QListWidget;
	ly->addWidget(lst, 1, 1, 4, 4);

	textValue = new QLineEdit;
	textStu = new QLineEdit;
	textExaminator = new QLineEdit;
	textValue->setPlaceholderText("Type value");
	ly->addWidget(textValue,1, 5, 1, 2);
	ly->addWidget(textStu,2, 5, 1, 2);
	ly->addWidget(textExaminator,3, 5, 1, 2);

	addB = new QPushButton("Add");
	ly->addWidget(addB, 4, 5, 1, 2);

	search = new QLineEdit;
	search->setPlaceholderText("Type to search");
	ly->addWidget(search, 5, 5, 1, 2);


}


void GUI::con()
{
	QObject::connect(addB, &QPushButton::clicked, [&]() {
		
		try
		{
			float value;
			value = textValue->text().toFloat();
			string stu;
			stu = textStu->text().toStdString();
			string exa;
			exa = textExaminator->text().toStdString();
			
			ctr.addNota(value, stu, exa);
			loadList(ctr.getSorted());
		}
		catch (Exceptions& ex)
		{
			QMessageBox::warning(nullptr, "warning", QString::fromStdString(ex.getEx()));
		}
	});


	QObject::connect(search, &QLineEdit::textEdited, [&]() {


		if (search->text() == "")
		{
			loadList(ctr.getSorted());
			return;
		}
		loadList(ctr.getFind(search->text().toStdString()));


	});





}

void GUI::loadList(const vector<Nota> all)
{
	lst->clear();
	for (const auto& n : all)
	{
		string toAdd;
		toAdd += to_string(n.getValoare());
		toAdd += " ";
		toAdd += n.getStudent();
		toAdd += " ";
		toAdd += n.getExaminator();

		QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(toAdd), lst);
	}
}


void GUI2::initGUI2()
{
	QGridLayout* ly = new QGridLayout;
	setLayout(ly);

	nrL = new QLabel();
	ly->addWidget(nrL,1,1,2,2);

}


void GUI::smecherie(vector<Aux> rez)
{
	for (const auto& aux : rez)
	{
		GUI2* da = new GUI2{};
		
		da->setWindowTitle(QString::fromStdString(aux.prof));
		da->nrL->setText(QString::number(aux.nr));
		da->show();
		
	}
}