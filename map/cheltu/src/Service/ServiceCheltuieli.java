package Service;

import Domain.Cheltuiala;
import Domain.Membru;
import Domain.TipCheltuiala;
import Domain.TipMembru;
import ObserverDP.ListEvent;
import ObserverDP.ListEventType;
import ObserverDP.Observable;
import ObserverDP.Observer;
import Repository.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ServiceCheltuieli implements Observable<Cheltuiala>{

    Repository<Cheltuiala,String> repo;
    ArrayList<Observer<Cheltuiala>> obs = new ArrayList<>();
    private List<Membru> membri = new ArrayList<>();
    private String fileMembrii;

    public String getFileMembrii() {
        return fileMembrii;
    }

    public void setFileMembrii(String fileMembrii) {
        this.fileMembrii = fileMembrii;
    }

    public ServiceCheltuieli(Repository<Cheltuiala, String> repo, String fileMembrii) {
        this.repo = repo;
        this.fileMembrii = fileMembrii;
        getMembrii();
    }

    public List<Cheltuiala> getAllCheltuiali() {
        return StreamSupport.stream(repo.getAll().spliterator(),false).collect(Collectors.toList());
    }

    public List<Cheltuiala> getCheltuialaByUser(String user) {
        //Predicate<Cheltuiala> filt = x -> x.getEfectuatDe().compareTo(user);
        List<Cheltuiala> rez =  getAllCheltuiali().stream().filter(x->x.getEfectuatDe().compareTo(user)==0).collect(Collectors.toList());
        return rez;
    }


    public Cheltuiala addCheltuiala(Cheltuiala ct) {

        Cheltuiala aux = repo.save(ct);
        if(aux==null) {
            ListEvent<Cheltuiala> ev = createEvent(ListEventType.ADD,ct,getAllCheltuiali());
            notifyObservers(ev);
        }
        return aux;
    }

    @Override
    public void addObserver(Observer<Cheltuiala> o) {
        obs.add(o);
    }

    @Override
    public void removeObserver(Observer<Cheltuiala> o) {
        obs.remove(o);
    }

    @Override
    public void notifyObservers(ListEvent<Cheltuiala> event) {
        obs.forEach(o->o.notifyEvent(event));
    }

    private <E> ListEvent<E> createEvent(ListEventType type, final E elem, final List<E> l){
        return new ListEvent<E>(type) {
            @Override
            public List<E> getList() {
                return l;
            }
            @Override
            public E getElement() {
                return elem;
            }
        };
    }

    public List<Membru> getMembri() {
        return membri;
    }

    public void getMembrii() {
        Path path = Paths.get(fileMembrii);
        try {
            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> {
                if (line.compareTo("") != 0) {
                    String[] fields = line.split(" ");
                    String nume = fields[0];
                    TipMembru tip = TipMembru.valueOf(fields[1]);
                    String[] fields2 = fields[2].split(":");
                    double suma = Double.parseDouble(fields2[1]);
                    Membru m = new Membru(nume,tip,suma);
                    membri.add(m);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public Double returnSumaRamasa(String user) {
        List<Membru> rez = getMembri().stream().filter(x->x.getNume().compareTo(user)==0).collect(Collectors.toList());
        return rez.get(0).getSumaDisp();
    }
}
