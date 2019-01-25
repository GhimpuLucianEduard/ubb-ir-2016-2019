package com.company.Utils;

import com.company.Matrix;

import static com.company.Utils.Constants.COL_COUNT;
import static com.company.Utils.Constants.RANDOM;
import static com.company.Utils.Constants.ROW_COUNT;

public class Utils {

    public static void foreach(ForeachOperator foreachOperator) {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                foreachOperator.execute(i, j);
            }
        }
    }

    public static Matrix<Integer> generateMatrix() {
        FileManager.getInstance().writeToFile("Generated matrix: ");
        FileManager.getInstance().writeToFile("Col count: " + COL_COUNT);
        FileManager.getInstance().writeToFile("Row count: " + ROW_COUNT);

        Matrix<Integer> matrix = new Matrix<>(ROW_COUNT, COL_COUNT);
        matrix.setData(new Integer[ROW_COUNT][COL_COUNT]);

        Utils.foreach((i, j) -> matrix.getData()[i][j] = RANDOM.nextInt(10));

        FileManager.getInstance().writeMatrixToFile(matrix);
        return matrix;
    }
}
