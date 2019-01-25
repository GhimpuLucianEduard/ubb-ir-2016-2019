package com.company.Threads;

import com.company.Matrix;

public class MultiplicationThread extends AbstractMatrixThread<Integer> {

    public MultiplicationThread(Matrix<Integer> leftMatrix, Matrix<Integer> rightMatrix, Matrix<Integer> resultMatrix, int startIndex, int endIndex, int size) {
        super(leftMatrix, rightMatrix, resultMatrix, startIndex, endIndex, size);
    }

    @Override
    public void run() {
        super.run();
        for (int i = startIndex; i < endIndex; i++) {
            int row = i / size;
            int col = i % size;
            multiply(row, col);
        }
    }

    private void multiply(int row, int col) {
        Integer[] leftRow = leftMatrix.getRow(row);
        Object[] rightCol = rightMatrix.getColumn(col);
        resultMatrix.getData()[row][col] = 0;
        for (int i = 0; i < leftRow.length; i++) {
            resultMatrix.getData()[row][col] += leftRow[i] * (Integer) rightCol[i];
        }
    }
}
