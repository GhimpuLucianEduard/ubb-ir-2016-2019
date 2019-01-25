package com.company.Utils;

import com.company.Matrix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;

import static com.company.Utils.Constants.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileManager {

    private static FileManager instance = null;
    private static String fileName;

    private FileManager() {
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
            String date = new SimpleDateFormat(FILENAME_DATE_FORMAT).format(CALENDAR.getTime());
            fileName = FILE_PATH + date;
        }
        return instance;
    }

    public void writeToFile(String text) {
        try {
            Files.write(Paths.get(fileName), (text + System.lineSeparator()).getBytes(UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(FILE_IO_EXCEPTION);
        }
    }

    public void writeMatrixToFile(Matrix matrix) {
        if (Constants.COL_COUNT <= 100 && Constants.ROW_COUNT <= 100) {
            writeToFile(matrix.toString());
        } else {
            writeToFile(MATRIX_TO_BIG_TO_WRITE);
        }
    }
}
