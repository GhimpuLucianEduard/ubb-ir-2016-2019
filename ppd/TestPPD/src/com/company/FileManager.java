package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileManager {

    private static FileManager instance = null;
    private static String fileName;
    public static final String FILENAME_DATE_FORMAT = "dd_MM__HH_mm_ss";
    public static Calendar CALENDAR;

    private FileManager() {
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
            String date = new SimpleDateFormat(FILENAME_DATE_FORMAT).format(CALENDAR.getTime());
            fileName = "C:\\Users\\Fabby\\Documents\\TestPPD\\src\\com\\company\\log.log";
        }
        return instance;
    }

    public void writeToFile(String text) {
        try {
            Files.write(Paths.get(fileName), (text + System.lineSeparator()).getBytes(UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
    }
}