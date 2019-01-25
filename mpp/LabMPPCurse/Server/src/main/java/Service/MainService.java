package Service;

import Models.Client.Client;
import Models.Cursa.Cursa;
import Models.Loc;
import Models.Rezervare;
import Models.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MainService {
    private ServicePersonal servicePersonal;
    private ServiceCurse serviceCurse;
    private ServiceRezervari serviceRezervari;
    private ServiceClienti serviceClienti;

    public ServiceRezervari getServiceRezervari() {
        return serviceRezervari;
    }

    public ServiceClienti getServiceClienti() {
        return serviceClienti;
    }

    public ServiceDestinatii getServiceDestinatii() {
        return serviceDestinatii;
    }

    private ServiceDestinatii serviceDestinatii;
    public ServicePersonal getServicePersonal() {
        return servicePersonal;
    }

    public ServiceCurse getServiceCurse() {
        return serviceCurse;
    }

    public MainService(ServicePersonal servicePersonal, ServiceCurse serviceCurse, ServiceRezervari serviceRezervari, ServiceClienti serviceClienti, ServiceDestinatii serviceDestinatii) {
        this.servicePersonal = servicePersonal;
        this.serviceCurse = serviceCurse;

        this.serviceRezervari = serviceRezervari;
        this.serviceClienti = serviceClienti;
        this.serviceDestinatii = serviceDestinatii;
    }

    public Iterable<Rezervare> findAllRezervariByIdCursa(int idCursa) {
        List<Rezervare> rez = new ArrayList<>();
        serviceRezervari.findAllRezervari().forEach(r->{
            if(r.getIdCursa()==idCursa) {
                rez.add(r);
            }
        });
        return rez;

    }


    public List<Loc> getLocuri(int idCursa) {

        List<Loc> locuri = new ArrayList<>();
        for (int i = 1; i <= 18 ; i++) {
            locuri.add(new Loc(i,"-","-"));
        }

        List<Rezervare> rezervari = (List<Rezervare>) findAllRezervariByIdCursa(idCursa);

        final int[] curent = {0};

        rezervari.forEach(r->{
            int aux = r.getNrLocuri();
            String numeClient = serviceClienti.findOneClient(r.getIdClient()).getNume();
            String prenumeClient = serviceClienti.findOneClient(r.getIdClient()).getPrenume();
            while(aux>0) {
                locuri.get(curent[0]).setNrCurent(curent[0] +1);
                locuri.get(curent[0]).setNumeClient(numeClient);
                locuri.get(curent[0]).setPrenumeClient(prenumeClient);
                curent[0]++;
                aux--;
            }
        });

        return locuri;

    }

    public void addRezervare(int idCursa, String nume, String prenume, int locuri) throws ServiceException {

        int locuriLibere=0;
        final int[] idClient = {0};
        Cursa aux = null;
        for (Cursa c : serviceCurse.getAllCurse() ) {

            if (c.getId()==idCursa) {
                locuriLibere=c.getNrLocuriDisponibile();
                aux=c;
            }
        }

        serviceClienti.findAllClienti().forEach(c->{
            if(c.getNume().compareTo(nume)==0&&c.getPrenume().compareTo(prenume)==0){
                idClient[0] =c.getId();
            }
        });

        final int[] maxIdClient = {0};
        serviceClienti.findAllClienti().forEach(r->{
            if(r.getId()> maxIdClient[0]){
                maxIdClient[0] =r.getId()+1;
            }
        });

        if(idClient[0]==0) {
            serviceClienti.addClient(new Client(maxIdClient[0]+1,nume,prenume));
            idClient[0]=maxIdClient[0]+1;
        }


        final int[] maxId = {0};
        serviceRezervari.findAllRezervari().forEach(r->{
            if(r.getId()> maxId[0]){
                maxId[0] =r.getId()+1;
            }
        });



        if (locuriLibere<locuri) {
            throw new ServiceException("Locuri insuficiente! Locuri disponibile: "+locuriLibere);
        } else
        {
            serviceRezervari.addRezervare(new Rezervare(maxId[0]+1,idClient[0],idCursa,locuri));
            serviceCurse.updateCursa(idCursa,new Cursa(idCursa,aux.getIdDestinatie(),aux.getData(),aux.getLocatiePlecare(),aux.getNrLocuriDisponibile()-locuri));

        }
    }




}
