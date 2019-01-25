#pragma once
#include "Matrix.h"
#include <iostream>
#include "Constants.h"
#include <fstream>
#include <functional>
#include <time.h>   
#include "ComplexNumber.h"
#include <thread>

using namespace std;

template <typename T>
class ExecutionManager
{
private:
	Matrix<T>* leftMatrix;
	Matrix<T>* rightMatrix;
	Matrix<T>* resMatrix;
	thread **threads;
public:
	void GenerateIntMatrix();
	void GenerateFloatMatrix();
	void GenerateComplexNumberMatrix();
	void doOperation(std::function<T(T, T)> oper);
	void doOperationParallel(std::function<T(T, T)> oper);
	void OperationThread(int start, int end, int size, std::function<T(T, T)> oper);
	ExecutionManager();
	~ExecutionManager();
};

template<typename T>
void ExecutionManager<T>::GenerateIntMatrix()
{	
	srand(time(NULL));
	ofstream fileStream;
	fileStream.open("run.txt", std::fstream::app);

	Matrix<int>* leftMatrix = new Matrix<int>(Constants::ROW_COUNT, Constants::COL_COUNT);
	Matrix<int>* rightMatrix = new Matrix<int>(Constants::ROW_COUNT, Constants::COL_COUNT);
	Matrix<int>* resMatrix = new Matrix<int>(Constants::ROW_COUNT, Constants::COL_COUNT);
	for (auto i = 0; i < Constants::ROW_COUNT; i++)
	{
		for (auto j = 0; j < Constants::COL_COUNT; j++)
		{
			(*leftMatrix).Set(rand() % 20 - 10, i, j);
			(*rightMatrix).Set(rand() % 20 - 10, i, j);
			(*resMatrix).Set(0, i, j);
		}
	}

	if (Constants::COL_COUNT < 100)
	{
		fileStream << "MATRICE GENERATA: " << endl << *leftMatrix;
		fileStream << "MATRICE GENERATA: " << endl << *rightMatrix;
	}
	else
	{
		fileStream << "Matrice prea mare pentru a fi tiparita" << endl;
	}

	this->leftMatrix = leftMatrix;
	this->rightMatrix = rightMatrix;
	this->resMatrix = resMatrix;
	fileStream.close();
}

template<typename T>
void ExecutionManager<T>::GenerateFloatMatrix()
{	
	srand(time(NULL));
	ofstream fileStream;
	fileStream.open("run.txt", std::fstream::app);

	Matrix<float>* leftMatrix = new Matrix<float>(Constants::ROW_COUNT, Constants::COL_COUNT);
	Matrix<float>* rightMatrix = new Matrix<float>(Constants::ROW_COUNT, Constants::COL_COUNT);
	Matrix<float>* resMatrix = new Matrix<float>(Constants::ROW_COUNT, Constants::COL_COUNT);
	for (auto i = 0; i < Constants::ROW_COUNT; i++)
	{
		for (auto j = 0; j < Constants::COL_COUNT; j++)
		{
			float flo1 = static_cast <float> (rand()) / static_cast <float> (RAND_MAX);
			float flo2 = static_cast <float> (rand()) / static_cast <float> (RAND_MAX);
			(*leftMatrix).Set(flo1 * 10 - 5, i, j);
			(*rightMatrix).Set(flo2 * 10 - 5, i, j);
			(*resMatrix).Set(0.0f, i, j);
		}
	}
		

	if (Constants::COL_COUNT < 100)
	{
		fileStream << "MATRICE GENERATA: " << endl << *leftMatrix;
		fileStream << "MATRICE GENERATA: " << endl << *rightMatrix;
	}
	else
	{
		fileStream << "Matrice prea mare pentru a fi tiparita" << endl;
	}


	this->leftMatrix = leftMatrix;
	this->rightMatrix = rightMatrix;
	this->resMatrix = resMatrix;
	fileStream.close();
}

