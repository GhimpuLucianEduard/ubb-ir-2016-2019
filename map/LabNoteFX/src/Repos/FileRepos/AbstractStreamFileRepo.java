package Repos.FileRepos;

import Models.HasID;
import Models.Validators.ValidationException;
import Models.Validators.Validator;
import Repos.AbstractRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class AbstractStreamFileRepo<E extends HasID<ID>,ID> extends AbstractRepository<E, ID> implements  FileRepository<E,ID> {

    /**
     * @param vali validatorul specific obiectului E
     * @param fileName
     */
    public AbstractStreamFileRepo(Validator<E> vali, String fileName) {
        super(vali);
        this.fileName = fileName;
        readFromFile();
    }

    private String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void readFromFile() {
        Path path = Paths.get(fileName);

        try {
            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> {
                if (line.compareTo("")!=0) {
                    String[] fields = line.split(";");
                    E t = buildEntity(fields);
                    try {
                        super.save(t);
                    } catch (ValidationException e) {
                        e.getMessage();
                    }
                }
            });
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    abstract E buildEntity(String[] fields);

    public void writeToFile(E e)
    {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(e.toString());
            bufferedWriter.newLine();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeToFile() {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (E e: super.getAll()) {
                bufferedWriter.write(e.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public Optional<E> save(E entity) throws ValidationException
    {
        Optional<E> aux = super.save(entity);
        if (!aux.isPresent()) {
            writeToFile(entity);
        }
        return aux;
    }

    @Override
    public Optional<E> update(E entity) throws ValidationException
    {
        Optional<E> e = super.update(entity);
        if (!e.isPresent()) {
            writeToFile();
        }
        return e;
    }

    @Override
    public Optional<E> delete(ID id) {
        Optional<E> aux = super.delete(id);
        writeToFile();
        return aux;
    }
}
