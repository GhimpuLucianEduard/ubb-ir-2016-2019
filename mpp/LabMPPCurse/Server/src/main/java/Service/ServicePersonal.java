package Service;

import Models.Personal.Personal;
import Models.ServiceException;
import Repository.RepoDbPersonal;

import java.util.ArrayList;


public class ServicePersonal {

    private RepoDbPersonal repoDbPersonal;
    //private ArrayList<Observer<Personal>> obs = new ArrayList<>();

    public Personal findPersonal(int id) {
        return repoDbPersonal.findOne(id);
    }

    public ServicePersonal(RepoDbPersonal repoDbPersonal) {
        this.repoDbPersonal = repoDbPersonal;
    }



    public Personal findByUsername(String username)  throws ServiceException {
        final Personal[] aux = new Personal[1];
        repoDbPersonal.findAll().forEach(x->{
            if(x.getUserName().compareTo(username)==0) {
                aux[0] = x;
            }
        });
        if (aux[0]!=null){
            return aux[0];
        }
        else {
            throw new ServiceException("Not such user in database!");
        }
    }


    public boolean loginHandler(String username, String password) throws ServiceException {

        final boolean[] gasit = {false};

        repoDbPersonal.findAll().forEach(x->{
            if((x.getUserName().compareTo(username)==0)&&(x.getPass().compareTo(password)==0))
            {
                gasit[0] =true;
            }
        });

        return gasit[0];
    }

//    @Override
//    public void addObserver(Observer<Personal> o) {
//        obs.add(o);
//    }
//
//    @Override
//    public void removeObserver(Observer<Personal> o) {
//        obs.remove(o);
//    }
//
//    @Override
//    public void notifyObservers(ListEvent<Personal> event) {
//        obs.forEach(o->o.notifyEvent(event));
//    }
}
