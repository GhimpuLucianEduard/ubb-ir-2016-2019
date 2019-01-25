#include "Controller.h"


void Controller::addSong(const int& id, const string& titlu, const string& artist, const int& rank)
{
	Song s(id, titlu, artist, rank);
	vali.validate(s);
	repo.store(s);
	notif();
}
vector<Song> Controller::getAllSogns() const
{
	return repo.getAll();
}
vector<Song> Controller::sortRank() const
{
	vector<Song> rez = repo.getAll();
	sort(rez.begin(), rez.end(), [&](const Song& s1, const Song& s2) {

		return s1.getRank() < s2.getRank();
	});

	return rez;
	

}
int Controller::cateLaFel(const int& rank) const
{	
	int rez = 0;
	for (const auto& s : repo.getAll())
	{
		if (s.getRank() == rank)
		{
			rez++;
		}
	}
	return rez;
}

const Song& Controller::findSong(const int& id) const
{
	return repo.find(id);
}


void Controller::updateSong(const int& id, const string& tnou, const int& rnou)
{
	
	Song snou{ id,tnou,repo.find(id).getArtist(),rnou };
	vali.validate(snou);
	repo.update(snou);
	notif();
}

void Controller::removeSong(const int& id)
{
	repo.remove(id);
	notif();
}