#pragma once
#include "Domain.h"
#include <fstream>

class Repository
{
private:
	string fName;
	vector<Nota> all;
public:
	Repository(const string& fName) : fName{ fName } {

		load();
	}

	void store(const Nota& n);
	vector<Nota> getAll() const { return all; }
	void load()
	{
		ifstream in(fName);
		while (!in.eof())
		{
			float value;
			in >> value;
			string student;
			in >> student;
			string examinator;
			in >> examinator;
			if (in.eof()) {
				break;
			}
			Nota n{ value,student,examinator };
			all.push_back(n);

		}
		in.close();
	}

	
	void write()
	{
		ofstream os(fName);
		for (const auto& n : all)
		{
			os << n.getValoare() << endl;
			os << n.getStudent() << endl;
			os << n.getExaminator() << endl;

		}
		os.close();
	}
		
};