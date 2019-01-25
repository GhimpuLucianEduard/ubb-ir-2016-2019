package com.company.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Constants {

    public static Calendar CALENDAR;
    public static Random RANDOM = new Random(System.currentTimeMillis());

    public static final String FILE_PATH_LOG = "C:\\Users\\Fabby\\Documents\\PPD\\Lab3\\src\\com\\company\\List.log";
    public static final String FILE_PATH_VALUES = "C:\\Users\\Fabby\\Documents\\PPD\\Lab3\\src\\com\\company\\List.values";
    public static final String FILENAME_DATE_FORMAT = "dd_MM__HH_mm_ss";
    public static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SS");

    public static final String FILE_IO_EXCEPTION = "IO EXCEPTION";
    public static List<Double> AddedToList = new ArrayList<>();
}