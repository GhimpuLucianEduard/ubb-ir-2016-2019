#include "pch.h"
#include <iostream>
#include <string>
#include "Matrix.h"
#include "ComplexNumber.h"
#include "ExecutionManager.h"

using namespace std;

int main()
{
	// open stream
	ofstream st;
	st.open("run.txt", std::fstream::trunc);
	//st.close();

	// set params
	cout << "Numar de threaduri: \n";
	cin >> Constants::THREAD_COUNT;
	st << "Numar threaduri: " << Constants::THREAD_COUNT << endl;

	cout << "Numar randuri: \n";
	cin >> Constants::ROW_COUNT;
	st << "Numar randurii: " << Constants::ROW_COUNT << endl;

	cout << "Numar coloane: \n";
	cin >> Constants::COL_COUNT;
	st << "Numar coloane: " << Constants::COL_COUNT << endl;

	int dataType = 0;
	cout << "Tip de data:  1 - float   2 - complex \n";
	cin >> dataType;
	
	if (dataType == 1)
	{	
		std::function<float(float, float)> floatMult = [](float a, float b) { return a * b; };
		std::function<float(float, float)> floatComp = [](float a, float b) { return 1 / ((1 / a) + (1 / b)); };
		ExecutionManager<float> floatManager = ExecutionManager<float>();
		floatManager.GenerateFloatMatrix();

		cout << "Tip operatie: 1 (inmultire) 2 (operatie speciala 1/((1/a)+(1/b))" << endl;
		int op;
		cin >> op;
		if (op == 1)
		{
			floatManager.doOperation(floatMult);
			floatManager.doOperationParallel(floatMult);
		}
		else
		{
			floatManager.doOperation(floatComp);
			floatManager.doOperationParallel(floatComp);
		}
	}
	else
	{	
		ExecutionManager<ComplexNumber> compManager = ExecutionManager<ComplexNumber>();
		std::function<ComplexNumber(ComplexNumber, ComplexNumber)> compMuti = [](ComplexNumber a, ComplexNumber b) { return a * b; };
		std::function<ComplexNumber(ComplexNumber, ComplexNumber)> compComp = [](ComplexNumber a, ComplexNumber b) { return (a*b)/(a + b); };
		compManager.GenerateComplexNumberMatrix();

		cout << "Tip operatie: 1 (inmultire) 2 (operatie speciala 1/((1/a)+(1/b))" << endl;
		int op;
		cin >> op;
		if (op == 1)
		{	
			compManager.doOperation(compMuti);
			compManager.doOperationParallel(compMuti);
		}
		else
		{
			compManager.doOperation(compComp);
			compManager.doOperationParallel(compComp);
		}
	}
}

