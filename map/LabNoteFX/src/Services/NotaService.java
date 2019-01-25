package Services;

import Models.Nota;
import Models.Validators.ValidationException;
import Repos.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class NotaService {
    private Repository<Nota,String> repo;

    public NotaService(Repository<Nota, String> repo) {
        this.repo = repo;
    }

    public Optional<Nota> addNota(String idStudent, int idNota, double valoare, int saptPredat) throws ValidationException {
        Nota nt = new Nota(idStudent,idNota,valoare,saptPredat);
        return repo.save(nt);
    }

    public Optional<Nota> findNota(String id) {
        return repo.find(id);
    }

    public Optional<Nota> removeNota(String id) {
        return repo.delete(id);
    }

    public Optional<Nota> updateNota(String idStudent, int idNota, double valoare, int saptPredat) throws ValidationException {
        Nota nt = new Nota(idStudent,idNota,valoare,saptPredat);
        return repo.update(nt);
    }

    public List<Nota> getAllNote() {
        return StreamSupport.stream(repo.getAll().spliterator(),false).collect(Collectors.toList());
    }
}




