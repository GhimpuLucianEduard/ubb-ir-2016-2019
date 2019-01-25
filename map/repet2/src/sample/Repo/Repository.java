package sample.Repo;

import sample.Domain.Bug;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Repository {
    private String fileName;
    private Map<Integer, Bug> entitati = new TreeMap<>();

    public Repository(String fileName) {
        this.fileName = fileName;
        loadData();
    }

    public void loadData() {
        try(BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while((linie=in.readLine())!=null) {
                String[] fields = linie.split(";");
                Bug bug = new Bug(fields[0],Integer.parseInt(fields[1]));
                save(bug);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData() {
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fileName))) {
            for (Bug e : getAll()) {
                bufferedWriter.write(e.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeOne(Bug bug) {
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.APPEND)) {
            bufferedWriter.write(bug.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bug save(Bug bug) {
        if(entitati.containsKey(bug.getId())) {
            return entitati.get(bug.getId());
        }
        entitati.put(bug.getId(),bug);
        return null;
    }

    public Bug delete(int id) {
        return entitati.remove(id);
    }

    public Iterable<Bug> getAll()
    {
        return entitati.values();
    }

}
