package com.company.Utils;

import com.company.ComplexNumber;

import java.util.Calendar;
import java.util.Random;
import java.util.function.BinaryOperator;

public class Constants {

    public static Calendar CALENDAR;
    public static Random RANDOM = new Random(System.currentTimeMillis());

    //region File I/O
    public static final String FILE_PATH = "C:\\Users\\Fabby\\IdeaProjects\\Lab2\\src\\com\\company\\Executii\\";
    public static final String FILENAME_DATE_FORMAT = "dd_MM__HH_mm_ss";
    //endregion

    //region MISC
    public static final String MATRIX_TO_BIG_TO_WRITE = "Matrix to big to write.";
    public static final String ELAPSED_TIME_MESSAGE = "Elapsed time: ";
    public static final String START_SECVENTIAL_MESSAGE = "=============== SECVENTIAL ===============";
    public static final String START_PARALLEL_MESSAGE = "=============== PARALLEL ===============";
    public static final String START_SUM_MESSAGE = "=======================START SUM=======================";
    public static final String START_MULT_MESSAGE = "=======================START MULT=======================";
    public static final String START_MESSAGE = "=================START======================";
    //endregion

    //region Matrix
    public static int ROW_COUNT = 5;
    public static int COL_COUNT = 5;
    public static int NR_THREADS = 4;
    //endregion

    //region Exceptions messages
    public static final String FILE_IO_EXCEPTION = "IO EXCEPTION";
    //endregion

    //predefined operators
    public static BinaryOperator<Integer> sumInteger = (integer, integer2) -> integer + integer2;

    public static BinaryOperator<Integer> multInteger = (integer, integer2) -> integer * integer2;

    public static BinaryOperator<Float> sumFloat = (float1, float2) -> float1 + float2;

    public static BinaryOperator<Float> multFloat = (float1, float2) -> (float)(float1 * float2);

    public static BinaryOperator<Float> combFloat = (float1, float2) -> 1 / ((1 / float1) + (1 / float2));

    public static BinaryOperator<ComplexNumber> sumComplex = (complexNumber1, complexNumber2) -> complexNumber1.add(complexNumber2);

    public static BinaryOperator<ComplexNumber> multComplex = (complexNumber1, complexNumber2) -> complexNumber1.multiply(complexNumber2);

    public static BinaryOperator<ComplexNumber> combComplex = (complexNumber1, complexNumber2) -> (complexNumber1.multiply(complexNumber2)).division(complexNumber1.add(complexNumber2));
}
