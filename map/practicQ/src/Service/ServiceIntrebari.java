package Service;

import Models.Intrebare;
import Models.Raspuns;
import ObserverDP.ListEvent;
import ObserverDP.ListEventType;
import ObserverDP.Observable;
import ObserverDP.Observer;
import Repo.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceIntrebari implements Observable<Intrebare> {



    public ServiceIntrebari(Repository<Intrebare, Integer> repo) {
        this.repo = repo;
    }

    ArrayList<Observer<Intrebare>> obs = new ArrayList<>();
    private Repository<Intrebare, Integer> repo;

    public List<Intrebare> getAllIntrebari() {
        return StreamSupport.stream(repo.getAll().spliterator(),false).collect(Collectors.toList());
    }
    public Intrebare deleteIntrebare(Integer id) {
        Intrebare aux = repo.delete(id);
        if(aux!=null) {
            ListEvent<Intrebare> ev = new ListEvent<Intrebare>(ListEventType.ADD) {
                @Override
                public Iterable<Intrebare> getList() {
                    return getAllIntrebari();
                }

                @Override
                public Intrebare getElement() {
                    return aux;
                }
            };
            notifyObservers(ev);
        }
        return aux;
    }

    @Override
    public void addObserver(Observer<Intrebare> o) {
        obs.add(o);
    }

    @Override
    public void removeObserver(Observer<Intrebare> o) {
        obs.remove(o);
    }

    @Override
    public void notifyObservers(ListEvent<Intrebare> event) {
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
}
