package sample.Repo;

import sample.domain.Mesaj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;

public class FileRepositoryMesaje implements Repository<Mesaj> {

    private Map<String, Mesaj> entities = new TreeMap();
    private String fileName;

    public FileRepositoryMesaje(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Mesaj save(Mesaj entity) {
        //vali.validate(entity);
        if (entities.containsKey(entity.getId())) {

            return entities.get(entity.getId());

        }
        entities.put(entity.getId(), entity);
        writeToFile(entity);
        return null;
    }



    @Override
    public Iterable<Mesaj> getAll() {
        return entities.values();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeToFile(Mesaj m) {

        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(m.toString());
            bufferedWriter.newLine();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }





}