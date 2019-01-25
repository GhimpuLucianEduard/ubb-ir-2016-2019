package Repository;

import Domain.Cheltuiala;
import Domain.TipCheltuiala;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class FileRepository implements Repository<Cheltuiala,String> {

    private Map<String, Cheltuiala> entities = new TreeMap();
    private String fileName;

    public FileRepository(String fileName) {
        this.fileName = fileName;
        readFromFile();
    }

    @Override
    public Cheltuiala save(Cheltuiala entity) {

        if (entities.containsKey(entity.getId())) {
            return entities.get(entity.getId());
        }
        entities.put(entity.getId(), entity);
        writeToFile(entity);
        return null;
    }

    @Override
    public Iterable<Cheltuiala> getAll() {
        return entities.values();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeToFile() {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (Cheltuiala e : getAll()) {
                bufferedWriter.write(e.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeToFile(Cheltuiala entity) {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(entity.toString());
            bufferedWriter.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readFromFile() {

        Path path = Paths.get(fileName);
        try {
            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> {
                if (line.compareTo("") != 0) {
                    String[] fields = line.split(";");
                    Cheltuiala cl = new Cheltuiala(TipCheltuiala.valueOf(fields[0]),Double.parseDouble(fields[1]),fields[2],fields[3], Date.valueOf(fields[4]));
                    entities.put(cl.getId(), cl);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}