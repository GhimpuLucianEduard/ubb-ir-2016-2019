package com.company.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;

import static com.company.Utils.Constants.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileManager {

    private static FileManager instance_log = null;
    private static FileManager instance_values = null;
    private static String fileName_log;
    private static String fileName_values;

    private FileManager() {
    }

    public static FileManager getInstance(FileOutput fileOutput) {

        switch (fileOutput){
            case LOG:
                if (instance_log == null) {
                    instance_log = new FileManager();
                    fileName_log = FILE_PATH_LOG;
                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter(fileName_log);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    writer.print("");
                    writer.close();

                }
                return instance_log;
            case VALUES:
                if (instance_values == null) {
                    instance_values = new FileManager();
                    fileName_values = FILE_PATH_VALUES;
                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter(fileName_values);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    writer.print("");
                    writer.close();

                }
                return instance_values;
        }
        return null;
    }

    public void writeToFile(String text, FileOutput fileOutput) {
        try {
            if (fileOutput.equals(FileOutput.LOG)) {
                Files.write(Paths.get(fileName_log), (text + System.lineSeparator()).getBytes(UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } else {
                Files.write(Paths.get(fileName_values), (text + System.lineSeparator()).getBytes(UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }
}