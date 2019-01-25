package sample.Repo;

import javafx.scene.layout.AnchorPane;
import sample.domain.Angajat;
import sample.domain.tipAngajat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class FileRepositoryAngajat {

    private Map<String, Angajat> entities = new TreeMap();
    private String fileName;

    public Iterable<Angajat> getAll() {
        return entities.values();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public FileRepositoryAngajat(String fileName) {
        this.fileName = fileName;
        readFromFile();
    }


    public Angajat update(Angajat entity)
    {
        if(entities.containsKey(entity.getNume()))
        {
            entities.put(entity.getNume(),entity);
            return null;

        }
        return entity;
    }

    public void readFromFile() {

        Path path = Paths.get(fileName);
        try {
            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> {
                if (line.compareTo("") != 0) {
                    String[] fields = line.split(";");
                    Angajat a = new Angajat(fields[0], tipAngajat.valueOf(fields[1]));
                    entities.put(a.getNume(),a);

                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}