#include "Repo.h"
#include <algorithm>

void Repository::store(const Nota& n)
{
	auto rez = find_if(all.begin(), all.end(), [&](const Nota& ot) {
	
		return ot.getStudent() == n.getStudent();
	});
	
	if (rez != all.end())
	{
		throw Exceptions("nota deja mai e!");
	}
	all.push_back(n);
	write();
}