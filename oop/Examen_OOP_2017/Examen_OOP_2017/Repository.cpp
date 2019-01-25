#include "Repo.h"

#include <iterator>
#include <fstream>


void Repository::store(const Song& s)
{
	auto rez = find_if(all.begin(), all.end(), [&](const Song& s2) {
		return s.getId() == s2.getId();
	});
	
	if (rez != all.end())
	{
		throw Exceptions("Exista deja in repo!");
	}
	all.push_back(s);
	write();

}

void Repository::remove(const int& id)
{
	auto rez = find_if(all.begin(), all.end(), [&](const Song& s2) {
		return id == s2.getId();
	});

	if (rez == all.end())
	{
		throw Exceptions("Nu exista in repo!");
	}

	all.erase(rez);
	write();

}

const Song& Repository::find(const int& id) const
{
	auto rez = find_if(all.begin(), all.end(), [&](const Song& s2) {
		return id == s2.getId();
	});
	if (rez == all.end())
	{
		throw Exceptions("Nu exista in repo!");
	}
	return *rez;
}


void Repository::load()
{
	ifstream in(fName);
	while (!in.eof())
	{
		int id;
		in >> id;
		string titlu;
		in >> titlu;
		string artist;
		in >> artist;
		int rank;
		in >> rank;
		if (in.eof())
		{
			break;
		}
	
		Song s{ id,titlu,artist,rank };
		all.push_back(s);
		
	}
	in.close();
}
void Repository::write()
{
	ofstream out(fName);

	for (const auto& s : all)
	{
		out << s.getId() << endl;
		out << s.getTitlu() << endl;
		out << s.getArtist() << endl;
		out << s.getRank() << endl;
	}
	out.close();

}


vector<Song> Repository::getAll() const
{
	return all;
}


void Repository::update(const Song& nou)
{
	
	auto rez = find_if(all.begin(), all.end(), [&](const Song& s2) {
		return nou.getId() == s2.getId();
	});
	all.erase(rez);
	all.push_back(nou);
	write();
}
