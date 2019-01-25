package Services;

import Models.Nota;
import Models.Validators.ValidationException;
import Repos.Repository;
import Utils.ObserverDP.ListEvent;
import Utils.ObserverDP.ListEventType;
import Utils.ObserverDP.Observable;
import Utils.ObserverDP.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class NotaService implements Observable<Nota> {
    private Repository<Nota,String> repo;
    ArrayList<Observer<Nota>> notaObservers=new ArrayList<>();

    public NotaService(Repository<Nota, String> repo) {
        this.repo = repo;
    }

    public Optional<Nota> addNota(String idStudent, int idTema, double valoare, int saptPredat) throws ValidationException {
        Nota nt = new Nota(idStudent,idTema,valoare,saptPredat);
        Optional<Nota> aux =  repo.save(nt);
        if(!aux.isPresent()) {
            ListEvent<Nota> ev = createEvent(ListEventType.ADD,nt,getAllNote());
            notifyObservers(ev);
        }
        return aux;
    }


    public Optional<Nota> addTema(Nota nt) throws ValidationException {
        Optional<Nota> aux =  repo.save(nt);
        if(!aux.isPresent()) {
            ListEvent<Nota> ev = createEvent(ListEventType.ADD,nt,getAllNote());
            notifyObservers(ev);
        }
        return aux;
    }

    public Optional<Nota> findNota(String id) {
        return repo.find(id);
    }

    public Optional<Nota> removeNota(String id) {
        Optional<Nota> aux = repo.delete(id);
        if(aux.isPresent()) {
            ListEvent<Nota> ev = createEvent(ListEventType.REMOVE,aux.get(),getAllNote());
            notifyObservers(ev);
        }
        return aux;
    }



    public Optional<Nota> updateNota(String idStudent, int idTema, double valoare, int saptPredat) throws ValidationException {
        Nota nt = new Nota(idStudent,idTema,valoare,saptPredat);
        Optional<Nota> aux = repo.update(nt);
        if(!aux.isPresent()) {
            ListEvent<Nota> ev = createEvent(ListEventType.UPDATE,nt,getAllNote());
            notifyObservers(ev);
        }
        return aux;
    }

    public Optional<Nota> updateTema(Nota nt) throws ValidationException {
        Optional<Nota> aux = repo.update(nt);
        if(!aux.isPresent()) {
            ListEvent<Nota> ev = createEvent(ListEventType.UPDATE,nt,getAllNote());
            notifyObservers(ev);
        }
        return aux;
    }

    public List<Nota> getAllNote() {
        return StreamSupport.stream(repo.getAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public void addObserver(Observer<Nota> o) {
        notaObservers.add(o);
    }

    @Override
    public void removeObserver(Observer<Nota> o) {
        notaObservers.remove(o);
    }

    @Override
    public void notifyObservers(ListEvent<Nota> event) {
        notaObservers.forEach(x->x.notifyEvent(event));
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


    /**
     * Metoda care returneaza o lista de note asociate unui student
     * @param idStudent, id-ul studentului dorit
     * @return lista de note asociate
     */
    public List<Nota> getNoteByStudent(String idStudent) {
        List<Nota> rez = new ArrayList<>();
        this.getAllNote().forEach(nt->{
            if (nt.getIdStudent().compareTo(idStudent)==0) {
                rez.add(nt);
            }
        });
        return rez;
    }
}




