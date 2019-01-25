package com.company.Utils;

import com.company.ComplexNumber;
import com.company.Matrix;

import static com.company.Utils.Constants.*;

public class Utils {

    public static void foreach(ForeachOperator foreachOperator) {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                foreachOperator.execute(i, j);
            }
        }
    }

    public static Matrix generateMatrix(DataType type) {
        FileManager.getInstance().writeToFile("Generated matrix: ");
        FileManager.getInstance().writeToFile("Col count: " + COL_COUNT);
        FileManager.getInstance().writeToFile("Row count: " + ROW_COUNT);

        switch (type) {
            case INTEGER:
                Matrix<Integer> matrix = new Matrix<>(ROW_COUNT, COL_COUNT);
                matrix.setData(new Integer[ROW_COUNT][COL_COUNT]);

                Utils.foreach((i, j) -> matrix.getData()[i][j] = RANDOM.nextInt(20) - 10);

                FileManager.getInstance().writeMatrixToFile(matrix);
                return matrix;
            case FLOAT:
                Matrix<Float> floatMatrix = new Matrix<>(ROW_COUNT, COL_COUNT);
                floatMatrix.setData(new Float[ROW_COUNT][COL_COUNT]);

                Utils.foreach((i, j) -> floatMatrix.getData()[i][j] = (RANDOM.nextFloat() * 10) - 5);

                FileManager.getInstance().writeMatrixToFile(floatMatrix);
                return floatMatrix;
            case COMPLEX:
                Matrix<ComplexNumber> complexMatrix = new Matrix<>(ROW_COUNT, COL_COUNT);
                complexMatrix.setData(new ComplexNumber[ROW_COUNT][COL_COUNT]);


                Utils.foreach((i, j) -> {
                    int a = RANDOM.nextInt(20) - 10;
                    int b = RANDOM.nextInt(20) - 10;
                    ComplexNumber complexNumber = new ComplexNumber(a, b);
                    complexMatrix.getData()[i][j] = complexNumber;
                });

                FileManager.getInstance().writeMatrixToFile(complexMatrix);
                return complexMatrix;
            default:
                break;
        }

        return null;
    }

    public enum DataType {
        INTEGER, FLOAT, COMPLEX
    }
}
