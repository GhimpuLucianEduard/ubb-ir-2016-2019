package com.company.Managers;

import com.company.Matrix;
import com.company.Threads.BinaryOperatorThread;

import java.util.function.BinaryOperator;

public class GenericOperationManager extends AbstractManager<BinaryOperatorThread> {

    BinaryOperator<Integer> operator;

    public GenericOperationManager(Matrix<Integer> leftMatrix, Matrix<Integer> rightMatrix, Matrix<Integer> resultMatrix, BinaryOperator<Integer> operator) {
        super(leftMatrix, rightMatrix, resultMatrix);
        this.operator = operator;
    }

    @Override
    protected void sequentialForEachConsumer(int i, int j) {
        resultMatrix.getData()[i][j] = operator.apply(leftMatrix.getData()[i][j], rightMatrix.getData()[i][j]);
    }

    @Override
    protected void addThread(Matrix<Integer> leftMatrix, Matrix<Integer> rightMatrix, Matrix<Integer> resultMatrix, int currentStartIndex, int currentEndIndex, int size) {
        threads.add(new BinaryOperatorThread<>(leftMatrix, rightMatrix, resultMatrix, currentStartIndex, currentEndIndex, size, (a, b) -> a + b));
    }
}
