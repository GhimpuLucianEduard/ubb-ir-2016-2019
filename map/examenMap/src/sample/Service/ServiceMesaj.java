package sample.Service;

import ObserverDP.ListEvent;
import ObserverDP.ListEventType;
import ObserverDP.Observable;
import ObserverDP.Observer;
import sample.Repo.FileRepositoryMesaje;
import sample.domain.Angajat;
import sample.domain.Mesaj;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceMesaj implements Observable<Mesaj>{
    ArrayList<Observer<Angajat>> obs = new ArrayList<>();
    FileRepositoryMesaje repo;

    public ServiceMesaj(FileRepositoryMesaje repo) {
        this.repo = repo;
    }

    public List<Mesaj> getAll() {
        return StreamSupport.stream(repo.getAll().spliterator(), false).collect(Collectors.toList());
    }

    public Mesaj addMesaj(Mesaj m) {
        Mesaj aux = repo.save(m);
        notifyObservers(new ListEvent<Mesaj>(ListEventType.MESAJNOU) {
            @Override
            public Iterable<Mesaj> getList() {
                return getAll();
            }

            @Override
            public Mesaj getElement() {
                return aux;
            }
        });
        return aux;
    }

    @Override
    public void addObserver(Observer<Mesaj> o) {

    }

    @Override
    public void removeObserver(Observer<Mesaj> o) {

    }

    @Override
    public void notifyObservers(ListEvent<Mesaj> event) {

    }



}
