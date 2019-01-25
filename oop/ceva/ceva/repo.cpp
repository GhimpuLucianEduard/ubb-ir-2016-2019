#include "repo.h"
#include <fstream>




void Repository::store(const Student& s) {

	auto rez = find_if(all.begin(), all.end(), [&](const Student& st) {
		return s.getNume() == st.getNume();
	});

	if (rez != all.end())
		throw StudentException{ "pet deja in data" };

	all.push_back(s);

}


void Repository::remove(const Student& s) {

	auto rez = find_if(all.begin(), all.end(), [&](const Student& st) {
		return s.getNume() == st.getNume();
	});

	if (rez == all.end())
		throw StudentException{ "pet nu e in repo" };

	all.erase(rez);

}

Student Repository::find(const string& s) {

	auto rez = find_if(all.begin(), all.end(), [&](const Student& st) {
		return s == st.getNume();
	});

	if (rez == all.end())
		throw StudentException{ "pet nu e in repo" };

	return *rez;

}
vector<Student> Repository::getAll()
{
	return all;
}
void FileRepo::load()
{
	ifstream in(fileName);

	while (!in.eof())
	{
		string nume;
		in >> nume;
		int medie;
		in >> medie;

		if (in.eof())
		{
			break;
		}
		Student s{ nume,medie };
		Repository::store(s);
	}
	in.close();
}

void FileRepo::write()
{
	ofstream out(fileName);

	for (const auto& s : Repository::getAll())
	{
		out << s.getNume();
		out << endl;
		out << s.getMedie();
		out << endl;

	}
	out.close();
}