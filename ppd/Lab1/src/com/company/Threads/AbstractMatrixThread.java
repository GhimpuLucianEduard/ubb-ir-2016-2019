package com.company.Threads;

import com.company.Matrix;

public abstract class AbstractMatrixThread<T> extends Thread {

    Matrix<T> leftMatrix;
    Matrix<T> rightMatrix;
    Matrix<T> resultMatrix;
    int endIndex;
    int startIndex;
    int size;

    public AbstractMatrixThread(Matrix<T> leftMatrix, Matrix<T> rightMatrix, Matrix<T> resultMatrix, int startIndex, int endIndex, int size) {
        this.leftMatrix = leftMatrix;
        this.rightMatrix = rightMatrix;
        this.resultMatrix = resultMatrix;
        this.endIndex = endIndex;
        this.startIndex = startIndex;
        this.size = size;
    }
}
