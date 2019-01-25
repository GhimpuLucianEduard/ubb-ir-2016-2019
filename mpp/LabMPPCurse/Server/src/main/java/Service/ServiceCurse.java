package Service;

import Models.Cursa.Cursa;
import Repository.RepoDBCurse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceCurse  {

    private RepoDBCurse repoDBCurse;
//    private ArrayList<Observer<Cursa>> obs = new ArrayList<>();
//
//    @Override
//    public void addObserver(Observer<Cursa> o) {
//        obs.add(o);
//    }
//
//    @Override
//    public void removeObserver(Observer<Cursa> o) {
//        obs.remove(o);
//    }
//
//    @Override
//    public void notifyObservers(ListEvent<Cursa> event) {
//        obs.forEach(x -> x.notifyEvent(event));
//    }

    public ServiceCurse(RepoDBCurse repoDBCurse) {
        this.repoDBCurse = repoDBCurse;
    }

    public List<Cursa> getAllCurse() {
        return StreamSupport.stream(repoDBCurse.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void addCursa(Cursa entity) {
        repoDBCurse.save(entity);
    }

    public Cursa findCursaByDetails(int idDestinatie, String data) {



        final Cursa[] aux = new Cursa[1];
        repoDBCurse.findAll().forEach(x -> {



            if (x.getIdDestinatie() == idDestinatie && data.compareTo(x.getData())==0 ) {
                aux[0] = x;
            }
        });
        return aux[0];
    }


    public void updateCursa(int id, Cursa c) {
        repoDBCurse.update(id, c);
//        notifyObservers(new ListEvent<Cursa>(ListEventType.UPDATE) {
//            @Override
//            public Iterable<Cursa> getList() {
//                return getAllCurse();
//            }
//
//            @Override
//            public Cursa getElement() {
//                return c;
//            }
//        });


    }
}


