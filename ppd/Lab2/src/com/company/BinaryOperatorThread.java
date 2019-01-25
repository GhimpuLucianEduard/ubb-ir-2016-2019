package com.company;

import com.company.Matrix;

import java.util.function.BinaryOperator;

public class BinaryOperatorThread<T> extends Thread {

    private BinaryOperator<T> operator;
    Matrix<T> leftMatrix;
    Matrix<T> rightMatrix;
    Matrix<T> resultMatrix;
    int endIndex;
    int startIndex;
    int size;

    public BinaryOperatorThread(Matrix<T> leftMatrix, Matrix<T> rightMatrix, Matrix<T> resultMatrix, int startIndex, int endIndex, int size, BinaryOperator operator) {
        this.leftMatrix = leftMatrix;
        this.rightMatrix = rightMatrix;
        this.resultMatrix = resultMatrix;
        this.endIndex = endIndex;
        this.startIndex = startIndex;
        this.size = size;
        this.operator = operator;
    }

    @Override
    public void run() {
        super.run();
        for (int i = startIndex; i < endIndex; i++) {
            int row = i / size;
            int col = i % size;
            resultMatrix.getData()[row][col] = operator.apply(leftMatrix.getData()[row][col], rightMatrix.getData()[row][col]);
        }
    }
}
