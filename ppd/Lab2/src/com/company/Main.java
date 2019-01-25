package com.company;

import com.company.Utils.Constants;
import com.company.Utils.FileManager;
import com.company.Utils.Utils;

import java.util.Calendar;
import java.util.Scanner;
import java.util.function.BinaryOperator;

import static com.company.Utils.Constants.*;

public class Main {

    public static void main(String[] args) {
        Constants.CALENDAR = Calendar.getInstance();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Numar de randuri:");
        ROW_COUNT = scanner.nextInt();
        System.out.println("Numar de coloane:");
        COL_COUNT = scanner.nextInt();
        System.out.println("Numar de threaduri:");
        Constants.NR_THREADS = scanner.nextInt();

        Matrix leftMatrix = new Matrix<>(ROW_COUNT, COL_COUNT);
        Matrix rightMatrix = new Matrix<>(ROW_COUNT, COL_COUNT);
        Matrix resultMatrix = new Matrix<>(ROW_COUNT, COL_COUNT);

        System.out.println("Tip de data: 1 - flaot    2 - complex");
        int tip = scanner.nextInt();

        if (tip == 1) {
            System.out.println("Alege operatia: 1 - inmultire    2 - combinata (1/(1/a)+(1/b))");
            leftMatrix = Utils.generateMatrix(Utils.DataType.FLOAT);
            rightMatrix = Utils.generateMatrix(Utils.DataType.FLOAT);
            resultMatrix.setData(new Float[ROW_COUNT][COL_COUNT]);
            int oper = scanner.nextInt();
            BinaryOperator binaryOperator = (oper == 1) ? multFloat : combFloat;
            FileManager.getInstance().writeToFile(START_MESSAGE);
            GenericOperationManager manager = new GenericOperationManager(leftMatrix, rightMatrix, resultMatrix, binaryOperator);
            manager.doOperation();
            resultMatrix.clear();
            manager.doOperationParallel();
            resultMatrix.clear();
        } else {

            System.out.println("Alege operatia: 1 - inmultire    2 - combinata (1/(1/a)+(1/b))");
            leftMatrix = Utils.generateMatrix(Utils.DataType.COMPLEX);
            rightMatrix = Utils.generateMatrix(Utils.DataType.COMPLEX);
            resultMatrix.setData(new ComplexNumber[ROW_COUNT][COL_COUNT]);
            int oper = scanner.nextInt();
            BinaryOperator binaryOperator = (oper == 1) ? multComplex : combComplex;
            FileManager.getInstance().writeToFile(START_MESSAGE);
            GenericOperationManager manager = new GenericOperationManager(leftMatrix, rightMatrix, resultMatrix, binaryOperator);
            manager.doOperation();
            resultMatrix.clear();
            manager.doOperationParallel();
            resultMatrix.clear();
        }
    }
}
