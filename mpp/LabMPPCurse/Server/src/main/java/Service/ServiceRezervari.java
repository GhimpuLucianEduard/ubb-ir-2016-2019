package Service;

import Models.Rezervare;
import Repository.RepoDbRezervari;


import java.util.ArrayList;

public class ServiceRezervari {
    public ServiceRezervari(RepoDbRezervari repoDbRezervari) {
        this.repoDbRezervari = repoDbRezervari;
    }

    private RepoDbRezervari repoDbRezervari;
//    private ArrayList<Observer<Rezervare>> obs = new ArrayList<>();
//
//    @Override
//    public void addObserver(Observer<Rezervare> o) {
//        obs.add(o);
//    }
//
//    @Override
//    public void removeObserver(Observer<Rezervare> o) {
//        obs.remove(o);
//    }
//
//    @Override
//    public void notifyObservers(ListEvent<Rezervare> event) {
//        obs.forEach(o->o.notifyEvent(event));
//    }

    public Iterable<Rezervare> findAllRezervari() {
        return repoDbRezervari.findAll();
    }

    public void addRezervare(Rezervare rez) {
        repoDbRezervari.save(rez);
    }


}
