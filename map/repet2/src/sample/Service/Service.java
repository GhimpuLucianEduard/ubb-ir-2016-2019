package sample.Service;

import sample.Domain.Bug;
import sample.ObserverDP.ListEvent;
import sample.ObserverDP.ListEventType;
import sample.ObserverDP.Observable;
import sample.ObserverDP.Observer;
import sample.Repo.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Service implements Observable<Bug> {
    private Repository repo;
    ArrayList<Observer<Bug>> abonati = new ArrayList<>();

    public Service(Repository repo) {
        this.repo = repo;
    }

    public Bug addBug(String nume, int id) {
        Bug toAdd = new Bug(nume, id);
        Bug aux = repo.save(toAdd);
        if(aux==null) {
            ListEvent<Bug> ev = createEvent(ListEventType.ADD,toAdd,getAllBugs());
            notifyObservers(ev);
        }
        return aux;
    }

    public Bug deleteBug(int id) {
        Bug aux = repo.delete(id);
        if(aux!=null) {
            ListEvent<Bug> ev = createEvent(ListEventType.REMOVE,aux,getAllBugs());
            notifyObservers(ev);
        }
        return aux;
    }

    public List<Bug> getAllBugs() {
        return StreamSupport.stream(repo.getAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public void addObserver(Observer<Bug> o) {
        abonati.add(o);
    }

    @Override
    public void removeObserver(Observer<Bug> o) {
        abonati.remove(o);
    }

    @Override
    public void notifyObservers(ListEvent<Bug> event) {
        abonati.forEach(o->o.notifyEvent(event));
    }

    private <E> ListEvent<E> createEvent(ListEventType type, final E element, final List<E> l) {
        return new ListEvent<E>(type) {
            @Override
            public Iterable<E> getList() {
                return l;
            }

            @Override
            public E getElement() {
                return element;
            }
        };
    }

}
