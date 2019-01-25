#pragma once
#include <iostream>
#include <string>
#include "Constants.h"

template <typename T>
class Matrix
{	
private:

	T** data;
	int rowCount;
	int colCount;

public:
	
	Matrix(const int& rowCount, const int& colCount);
	~Matrix();
	int GetRowCount() const;
	void SetRowCount(const int& count);
	int GetColCount() const;
	void SetColCount(const int& count);
	T Get(const int& x, const int& y);
	void Set(const T& t, const int& x, const int& y);
	T* GetData() const;
	void SetData(T* data);
	void Clear();
	friend ostream& operator<<(ostream& os, const Matrix<T>& matrix)
	{
		for (auto i = 0; i < matrix.rowCount; i++)
		{
			for (auto j = 0; j < matrix.colCount; j++)
			{
				os << matrix.data[i][j];
				os << SPACE_STRING;
			}
			os << NEW_LINE;
		}

		return os;
	}
};

template<typename T>
Matrix<T>::Matrix(const int& rowCount, const int& colCount)
{
	this->rowCount = rowCount;
	this->colCount = colCount;

	this->data = new T*[rowCount];
	for (int i = 0; i < rowCount; i++)
	{
		data[i] = new T[colCount];
	}
}

template<typename T>
Matrix<T>::~Matrix()
{
	this->Clear();
}


template<typename T>
void Matrix<T>::Clear()
{
	if (this->data == nullptr) { return; }

	for (int i = 0; i < rowCount; i++)
	{
		delete[] data[i];
	}
	delete[] data;

	data = nullptr;
}


template<typename T>
T Matrix<T>::Get(const int& x, const int& y)
{
	return data[y][x];
}

template<typename T>
void Matrix<T>::Set(const T& t, const int& x, const int& y)
{
	data[x][y] = t;
}