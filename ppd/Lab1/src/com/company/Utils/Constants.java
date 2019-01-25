package com.company.Utils;

import java.util.Calendar;
import java.util.Random;

public class Constants {

    public static Calendar CALENDAR;
    public static Random RANDOM = new Random(System.currentTimeMillis());

    //region File I/O
    public static final String FILE_PATH = "C:\\Users\\Fabby\\Documents\\PPD\\Lab1\\src\\com\\company\\Executii\\";
    public static final String FILENAME_DATE_FORMAT = "dd_MM__HH_mm_ss";
    //endregion

    //region MISC
    public static final String MATRIX_TO_BIG_TO_WRITE = "Matrix to big to write.";
    public static final String ELAPSED_TIME_MESSAGE = "Elapsed time: ";
    public static final String START_SECVENTIAL_MESSAGE = "=============== SECVENTIAL ===============";
    public static final String START_PARALLEL_MESSAGE = "=============== PARALLEL ===============";
    public static final String START_SUM_MESSAGE = "=======================START SUM=======================";
    public static final String START_MULT_MESSAGE = "=======================START MULT=======================";
    //endregion

    //region Matrix
    public static int ROW_COUNT = 5;
    public static int COL_COUNT = 5;
    public static int NR_THREADS = 4;
    //endregion

    //region Exceptions messages
    public static final String FILE_IO_EXCEPTION = "IO EXCEPTION";
    //endregion
}
