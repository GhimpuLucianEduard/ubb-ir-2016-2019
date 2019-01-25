import javax.annotation.Resources;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileManager {

    private static final String FILE_PATH = "C:\\Users\\Fabby\\Documents\\PPD\\Lab4PPD\\Server\\src\\main\\resources\\log.log";
    private static FileManager instance = null;

    private FileManager() {
    }

    public String getFilePath() {
        return FILE_PATH;
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public void writeToFile(String text) {
        try {
            Files.write(Paths.get(FILE_PATH), (text + System.lineSeparator()).getBytes(UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}