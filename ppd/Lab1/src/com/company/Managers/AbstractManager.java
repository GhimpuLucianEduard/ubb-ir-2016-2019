package com.company.Managers;

import com.company.Matrix;
import com.company.Threads.AbstractMatrixThread;
import com.company.Utils.FileManager;
import com.company.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.company.Utils.Constants.*;

public abstract class AbstractManager<T extends AbstractMatrixThread> {

    Matrix<Integer> leftMatrix;
    Matrix<Integer> rightMatrix;
    Matrix<Integer> resultMatrix;
    List<T> threads;

    public AbstractManager(Matrix<Integer> leftMatrix, Matrix<Integer> rightMatrix, Matrix<Integer> resultMatrix) {
        this.threads = new ArrayList<>(NR_THREADS);
        this.leftMatrix = leftMatrix;
        this.rightMatrix = rightMatrix;
        this.resultMatrix = resultMatrix;
    }

    public void doOperation() {
        FileManager.getInstance().writeToFile(START_SECVENTIAL_MESSAGE);
        long startTime = System.currentTimeMillis();

        Utils.foreach((i, j) -> sequentialForEachConsumer(i, j));

        long endTime = System.currentTimeMillis();

        FileManager.getInstance().writeToFile(ELAPSED_TIME_MESSAGE + (endTime - startTime));
        FileManager.getInstance().writeMatrixToFile(resultMatrix);
    }

    protected abstract void sequentialForEachConsumer(int i, int j);

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
            int size = (COL_COUNT > ROW_COUNT) ? COL_COUNT : ROW_COUNT;
            addThread(leftMatrix, rightMatrix, resultMatrix, currentStartIndex, currentEndIndex, COL_COUNT);
            FileManager.getInstance().writeToFile("Thread number: " + i + ". Amount of data: " + (currentEndIndex - currentStartIndex));
            currentStartIndex = currentEndIndex;
        }
    }

    protected abstract void addThread(Matrix<Integer> leftMatrix, Matrix<Integer> rightMatrix, Matrix<Integer> resultMatrix, int currentStartIndex, int currentEndIndex, int size);
}
