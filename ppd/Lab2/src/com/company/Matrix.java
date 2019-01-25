package com.company;

import com.company.Utils.ForeachOperator;

public class Matrix<T> {

    private int rowCount;
    private int colCount;
    private T[][] data;

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    public T[][] getData() {
        return data;
    }

    public void setData(T[][] data) {
        this.data = data;
    }

    public Matrix(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    public T[] getRow(int rowIndex) {
        return data[rowIndex];
    }

    public Object[] getColumn(int colIndex) {
        Object[] column = new Object[rowCount];
        for (int i = 0; i < column.length; i++) {
            column[i] = data[i][colIndex];
        }
        return column;
    }

    public void clear() {
        foreach((rowIndex, colIndex) -> {
            getData()[rowIndex][colIndex] = null;
        });
    }

    public void foreach(ForeachOperator foreachOperator) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                foreachOperator.execute(i, j);
            }
        }
    }

    @Override
    public String toString() {
        String matrixAsString = "";
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                matrixAsString += data[i][j] + " ";
            }
            matrixAsString += "\n";
        }
        return matrixAsString;
    }
}
