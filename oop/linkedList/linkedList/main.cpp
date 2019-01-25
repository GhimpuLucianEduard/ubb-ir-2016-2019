#define _CRTDBG_MAP_ALLOC  
#include <stdlib.h>  
#include <crtdbg.h>  
#include "linkedList.h"
#include <iostream>
#include <string>
#include "CarController.h"
#include "ui.h"
#include "test.h"
using namespace std;

int main()
{

	testGet();
	testVali();
	testRepo();
	testCtr();


	{
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
	

	
	_CrtDumpMemoryLeaks();
	return 0;

}