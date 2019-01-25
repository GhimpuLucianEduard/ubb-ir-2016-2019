package com.company;

import com.company.Managers.GenericOperationManager;
import com.company.Managers.MultiplicationManager;
import com.company.Utils.Constants;
import com.company.Utils.FileManager;
import com.company.Utils.Utils;

import java.util.Calendar;
import java.util.Scanner;

import static com.company.Utils.Constants.*;

public class Main {

    public static void main(String[] args) {

        Constants.CALENDAR = Calendar.getInstance();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Numar de randuri:");
        ROW_COUNT = scanner.nextInt();
        System.out.println("Numar de coloane:");
        Constants.COL_COUNT = scanner.nextInt();
        System.out.println("Numar de threaduri:");
        Constants.NR_THREADS = scanner.nextInt();

        Matrix<Integer> leftMatrix = Utils.generateMatrix();
        Matrix<Integer> rightMatrix = Utils.generateMatrix();
        Matrix<Integer> resultMatrix = new Matrix<>(ROW_COUNT, COL_COUNT);
        resultMatrix.setData(new Integer[ROW_COUNT][COL_COUNT]);

        FileManager.getInstance().writeToFile(START_SUM_MESSAGE);

        GenericOperationManager sumManager = new GenericOperationManager(leftMatrix, rightMatrix, resultMatrix, (a, b) -> a + b);
        sumManager.doOperation();
        resultMatrix.clear();
        sumManager.doOperationParallel();
        resultMatrix.clear();

//        FileManager.getInstance().writeToFile(START_MULT_MESSAGE);
//
//        MultiplicationManager multiplicationManager = new MultiplicationManager(leftMatrix, rightMatrix, resultMatrix);
//        multiplicationManager.doOperation();
//        resultMatrix.clear();
//        multiplicationManager.doOperationParallel();
//        resultMatrix.clear();
    }
}
