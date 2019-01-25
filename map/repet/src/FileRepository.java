import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;

public class FileRepository<E extends HasID<ID>, ID> implements Repository<E, ID> {
    //private Validator<E> vali;
    private Map<ID, E> entities = new TreeMap();
    private String fileName;

    @Override
    public long size() {
        return entities.size();
    }

    @Override
    public E save(E entity) {
        //vali.validate(entity);
        if (entities.containsKey(entity.getId())) {
            return entities.get(entity.getId());
        }
        entities.put(entity.getId(), entity);
        writeToFile(entity);
        return null;
    }

    @Override
    public E delete(ID id) {
        E aux = entities.remove(id);
        writeToFile();
        return aux;
    }

    @Override
    public E update(E entity) {
        //vali.validate(entity);
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
            writeToFile();
            return null;

        }
        return entity;
    }

    @Override
    public E find(ID id) {
        return entities.get(id);
    }

    @Override
    public Iterable<E> getAll() {
        return entities.values();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeToFile() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fileName))) {
            for (E e : getAll()) {
                bufferedWriter.write(e.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(E entity) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.APPEND)) {
            bufferedWriter.write(entity.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try (BufferedReader in = new BufferedReader(new FileReader(getFileName()))) {
            String linie;
            while ((linie = in.readLine()) != null) {
                String[] fields = linie.split(";");
                //E entity = new (...);
                //save(entity);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}