package Service;


import Models.Client.Client;
import Repository.RepoDbClienti;

public class ServiceClienti {

    RepoDbClienti repoDbClienti;

    public ServiceClienti(RepoDbClienti repoDbClienti) {
        this.repoDbClienti = repoDbClienti;
    }

    public Client findOneClient(int id) {
        return repoDbClienti.findOne(id);
    }
    public Iterable<Client> findAllClienti() {
        return repoDbClienti.findAll();
    }


    void addClient(Client c) {
        repoDbClienti.save(c);
    }
}
