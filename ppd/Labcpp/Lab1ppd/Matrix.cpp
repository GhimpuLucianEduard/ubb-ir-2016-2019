#include "pch.h"
#include "Matrix.h"
#include <iostream>

using namespace std;

template<typename T>
int Matrix<T>::GetRowCount() const
{
	return this.rowCount;
}

template<typename T>
void Matrix<T>::SetRowCount(const int& count)
{
	this->rowCount = count;
}

template<typename T>
int Matrix<T>::GetColCount() const
{
	return this->colCount;
}

template<typename T>
void Matrix<T>::SetColCount(const int & count)
{
	this->colCount = count;
}

template<typename T>
T* Matrix<T>::GetData() const
{
	return this->data;
}

template<typename T>
void Matrix<T>::SetData(T* data)
{
	this->data = data;
}