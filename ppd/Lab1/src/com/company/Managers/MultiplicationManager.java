package com.company.Managers;

import com.company.Matrix;
import com.company.Threads.MultiplicationThread;

public class MultiplicationManager extends AbstractManager<MultiplicationThread> {

    public MultiplicationManager(Matrix<Integer> leftMatrix, Matrix<Integer> rightMatrix, Matrix<Integer> resultMatrix) {
        super(leftMatrix, rightMatrix, resultMatrix);
    }

    private void multiply(int row, int col) {
        Integer[] leftRow = leftMatrix.getRow(row);
        Object[] rightCol = rightMatrix.getColumn(col);
        resultMatrix.getData()[row][col] = 0;
        for (int i = 0; i < leftRow.length; i++) {
            resultMatrix.getData()[row][col] += leftRow[i] * (Integer) rightCol[i];
        }
    }

    @Override
    protected void sequentialForEachConsumer(int rowIndex, int colIndex) {
        multiply(rowIndex, colIndex);
    }

    @Override
    protected void addThread(Matrix<Integer> leftMatrix, Matrix<Integer> rightMatrix, Matrix<Integer> resultMatrix, int currentStartIndex, int currentEndIndex, int size) {
        threads.add(new MultiplicationThread(leftMatrix, rightMatrix, resultMatrix, currentStartIndex, currentEndIndex, size));
    }
}
