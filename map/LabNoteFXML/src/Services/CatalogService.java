package Services;

import Models.Comparatorul;
import Models.Nota;
import Models.Student;
import Models.Tema;
import Models.Validators.ValidationException;
import Utils.GenericFilter;
import Utils.ObserverDP.ListEvent;
import Utils.ObserverDP.ListEventType;
import Utils.ObserverDP.Observable;
import Utils.ObserverDP.Observer;
import Utils.Pair;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Service pentru toate cele 3 entitati
 * Necesita in constructor referinta la cate un service pentru fiecare entitate (student, tema, nota)
 */
public class CatalogService{

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




    public List<Student> filtStudentsByFirstInitiala(String initiala) {
        Predicate<Student> filt = x -> x.getName().startsWith(initiala);
        Comparator<Student> comp = Comparatorul.getInstance()::compareStudentByName;
        List<Student> list = serviceStudent.getAllStudents();
        return GenericFilter.genericFilt(list, filt, comp);
    }

    public List<Student> filtStudentsByProfesor(String profesor) {
        Predicate<Student> filt = x -> x.getProf().compareTo(profesor)==0;
        Comparator<Student> comp = Comparatorul.getInstance()::compareStudentByGrupa;
        List<Student> list = serviceStudent.getAllStudents();
        return GenericFilter.genericFilt(list, filt, comp);
    }

    public List<Student> filtStudentsByGrupa(int grupa) {
        Predicate<Student> filt = x -> x.getGroup() == grupa;
        Comparator<Student> comp = Comparatorul.getInstance()::compareStudentByName;
        List<Student> list = serviceStudent.getAllStudents();
        return GenericFilter.genericFilt(list, filt, comp);
    }


    public List<Nota> filtNoteByStudent(String id) {
        Predicate<Nota> filt = x -> x.getIdStudent().compareTo(id)==0;
        Comparator<Nota> comp = Comparatorul.getInstance()::compareNoteByValue;
        List<Nota> list = serviceNota.getAllNote();
        return GenericFilter.genericFilt(list, filt, comp);
    }


    public List<Nota> filtNoteByValoare(int value) {
        Predicate<Nota> filt = x -> x.getValoare()>=value;
        Comparator<Nota> comp = Comparatorul.getInstance()::compareNoteByStudent;
        List<Nota> list = serviceNota.getAllNote();
        return GenericFilter.genericFilt(list, filt, comp);
    }

    public List<Nota> filtNoteByTema(int value) {
        Predicate<Nota> filt = x -> x.getIdTema() == value;
        Comparator<Nota> comp = Comparatorul.getInstance()::compareNoteByValue;
        List<Nota> list = serviceNota.getAllNote();
        return GenericFilter.genericFilt(list, filt, comp);
    }

    public List<Tema> filtTemaByDeadLine(int deadline) {
        Predicate<Tema> filt = x -> x.getDeadline() <= deadline;
        Comparator<Tema> comp = Comparatorul.getInstance()::compareTemeByDeadLine;
        List<Tema> list = serviceTema.getAllTeme();
        return GenericFilter.genericFilt(list, filt, comp);

    }

    public List<Tema> filtTemaByDescriere(String desc) {
        Predicate<Tema> filt = x -> x.getInfo().contains(desc);
        Comparator<Tema> comp = Comparatorul.getInstance()::compareTemeByNumar;
        List<Tema> list = serviceTema.getAllTeme();
        return GenericFilter.genericFilt(list, filt, comp);

    }

    public List<Tema> filtTemaByNumar(int numar) {
        Predicate<Tema> filt = x -> x.getId() <= numar;
        Comparator<Tema> comp = Comparatorul.getInstance()::compareTemeByNumar;
        List<Tema> list = serviceTema.getAllTeme();
        return GenericFilter.genericFilt(list, filt, comp);

    }


    /**
     * @param valoare valoare notei de ajustat
     * @param deadline deadline-ul asociat temei respective
     * @param saptPredat saptamana predarii temei, asociata notei
     * @return noua valoare, respectant urmatoarea regula: daca o nota este predata la o distanta mai mare de 2 saptamani fata de deadline,
     * atunci se noteaza cu 1, altfel se scad 2 puncte pentru fiecare saptamana intarziere
     */
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

