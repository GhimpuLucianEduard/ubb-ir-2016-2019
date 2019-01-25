#include "examen_oop_2017.h"
#include <QtWidgets/QApplication>
#include "Repo.h"
#include <assert.h>
#include "GUI.h"
void teste()
{
	Song s1{ 1,"wow","cineva",4 };
	assert(s1.getId() == 1);
	assert(s1.getTitlu() == "wow");
	assert(s1.getArtist() == "cineva");
	assert(s1.getRank() == 4);

	Repository repo1{ "testCitire.txt" };
	
	assert(repo1.getAll()[0].getId() == 1);
	Validator vali{};
	Controller ctr{ repo1,vali };
	
	

	

	try
	{
		ctr.addSong(90, "wow", "wow2", 12);
		assert(false);
	}
	catch (Exceptions& ex)
	{
		assert(true);
	}

	assert(ctr.cateLaFel(4) == 2);

	//ctr.removeSong(90);

	assert(ctr.cateLaFel(4) == 2);

	Song cautat = ctr.findSong(1);
	assert(cautat.getId() == 1);

	try
	{
		repo1.remove(901);
		assert(false);
	}
	catch (Exceptions& ex)
	{
		assert(true);
	}
	
}
int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	//Examen_OOP_2017 w;
	teste();

	Validator vali{};
	Repository repo{ "songs.txt" };
	Controller ctr{ repo,vali };
	GUI gui{ ctr };
	gui.show();
	//GUI2 gui2{ ctr };
	//gui2.show();
	//w.show();
	return a.exec();
}
