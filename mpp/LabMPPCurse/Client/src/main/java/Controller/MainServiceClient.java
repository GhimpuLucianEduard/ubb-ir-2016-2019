package Controller;

import Models.Cursa.Cursa;
import Models.Destinatie.Destinatie;
import Models.Loc;
import Models.Personal.Personal;
import Observer.Observable;
import Observer.Observer;
import javafx.scene.control.Alert;
import services.IObserver;
import services.IServer;
import services.ServerException;

import java.util.ArrayList;
import java.util.List;

public class MainServiceClient implements IObserver, Observable {

    private IServer server;
    private ArrayList<Observer> observers = new ArrayList<>();
    public MainServiceClient(IServer server) {
        this.server = server;
    }

    public boolean loginHandler(String text, String text1) {
        Personal pers = new Personal(text, text1);
        try {
            server.login(pers,this);
            return true;
        } catch (ServerException e) {
            return false;
        }

    }

    public List<Destinatie> findAllDestinatii() {
        try {
            Destinatie[] desti = server.getDestinatii();
            List<Destinatie> rez = new ArrayList<>();
            for (int i = 0; i < desti.length; i++) {
                rez.add(desti[i]);
            }
            return rez;
        } catch (ServerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getAllCurse() {
        try {
            Cursa[] c = server.getCurse();
            List<Cursa> rez = new ArrayList<>();
            for (int i = 0; i < c.length; i++) {
                rez.add(c[i]);
            }
            return rez;
        } catch (ServerException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object getLocuri(Cursa c) {
        try {
            Loc[] l = server.getLocuri(c);
            List<Loc> rez = new ArrayList<>();
            for (int i = 0; i < l.length; i++) {
                rez.add(l[i]);
            }
            return rez;
        } catch (ServerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addRezervare(Cursa idCursa, String text, String text1, int value) {
        try {
            server.addRezervare(idCursa.getId(), text,text1,value);

        } catch (ServerException e) {
            MessageAlert.showError(null,"NU AM ADAUGAT~@#@");
        }
    }




    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyAll(Object arg) {
        observers.forEach(x->{
            x.update(arg);
        });
    }

    @Override
    public void newRezervare(Cursa[] c) throws ServerException {
        List<Cursa> rez = new ArrayList<>();
        for (int i = 0; i < c.length; i++) {
            rez.add(c[i]);
        }
        notifyAll(rez);
    }
}
