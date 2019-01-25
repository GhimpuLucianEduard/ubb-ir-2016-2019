package Repos.FileRepos;

import Models.HasID;
import Models.Validators.ValidationException;
import Models.Validators.Validator;
import Repos.AbstractRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public abstract class AbstractFileRepo<E extends HasID<ID>,ID> extends AbstractRepository<E, ID> implements  FileRepository<E,ID> {

    /**
     * Calea catre fisier
     */
    private String fileName;

    /**
     * Constructor care cand se apeleaza se incarca date din fiser in memorie
     * @param vali validatorul specific obiectului E
     * @param fileName calea catre fisierul din care se citeste/scrie
     */
    public AbstractFileRepo(Validator<E> vali, String fileName) {
        super(vali);
        this.fileName = fileName;
        readFromFile();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    abstract E buildEntity(String[] fields);

    @Override
    public void writeToFile() {
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fileName))) {
            for (E e : super.getAll()) {
                bufferedWriter.write(e.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile(E entity) {
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.APPEND)) {
            bufferedWriter.write(entity.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readFromFile() {
        try(BufferedReader in = new BufferedReader(new FileReader(getFileName()))) {
            String linie;
            while((linie=in.readLine())!=null) {
                String[] fields = linie.split(";");
                E entity = buildEntity(fields);
                super.save(entity);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.getMessage();
        }
    }

    @Override
    public Optional<E> save(E entity) throws ValidationException {
        Optional<E> aux = super.save(entity);
        if(!aux.isPresent()) {
            writeToFile(entity);
        }
        return aux;
    }

    @Override
    public Optional<E> delete(ID id) {
        Optional<E> aux = super.delete(id);
        writeToFile();
        return aux;
    }

    @Override
    public Optional<E> update(E entity) throws ValidationException {
        Optional<E> aux = super.update(entity);
        if(!aux.isPresent()) {
            writeToFile();
        }
        return aux;
    }
}
