#include "Domain.h"
#include "teste.h"
#include <assert.h>
#include "Repo.h"
#include "qdebug.h"
void teste()
{
	Nota n1(8.40, "MArian", "Istavan");
	Nota n2(3.40, "dsaArew3q", "Idsastavan");
	Nota n3(2.40, "MAriewqan", "Isdsa21tavan");

	assert(n1.getStudent() == "MArian");
	assert(n1.getExaminator() == "Istavan");
	assert(n1.getValoare() == (float)8.40);

	
	Repository repo("teste.txt");

	
	Nota ner(8.420, "MArian", "Isdsatavan");
	try {
		repo.store(n1);
		repo.store(n2);
		repo.store(n3);
		
	}
	catch(Exceptions& ex)
	{
		assert(true);
	}
	//auto all = repo.getAll();
	//auto size = all.size();
	//assert(all.size()==3);
	


}

