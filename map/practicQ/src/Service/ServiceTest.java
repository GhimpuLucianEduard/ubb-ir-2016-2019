package Service;

import Models.Intrebare;
import Models.Raspuns;
import ObserverDP.ListEvent;
import ObserverDP.ListEventType;
import ObserverDP.Observable;
import ObserverDP.Observer;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ServiceTest implements Observable<Intrebare> {
    ArrayList<Observer<Intrebare>> obs = new ArrayList<>();
    private List<Intrebare> testCurent = new ArrayList<>();
    private double punctajMaxim;

    private Map<String,Raspuns> raspunsuriNecorectate = new TreeMap<>();

    public Map<String,Raspuns>  getRaspunsuriNecorectate() {
        return raspunsuriNecorectate;
    }

    public Raspuns addToRaspunsuriNecorectate(Raspuns i)  {


        if (raspunsuriNecorectate.containsKey(i.getNume()+i.getCodI())) {
            return raspunsuriNecorectate.get(i.getNume()+i.getCodI());
        }
        raspunsuriNecorectate.put(i.getNume()+i.getCodI(),i);
        return null;
    }


    private List<Raspuns> raspunsuriFinale = new ArrayList<>();

    public void getRaspunsuriFinale() {


        List<Raspuns> rez = raspunsuriNecorectate.values().stream().collect(Collectors.toList());
        rez.forEach(x->{

            List<Intrebare> rez2 = testCurent.stream().filter(y->y.getId()==x.getCodI()).collect(Collectors.toList());
            String raspunsCorect = rez2.get(0).getCorect();
            double pnc = rez2.get(0).getPunctaj();
            if(x.getRaspuns().compareTo(raspunsCorect)==0) {
                Raspuns fin = new Raspuns(x.getNume(),x.getCodI(),x.getRaspuns(),pnc);
                raspunsuriFinale.add(fin);
            }
            else {
                Raspuns fin = new Raspuns(x.getNume(),x.getCodI(),0.0);
                raspunsuriFinale.add(fin);
            }

        });


    }


    public void getRezultat() {

        TreeMap<String,Double> rez = new TreeMap<>();
        raspunsuriFinale.forEach(x->{
            if (!rez.containsKey(x.getNume())) {
                rez.put(x.getNume(),x.getPunctaj());
            }
            else {
                double aux = rez.get(x.getNume());
                rez.put(x.getNume(),aux+x.getPunctaj());
            }
        });
        obs.forEach(o->o.notifyEvent(rez));
    }




    public double getPunctajMaxim() {
        return punctajMaxim;
    }

    public void setPunctajMaxim(double punctajMaxim) {
        this.punctajMaxim = punctajMaxim;
    }

    public List<Intrebare> getTest() {
        return testCurent;
    }

    public void addToTest(Intrebare i)  {

        testCurent.add(i);
        ListEvent<Intrebare> ev = createEvent(ListEventType.ADD,i,getTest());
        notifyObservers(ev);

    }




//    public Intrebare deleteIntrebare(String id) {
//
//        testCurent.remove(testCurent.get(id));
//        if(aux.isPresent()) {
//            ListEvent<Student> ev = createEvent(ListEventType.REMOVE,aux.get(),getAllStudents());
//            notifyObservers(ev);
//        }
//        return aux;
//    }

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
