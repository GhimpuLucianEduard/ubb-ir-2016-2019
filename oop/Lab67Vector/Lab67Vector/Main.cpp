#include "Controller.h"
#include "Domain.h"
#include "Repo.h"
#include "Validator.h"
#include "Ui.h"
#include "test.h"
int main()
{
	testGet();
	testRepo();
	testVali();
	testCtr();


	Repository repo;
	CarValidator vali;
	CarController ctr{ repo,vali };
	ctr.addCar("gl07kyt", "vw", "golf", "nush");
	ctr.addCar("hd09ghi", "audi", "a7", "sport");
	ctr.addCar("br01ffo", "ferrari", "italia", "smecherie");
	ctr.addCar("br01bos", "bmw", "x3", "suv");
	ctr.addCar("if01bos", "bmw", "x5", "suv");
	Ui ui{ ctr };
	ui.startMenu();
}