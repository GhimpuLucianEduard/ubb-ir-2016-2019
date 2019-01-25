package Service;

import Models.Destinatie.Destinatie;
import Repository.RepoDbDestinatie;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceDestinatii {
    RepoDbDestinatie repoDbDestinatie;

    public ServiceDestinatii(RepoDbDestinatie repoDbDestinatie) {
        this.repoDbDestinatie = repoDbDestinatie;
    }

    public List<Destinatie> findAllDestinatii() {

        return StreamSupport.stream(repoDbDestinatie.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
