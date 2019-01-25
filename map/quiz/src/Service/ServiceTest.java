package Service;

import Models.Intrebare;
import Observer.Observable;
import Observer.Observer;
import Observer.ListEvent;
import Observer.ListEventType;

import java.util.ArrayList;
import java.util.List;

public class ServiceTest implements Observable<Intrebare> {
    public List<Intrebare> testCurent = new ArrayList<>();
    ArrayList<Observer<Intrebare>> observers = new ArrayList<>();

    public void addToTest(Intrebare e) {
        testCurent.add(e);
        ListEvent<Intrebare> ev = createEvent(ListEventType.ADD,e,getTest());
        notifyObservers(ev);
    }

    public List<Intrebare> getTest() {
        return testCurent;
    }

    @Override
    public void addObserver(Observer<Intrebare> o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(ListEvent<Intrebare> event) {
        observers.forEach(x->x.notifyEvent(event));
    }

    @Override
    public void removeObserver(Observer<Intrebare> o) {
        observers.remove(o);
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
