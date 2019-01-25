package sample.Service;

import ObserverDP.ListEvent;
import ObserverDP.ListEventType;
import ObserverDP.Observable;
import ObserverDP.Observer;
import sample.Repo.FileRepositoryAngajat;
import sample.Repo.FileRepositoryMesaje;
import sample.domain.Angajat;
import sample.domain.Mesaj;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceAngajat  implements Observable<Angajat>{
    FileRepositoryAngajat repo;
    FileRepositoryMesaje repo2;
    ArrayList<Observer<Angajat>> obs = new ArrayList<>();

    public ServiceAngajat(FileRepositoryAngajat repo, FileRepositoryMesaje repo2) {
        this.repo = repo;
        this.repo2 = repo2;
    }

    public List<Angajat> getAll() {
        return StreamSupport.stream(repo.getAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Angajat> getAllActivi() {
        List<Angajat> rez = new ArrayList<>();
        getAll().forEach(x->{
            if(x.isStare())
            {
                rez.add(x);
            }
        });
        return rez;
    }

    public void retrageUnangajat(Angajat a) {
        repo.update(a);
        this.notifyObservers(new ListEvent<Angajat>(ListEventType.RETRAJE) {
            @Override
            public Iterable<Angajat> getList() {
                return getAllActivi();
            }

            @Override
            public Angajat getElement() {
                return null;
            }
        });
    }

    @Override
    public void addObserver(Observer<Angajat> o) {
        obs.add(o);
    }

    @Override
    public void removeObserver(Observer<Angajat> o) {
        obs.remove(o);
    }

    @Override
    public void notifyObservers(ListEvent<Angajat> event) {
        obs.forEach(o->o.notifyEvent(event));
    }



    public List<Mesaj> getAllMesaje() {
        return StreamSupport.stream(repo2.getAll().spliterator(), false).collect(Collectors.toList());
    }

    public Mesaj addMesaj(Mesaj m) {
        Mesaj aux = repo2.save(m);
        notifyObservers(createEvent(ListEventType.MESAJNOU,null,null));
        return aux;
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

    public boolean sefulPoate() {
        if(getAllActivi().size()==1){
            return true;
        }
        else
            return false;
    }


}
