#include "test.h"
#include <assert.h>




void testGet()
{
	Car c{ "gl09goj", "bmw", "x3", "suv" };

	assert(c.getId() == "gl09goj");
	assert(c.getProducer() == "bmw");
	assert(c.getModel() == "x3");
	assert(c.getType() == "suv");

}

void testVali()
{
	Car c1{ "gl09goj", "bmw", "x3", "suv" };
	Car c2{ "", "bmw", "x3", "suv" };
	CarValidator cv{};

	try
	{
		cv.validate(c1);
		assert(true);
	}
	catch (ValidatorException&)
	{
		//assert(false);
	}

	try
	{
		cv.validate(c2);
		//assert(false);
	}
	catch (ValidatorException& ex)
	{
		cout << ex;
		assert(true);
	}

}

void testRepo()
{
	Car c1{ "gl09goj", "bmw", "x3", "suv" };
	Car c2{ "gl09goj", "bmw", "x3", "suv" };
	Repository repo{};

	repo.store(c1);
	assert(size(repo.getAll()) == 1);

	try
	{
		repo.store(c2);
		//assert(false);
	}
	catch (RepoException&)
	{
		assert(true);
	}

	try
	{
		repo.find("gl09goj");
		assert(true);
	}
	catch (RepoException&)
	{
		//assert(false);
	}

	try
	{
		repo.find("wow");
		//assert(false);
	}
	catch (RepoException& ex)
	{
		cout << ex;
		assert(true);
	}

	repo.erase("gl09goj");
	assert(size(repo.getAll()) == 0);
	Car c4{ "5", "bmw", "x3", "suv" };
	Car c5{ "6", "bmw", "x3", "suv" };
	Car c6{ "7", "bmw", "x3", "suv" };
	Car c100{ "100","sad","dsa","dsadas" };
	repo.store(c4);
	repo.store(c5);
	repo.store(c6);
	assert(size(repo.getAll()) == 3);
	repo.erase("7");
	try
	{
		repo.erase("10dsadasd");
	}
	catch (RepoException& ex)
	{

		assert(true);
	}

	try
	{
		repo.update(c100);
	}
	catch (RepoException& ex)
	{
		assert(true);
	}


}

void testCtr()
{
	Car c1{ "gl09goj", "bmw", "x3", "suv" };
	Car c2{ "gl09goj", "bmw", "x3", "suv" };

	Repository repo{};
	CarValidator cv{};
	CarController cr{ repo,cv };

	cr.addCar("gl09goj", "bmw", "x3", "suv");
	assert(size(cr.getAll()) == 1);

	cr.deleteCar("gl09goj");

	cr.addCar("id1", "ceva", "altcea", "sda");
	cr.update("id1", "ceva2", "altce2a", "sd2a");
	cr.addCar("id2", "ceva", "altcea", "sda");
	Car c3 = cr.findCar("id2");
	assert(c3.getId() == "id2");


	Repository repo2{};
	CarValidator cv2{};
	CarController cr2{ repo2,cv2 };

	cr2.addCar("gl07kyt", "vw", "golf", "nush");
	cr2.addCar("hd09ghi", "audi", "a7", "sport");
	cr2.addCar("br01ffo", "ferrari", "italia", "smecherie");
	cr2.addCar("aa01bos", "bmw", "x3", "suv");
	cr2.addCar("if01bos", "bmw", "x5", "suv");
	assert(size(cr2.getAll()) == 5);
	LinkedList<Car> rez1 = cr2.producerFilt("bmw");
	assert(size(rez1) == 2);
	LinkedList<Car> rez2 = cr2.typeFilt("sport");
	assert(size(rez2) == 1);

	LinkedList<Car> rez3 = cr2.sortById();
	//Car test = rez3.front();
	//assert(test.getId() == "aa01bos");
	LinkedList<Car> rez4 = cr2.sortByProducatorModel();
	//Car test2 = rez4.front();
	//assert(test2.getProducer() == "audi");



}




