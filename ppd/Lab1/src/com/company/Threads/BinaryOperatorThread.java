package com.company.Threads;

import com.company.Matrix;

import java.util.function.BinaryOperator;

public class BinaryOperatorThread<T> extends AbstractMatrixThread<T> {

    private BinaryOperator<T> operator;

    public BinaryOperatorThread(Matrix<T> leftMatrix, Matrix<T> rightMatrix, Matrix<T> resultMatrix, int startIndex, int endIndex, int size, BinaryOperator<T> operator) {
        super(leftMatrix, rightMatrix, resultMatrix, startIndex, endIndex, size);
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