        File fis = new File("C:\\Users\\Deus\\IdeaProjects\\LabNoteFXML\\src\\Catalog\\" + idStudent + ".txt");
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
            File fis = new File("C:\\Users\\Deus\\IdeaProjects\\LabNoteFXML\\src\\Catalog\\" + idStudent + ".txt");
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
        return serviceNota.findNota(idStudent+idTema);
    }


    /**
     * @return o pereche Tema, Integer, reprezentad cea mai grea tema, respectiv cati studenti au intarziat la tema respectiva
     */
    public Pair ceaMaiGreaTema() {

        List<Pair<Tema,Integer>> frecventa = new ArrayList<>();
        serviceTema.getAllTeme().forEach(x->{
            Pair<Tema,Integer> newTema = new Pair(x,0);
            serviceNota.getAllNote().forEach(y->{
                if((y.getIdTema()==x.getId()) && (y.getSaptPredare()-x.getDeadline() >=2)) {
                    int oldCount = newTema.getSecond();
                    newTema.setSecond(oldCount+1);
                }
            });
            frecventa.add(newTema);
        });

        Pair<Tema,Integer> maxim = new Pair(null,0);
        frecventa.forEach(x-> {
            if (x.getSecond()>maxim.getSecond()) {
                maxim.setBoth(x.getFirst(),x.getSecond());
            }
        });
        return maxim;
    }

    public List<Pair<Student,Double>> medii() {
        List<Pair<Student,Double>> medii = new ArrayList<>();
        getServiceStudent().getAllStudents().forEach(x->{
            medii.add(new Pair<Student,Double>(x,getMedie(x)));
        });
        return medii;
    }

    /**
     * Metoda pentru calcularea mediei unui singur student
     * @param st studentul pentru care se calculeaza mediea
     * @return un double reprezentand media (momentatn aritmetica)
     */
    public double getMedie(Student st) {
        final double[] medie = {0};
        getServiceNota().getAllNote().forEach(x->{
            if (x.getIdStudent().compareTo(st.getId())==0) {
                medie[0] = medie[0] + x.getValoare();
            }
        });
        return medie[0]/(getServiceTema().getAllTeme().size());
    }

    /**
     * Metoda care calculeaza media notelor la o tema data
     * @param tm tema la care se doreste calcularea medie
     * @return un double, reprezentand media la tema respectiva
     */
    public double getMedieTema(Tema tm) {
        final double[] medie = {0};
        getServiceNota().getAllNote().forEach(x->{
            if (x.getIdTema()==tm.getId()) {
                medie[0] = medie[0] + x.getValoare();
            }
        });
        return medie[0]/(getServiceStudent().getAllStudents().size());
    }


    public List<Pair<Tema,Double>> getMediiTeme() {
        List<Pair<Tema,Double>> medii = new ArrayList<>();
        getServiceTema().getAllTeme().forEach(x->{
            medii.add(new Pair<Tema,Double>(x,getMedieTema(x)));
        });
        return medii;
    }

    public List<Pair<Integer,Integer>> getNrMedii() {
        List<Integer> medii = null;
        medii.add(1);
        medii.add(2);
        medii.add(3);
        medii.add(4);
        medii.add(5);
        medii.add(6);
        medii.add(7);
        medii.add(8);
        medii.add(9);
        medii.add(10);
        List<Pair<Integer,Integer>> nrMedii = new ArrayList<>();
        medii.forEach(x->{
            final int[] cate = {0};
            serviceStudent.getAllStudents().forEach(st->{
                if (Math.round(getMedie(st))==x) {
                    cate[0]++;
                }
            });
            nrMedii.add(new Pair<Integer,Integer>(x,cate[0]));

        });
        return nrMedii;
    }

    public List<Pair<String,Integer>> getNepredate() {
        List<Pair<String,Integer>> rez = new ArrayList<>();
        serviceStudent.getAllStudents().forEach(st->{
            serviceTema.getAllTeme().forEach(tm->{
                if(!serviceNota.findNota(st.getId()+String.valueOf(tm.getId())).isPresent()) {
                    Pair<String,Integer> newpair = new Pair<>(st.getId(),tm.getId());
                    rez.add(newpair);
                }
            });
        });

        return rez;
    }



}