template<typename T>
void ExecutionManager<T>::GenerateComplexNumberMatrix()
{	
	srand(time(NULL));
	ofstream fileStream;
	fileStream.open("run.txt", std::fstream::app);

	Matrix<ComplexNumber>* leftMatrix = new Matrix<ComplexNumber>(Constants::ROW_COUNT, Constants::COL_COUNT);
	Matrix<ComplexNumber>* rightMatrix = new Matrix<ComplexNumber>(Constants::ROW_COUNT, Constants::COL_COUNT);
	Matrix<ComplexNumber>* resMatrix = new Matrix<ComplexNumber>(Constants::ROW_COUNT, Constants::COL_COUNT);
	for (auto i = 0; i < Constants::ROW_COUNT; i++)
	{
		for (auto j = 0; j < Constants::COL_COUNT; j++)
		{
			float real1 = static_cast <float> (rand()) / static_cast <float> (RAND_MAX);
			float img1 = static_cast <float> (rand()) / static_cast <float> (RAND_MAX);
			float real2 = static_cast <float> (rand()) / static_cast <float> (RAND_MAX);
			float img2 = static_cast <float> (rand()) / static_cast <float> (RAND_MAX);
			ComplexNumber* comp1 = new ComplexNumber(real1, img1);
			ComplexNumber* comp2 = new ComplexNumber(real2, img2);
			ComplexNumber* comp3 = new ComplexNumber(0.0f, 0.0f);
			(*leftMatrix).Set(*comp1, i, j);
			(*rightMatrix).Set(*comp2, i, j);
			(*resMatrix).Set(*comp3, i, j);
		}
	}
		
	if (Constants::COL_COUNT < 100)
	{
		fileStream << "MATRICE GENERATA: " << endl << *leftMatrix;
		fileStream << "MATRICE GENERATA: " << endl << *rightMatrix;
	}
	else
	{
		fileStream << "Matrice prea mare pentru a fi tiparita" << endl;
	}

		this->leftMatrix = leftMatrix;
		this->rightMatrix = rightMatrix;
		this->resMatrix = resMatrix;
		fileStream.close();
}

template<typename T>
inline void ExecutionManager<T>::doOperation(std::function<T(T, T)> oper)
{
	ofstream fileStream;
	fileStream.open("run.txt", std::fstream::app);

	fileStream << "=============================SECVENTIAL===============================" << endl;
	auto start = chrono::steady_clock::now();

	for (auto i = 0; i < Constants::ROW_COUNT; i++)
	{
		for (auto j = 0; j < Constants::COL_COUNT; j++)
		{
			this->resMatrix->Set(oper(this->leftMatrix->Get(i, j), this->rightMatrix->Get(i, j)), i, j);
		}
	}

	auto stop = chrono::steady_clock::now();
	fileStream << "Timp secvential: " << endl;
	fileStream << std::chrono::duration_cast<std::chrono::milliseconds>(stop - start).count() << endl;
	fileStream << "Matrice Rezultat: " << endl;
	
	if (Constants::COL_COUNT < 100)
	{
		fileStream << *(this->resMatrix);
	}
	else
	{
		fileStream << "Matrice prea mare pentru a fi tiparita" << endl;
	}
}

template<typename T>
inline void ExecutionManager<T>::doOperationParallel(std::function<T(T, T)> oper)
{
	ofstream fileStream;
	fileStream.open("run.txt", std::fstream::app);

	fileStream << "=============================PARALEL===============================" << endl;
	auto start = chrono::steady_clock::now();

	// create threads
	int numberOfElements = Constants::COL_COUNT * Constants::COL_COUNT;
	int roundedElementsPerThread = numberOfElements / Constants::THREAD_COUNT;
	int numberOfRemainedElements = numberOfElements % Constants::THREAD_COUNT;
	int currentStartIndex = 0;
	int currentEndIndex = 0;
	for (int i = 0; i < Constants::THREAD_COUNT; i++) {
		currentEndIndex = currentStartIndex + roundedElementsPerThread;
		if (numberOfRemainedElements > 0) {
			currentEndIndex++;
			numberOfRemainedElements--;
		}
		threads[i] = new thread([=] { this->OperationThread(currentStartIndex, currentEndIndex, Constants::COL_COUNT, oper); });
		currentStartIndex = currentEndIndex;
	}

	for (int i = 0; i < Constants::THREAD_COUNT; i++)
	{
		threads[i]->join();
	}

	auto stop = chrono::steady_clock::now();
	fileStream << "Timp paralel: " << endl;
	fileStream << std::chrono::duration_cast<std::chrono::milliseconds>(stop - start).count() << endl;
	fileStream << "Matrice Rezultat: " << endl;

	if (Constants::COL_COUNT < 100)
	{
		fileStream << *(this->resMatrix);
	}
	else
	{
		fileStream << "Matrice prea mare pentru a fi tiparita" << endl;
	}
}

template<typename T>
inline void ExecutionManager<T>::OperationThread(int start, int end, int size, std::function<T(T, T)> oper)
{
	for (int i = start; i < end; i++) {
		int row = i / size;
		int col = i % size;
		resMatrix->Set(oper(rightMatrix->Get(row,col), leftMatrix->Get(row,col)), row, col);
	}
}

template<typename T>
ExecutionManager<T>::ExecutionManager()
{
	this->threads = new thread*[Constants::THREAD_COUNT];
}

template<typename T>
ExecutionManager<T>::~ExecutionManager()
{
}
