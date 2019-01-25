package Services;

import Models.Student;
import Models.Tema;
import Models.Validators.ValidationException;
import Repos.Repository;
import Utils.Cronos;
import Utils.ObserverDP.ListEvent;
import Utils.ObserverDP.ListEventType;
import Utils.ObserverDP.Observable;
import Utils.ObserverDP.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TemaService implements Observable<Tema> {
    public int saptCurenta = Cronos.getInstance().getSaptCurent();
    private Repository<Tema, Integer> repo;
    ArrayList<Observer<Tema>> temaObservers=new ArrayList<>();

    public TemaService(Repository<Tema, Integer> repo) {
        this.repo = repo;
    }

    public Optional<Tema> addTema(int id, String info, int deadline) throws ValidationException {
        Tema tm = new Tema(id,info,deadline);
        Optional<Tema> aux =  repo.save(tm);
        if(!aux.isPresent()) {
            ListEvent<Tema> ev = createEvent(ListEventType.ADD,tm,getAllTeme());
            notifyObservers(ev);
        }
        return aux;
    }

    public Optional<Tema> addTema(Tema tm) throws ValidationException {
        Optional<Tema> aux =  repo.save(tm);
        if(!aux.isPresent()) {
            ListEvent<Tema> ev = createEvent(ListEventType.ADD,tm,getAllTeme());
            notifyObservers(ev);
        }
        return aux;
    }


    public Optional<Tema> removeTema(int id) {
        Optional<Tema> aux = repo.delete(id);
        if(aux.isPresent()) {
            ListEvent<Tema> ev = createEvent(ListEventType.REMOVE,aux.get(),getAllTeme());
            notifyObservers(ev);
        }
        return aux;
    }

    public Optional<Tema> findTemaById(int id) {
        return repo.find(id);
    }

    public Optional<Tema> updateTema(int id, String info, int deadline) throws ValidationException {
        Tema tm = new Tema(id,info,deadline);
        Optional<Tema> aux = repo.update(tm);
        if(aux.isPresent()) {
            ListEvent<Tema> ev = createEvent(ListEventType.UPDATE,aux.get(),getAllTeme());
            notifyObservers(ev);
        }
        return aux;
    }

    public Optional<Tema> updateTema(Tema tm) throws ValidationException {
        Optional<Tema> aux = repo.update(tm);
        if(!aux.isPresent()) {
            ListEvent<Tema> ev = createEvent(ListEventType.UPDATE,tm,getAllTeme());
            notifyObservers(ev);
        }
        return aux;
    }

    public int getSaptCurenta() {
        return saptCurenta;
    }

    public List<Tema> getAllTeme() {
        return StreamSupport.stream(repo.getAll().spliterator(),false).collect(Collectors.toList());
    }

    public Optional<Tema> prelungire(int id, int deadLineNou) throws ValidationException {
        Optional<Tema> tm = repo.find(id);

        if(deadLineNou > 14 || deadLineNou <= tm.get().getDeadline()) {
            throw new ValidationException("Data noua invalida!");
        }
        if(saptCurenta >= tm.get().getDeadline()) {
            throw new ValidationException("Prea tarziu pentru a modifica deadline-ul!");
        }
        tm.get().setDeadline(deadLineNou);
        return tm;
    }

    @Override
    public void addObserver(Observer<Tema> o) {
        temaObservers.add(o);
    }

    @Override
    public void removeObserver(Observer<Tema> o) {
        temaObservers.remove(o);
    }

    @Override
    public void notifyObservers(ListEvent<Tema> event) {
        temaObservers.forEach(x->x.notifyEvent(event));
    }

    private <E> ListEvent<E> createEvent(ListEventType type, final E elem, final List<E> l) {
        return new ListEvent<E>(type) {
            @Override
            public Iterable<E> getList() {
                return l;
            }

            @Override
            public E getElement() {
                return elem;
            }
        };
    }

    public List<Integer> getAllTemeId() {
        List<Integer> rez = new ArrayList<>();
        this.getAllTeme().forEach(x->{
            rez.add(x.getId());
        });
        return rez;
    }
}
