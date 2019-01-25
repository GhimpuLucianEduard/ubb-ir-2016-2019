package Service;

import Models.Intrebare;
import Observer.Observable;
import Observer.Observer;
import Observer.ListEvent;
import Observer.ListEventType;
import Repo.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceIntrebari implements Observable<Intrebare> {
    private Repository<Intrebare,Integer> repo;
    ArrayList<Observer<Intrebare>> obs = new ArrayList<>();
    List<Trio> raspunsuri = new ArrayList<>();

    public ServiceIntrebari(Repository<Intrebare, Integer> repo) {
        this.repo = repo;
    }


    public List<Intrebare> getAll() {
        return StreamSupport.stream(repo.getAll().spliterator(), false).collect(Collectors.toList());
    }

    public Intrebare deleteIntrebare(int id) {
        Intrebare aux = repo.delete(id);;
        if(aux!=null) {
            ListEvent<Intrebare> ev = createEvent(ListEventType.REMOVE,aux,getAll());
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
        obs.forEach(x->x.notifyEvent(event));
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

