package Services;

import Models.Comparatorul;
import Models.Nota;
import Models.Student;
import Models.Tema;
import Models.Validators.ValidationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CatalogService {

    private TemaService serviceTema;
    private NotaService serviceNota;
    private StudentService serviceStudent;

    public TemaService getServiceTema() {
        return serviceTema;
    }

    public NotaService getServiceNota() {
        return serviceNota;
    }

    public StudentService getServiceStudent() {
        return serviceStudent;
    }

    public CatalogService(TemaService serviceTema, NotaService serviceNota, StudentService serviceStudent) {
        this.serviceTema = serviceTema;
        this.serviceNota = serviceNota;
        this.serviceStudent = serviceStudent;
    }

    public <E> List<E> genericFilt(List<E> entities, Predicate<E> filt, Comparator<E> comp) {
        return entities.stream().filter(filt).sorted(comp).collect(Collectors.toList());
    }

    public List<Student> filtStudentsByFirstInitiala(String initiala) {
        Predicate<Student> filt = x -> x.getName().startsWith(initiala);
        Comparator<Student> comp = Comparatorul.getInstance()::compareStudentByName;
        List<Student> list = serviceStudent.getAllStudents();
        return genericFilt(list, filt, comp);
    }

    public List<Student> filtStudentsByProfesor(String profesor) {
        Predicate<Student> filt = x -> x.getProf().compareTo(profesor)==0;
        Comparator<Student> comp = Comparatorul.getInstance()::compareStudentByGrupa;
        List<Student> list = serviceStudent.getAllStudents();
        return genericFilt(list, filt, comp);
    }

    public List<Student> filtStudentsByGrupa(int grupa) {
        Predicate<Student> filt = x -> x.getGroup() == grupa;
        Comparator<Student> comp = Comparatorul.getInstance()::compareStudentByName;
        List<Student> list = serviceStudent.getAllStudents();
        return genericFilt(list, filt, comp);
    }


    public List<Nota> filtNoteByStudent(String id) {
        Predicate<Nota> filt = x -> x.getIdStudent().compareTo(id)==0;
        Comparator<Nota> comp = Comparatorul.getInstance()::compareNoteByValue;
        List<Nota> list = serviceNota.getAllNote();
        return genericFilt(list, filt, comp);
    }


    public List<Nota> filtNoteByValoare(int value) {
        Predicate<Nota> filt = x -> x.getValoare()>=value;
        Comparator<Nota> comp = Comparatorul.getInstance()::compareNoteByStudent;
        List<Nota> list = serviceNota.getAllNote();
        return genericFilt(list, filt, comp);
    }

    public List<Nota> filtNoteByTema(int value) {
        Predicate<Nota> filt = x -> x.getIdTema() == value;
        Comparator<Nota> comp = Comparatorul.getInstance()::compareNoteByValue;
        List<Nota> list = serviceNota.getAllNote();
        return genericFilt(list, filt, comp);
    }

    public List<Tema> filtTemaByDeadLine(int deadline) {
        Predicate<Tema> filt = x -> x.getDeadline() <= deadline;
        Comparator<Tema> comp = Comparatorul.getInstance()::compareTemeByDeadLine;
        List<Tema> list = serviceTema.getAllTeme();
        return genericFilt(list, filt, comp);

    }

    public List<Tema> filtTemaByDescriere(String desc) {
        Predicate<Tema> filt = x -> x.getInfo().contains(desc);
        Comparator<Tema> comp = Comparatorul.getInstance()::compareTemeByNumar;
        List<Tema> list = serviceTema.getAllTeme();
        return genericFilt(list, filt, comp);

    }

    public List<Tema> filtTemaByNumar(int numar) {
        Predicate<Tema> filt = x -> x.getId() <= numar;
        Comparator<Tema> comp = Comparatorul.getInstance()::compareTemeByNumar;
        List<Tema> list = serviceTema.getAllTeme();
        return genericFilt(list, filt, comp);

    }

    public double ajustareNota(double valoare, int deadline, int saptPredat) {
        if(saptPredat - deadline > 2) {
            return 1;
        }
        else if(saptPredat>deadline) {
            return valoare-(saptPredat-deadline)*2;
        }
        else return valoare;
    }

    public Optional<Nota> adaugareNota(String idStudent, int idTema, double valoare, int saptPredat, String obs) throws ValidationException {
        if (!serviceStudent.findStudentById(idStudent).isPresent() || !serviceTema.findTemaById(idTema).isPresent()) {
            throw new ValidationException("Id student sau id tema invalide");
        }

        double aux = ajustareNota(valoare, serviceTema.findTemaById(idTema).get().getDeadline(), saptPredat);

        File fis = new File("File fis  = new File(C:\\Users\\Deus\\IdeaProjects\\LabNoteFX\\src\\Catalog\\" + idStudent + ".txt");
        try {
            fis.createNewFile(); //daca fisierul deja exista nu fa nimic
        } catch (IOException e) {

        } finally {
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(fis.toPath(), StandardOpenOption.APPEND)) {
                if (fis.length() == 0) {
                    String legenda = "%-20s %-10s %-10s %-10s %-15s %-30s%n";
                    bufferedWriter.write(String.format(legenda, "Actiune", "Nr tema", "Nota", "Deadline", "Sapt. Predarii", "Observatii"));
                }
                String formatStr = "%-20s %-10d %-10f %-10d %-10d %-30s%n";
                bufferedWriter.write(String.format(formatStr, "Adaugare Nota", idTema, aux, serviceTema.findTemaById(idTema).get().getDeadline(), saptPredat, obs));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return serviceNota.addNota(idStudent, idTema, aux, saptPredat);
        }
    }

    public Optional<Nota> modificareNota(String idStudent, int idTema, double valoare, int saptPredat, String obs) throws ValidationException {
        if (!serviceStudent.findStudentById(idStudent).isPresent() || !serviceTema.findTemaById(idTema).isPresent()) {
            throw new ValidationException("Id student sau id tema invalide");
        }

        double aux = ajustareNota(valoare, serviceTema.findTemaById(idTema).get().getDeadline(), saptPredat);

        if(aux>serviceNota.findNota(idStudent+idTema).get().getValoare()) {
            File fis = new File("File fis  = new File(C:\\Users\\Deus\\IdeaProjects\\LabNoteFX\\src\\Catalog\\" + idStudent + ".txt");
            try {
                fis.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try(BufferedWriter bufferedWriter = Files.newBufferedWriter(fis.toPath(),StandardOpenOption.APPEND)) {
                    String formatStr = "%-20s %-10d %-10f %-10d %-10d %-30s%n";
                    bufferedWriter.write(String.format(formatStr,"Modificare Nota", idTema, aux, serviceTema.findTemaById(idTema).get().getDeadline(), saptPredat, obs));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return serviceNota.updateNota(idStudent,idTema,aux,saptPredat);
        }
        return null;
    }



}

