package com.company;

import com.company.Utils.FileManager;
import com.company.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

import static com.company.Utils.Constants.*;

public class GenericOperationManager {

    BinaryOperator operator;
    Matrix leftMatrix;
    Matrix rightMatrix;
    Matrix resultMatrix;
    List<BinaryOperatorThread> threads;

    public GenericOperationManager(Matrix leftMatrix, Matrix rightMatrix, Matrix resultMatrix, BinaryOperator operator) {
        this.threads = new ArrayList<>(NR_THREADS);
        this.leftMatrix = leftMatrix;
        this.rightMatrix = rightMatrix;
        this.resultMatrix = resultMatrix;
        this.operator = operator;
    }

    public void doOperation() {
        FileManager.getInstance().writeToFile(START_SECVENTIAL_MESSAGE);
        long startTime = System.currentTimeMillis();

        Utils.foreach((i, j) -> resultMatrix.getData()[i][j] = operator.apply(leftMatrix.getData()[i][j], rightMatrix.getData()[i][j]));

        long endTime = System.currentTimeMillis();

        FileManager.getInstance().writeToFile(ELAPSED_TIME_MESSAGE + (endTime - startTime));
        FileManager.getInstance().writeMatrixToFile(resultMatrix);
    }

    public void doOperationParallel() {
        FileManager.getInstance().writeToFile(START_PARALLEL_MESSAGE);
        long startTime = System.currentTimeMillis();

        createThreads();

        threads.forEach(thread -> thread.start());
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long endTime = System.currentTimeMillis();

        FileManager.getInstance().writeToFile(ELAPSED_TIME_MESSAGE + (endTime - startTime));
        FileManager.getInstance().writeMatrixToFile(resultMatrix);
    }

    private void createThreads() {
        int numberOfElements = COL_COUNT * ROW_COUNT;
        int roundedElementsPerThread = numberOfElements / NR_THREADS;
        int numberOfRemainedElements = numberOfElements % NR_THREADS;

        FileManager.getInstance().writeToFile("Number of elements in matrix: " + numberOfElements);
        FileManager.getInstance().writeToFile("Rounded number of elements per thread: " + roundedElementsPerThread);
        FileManager.getInstance().writeToFile("Modulo (remained elements): " + numberOfRemainedElements);

        int currentEndIndex = 0;
        int currentStartIndex = 0;
        FileManager.getInstance().writeToFile("Number of threads: " + NR_THREADS);
        for (int i = 0; i < NR_THREADS; i++) {
            currentEndIndex = currentStartIndex + roundedElementsPerThread;
            if (numberOfRemainedElements > 0) {
                currentEndIndex++;
                numberOfRemainedElements--;
            }
            threads.add(new BinaryOperatorThread<>(leftMatrix, rightMatrix, resultMatrix, currentStartIndex, currentEndIndex, COL_COUNT, operator));
            //FileManager.getInstance().writeToFile("Thread number: " + i + ". Amount of data: " + (currentEndIndex - currentStartIndex));
            currentStartIndex = currentEndIndex;
        }
    }
}
