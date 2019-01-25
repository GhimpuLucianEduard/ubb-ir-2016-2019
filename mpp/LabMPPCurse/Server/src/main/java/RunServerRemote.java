import Models.Cursa.Cursa;
import Models.Destinatie.Destinatie;
import Models.Loc;
import Models.Personal.Personal;
import Models.Rezervare;
import Models.ServiceException;
import Service.MainService;
import services.IObserver;
import services.IServer;
import services.ServerException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunServerRemote implements IServer {

    private Map<Integer, IObserver> loggedClients;
    private MainService service;
    private final int defaultThreadsNo=5;

    public RunServerRemote(MainService service) {

        this.service = service;
        loggedClients=new ConcurrentHashMap<>();;
    }


    public synchronized void login(Personal user, IObserver client) throws ServerException {
        boolean gasit = false;
        Personal userG = null;
        try {
            gasit = service.getServicePersonal().loginHandler(user.getUserName(), user.getPass());
            userG = service.getServicePersonal().findByUsername(user.getUserName());
        } catch (ServiceException e) {
            throw new ServerException("Nu is bune credentialele sau ceva");
        }
        if (gasit == true){
            if(loggedClients.get(userG.getId())!=null)
                throw new ServerException("User already logged in.");
            loggedClients.put(userG.getId(), client);
        }else
            throw new ServerException("Authentication failed.");
    }

    @Override
    public Destinatie[] getDestinatii() throws ServerException {
        List<Destinatie> desti = service.getServiceDestinatii().findAllDestinatii();
        Destinatie[] destiTOSend = new Destinatie[desti.size()];
        final int[] i = {0};
        desti.forEach(x->{
            destiTOSend[i[0]] = x;
            i[0]++;
        });
        return destiTOSend;
    }

    @Override
    public Cursa[] getCurse() throws ServerException {
        List<Cursa> rez = service.getServiceCurse().getAllCurse();
        Cursa[] destiTOSend = new Cursa[rez.size()];
        final int[] i = {0};
        rez.forEach(x->{
            destiTOSend[i[0]] = x;
            i[0]++;
        });
        return destiTOSend;
    }

    @Override
    public Loc[] getLocuri(Cursa c) throws ServerException {
        List<Loc> rez = service.getLocuri(c.getId());
        Loc[] destiTOSend = new Loc[rez.size()];
        final int[] i = {0};
        rez.forEach(x->{
            destiTOSend[i[0]] = x;
            i[0]++;
        });
        return destiTOSend;
    }

    @Override
    public synchronized void addRezervare(int idCursa, String nume, String prenume, int nrLocuri) throws ServerException {
        try {
            service.addRezervare(idCursa,nume,prenume,nrLocuri);
            notifyNewRezervare();
        } catch (ServiceException e) {
            throw new ServerException(e.getMessage());
        }

    }

    private void notifyNewRezervare() {
        List<Cursa> curse = service.getServiceCurse().getAllCurse();
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        loggedClients.keySet().forEach(x->{
            IObserver obs = loggedClients.get(x);
            executor.execute(()->{
                try {
                    Cursa[] curserez = new Cursa[curse.size()];
                    final int[] i = {0};
                    curse.forEach(y->{
                        curserez[i[0]]=y;
                        i[0]++;
                    });
                    obs.newRezervare(curserez);
                } catch (ServerException e) {
                    e.printStackTrace();
                }
            });
        });
        executor.shutdown();
    }




}
