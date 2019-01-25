package Repo;

import Models.HasID;
import Models.Intrebare;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class FileRepository implements Repository<Intrebare, Integer> {

    private Map<Integer, Intrebare> entities = new TreeMap();
    private String fileName;

    public FileRepository(String fileName) {
        this.fileName = fileName;
        readFromFile();
    }

    @Override
    public Intrebare save(Intrebare entity) {

        if (entities.containsKey(entity.getId())) {
            return entities.get(entity.getId());
        }
        entities.put(entity.getId(), entity);
        return null;
    }

    @Override
    public Intrebare delete(Integer id) {
        Intrebare aux = entities.remove(id);
        return aux;
    }

    @Override
    public Iterable<Intrebare> getAll() {
        return entities.values();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public void readFromFile() {

        Path path = Paths.get(fileName);
        try {
            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> {
                if (line.compareTo("") != 0) {
                    String[] fields = line.split(";");
                    Intrebare e = new Intrebare(Integer.parseInt(fields[0]),fields[1],fields[2],fields[3],fields[4],fields[5],Double.parseDouble(fields[6]));
                    save(e);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}