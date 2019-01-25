package Services;

import Models.Tema;
import Models.Validators.ValidationException;
import Repos.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TemaService {
    public int saptCurenta;
    private Repository<Tema, Integer> repo;

    public TemaService(Repository<Tema, Integer> repo) {
        this.repo = repo;
    }

    public Optional<Tema> addTema(int id, String info, int deadline) throws ValidationException {
        Tema tm = new Tema(id,info,deadline);
        return repo.save(tm);
    }

    public Optional<Tema> removeTema(int id) {
        return repo.delete(id);
    }

    public Optional<Tema> findTemaById(int id) {
        return repo.find(id);
    }

    public Optional<Tema> updateTema(int id, String info, int deadline) throws ValidationException {
        Tema tm = new Tema(id,info,deadline);
        return repo.update(tm);
    }

    public List<Tema> getAllTeme() {
        return StreamSupport.stream(repo.getAll().spliterator(),false).collect(Collectors.toList());
    }

    public Optional<Tema> prelungire(int id, int deadLineNou) throws ValidationException {
        Optional<Tema> tm = repo.find(id);

        if(deadLineNou > 14 || deadLineNou <= tm.get().getDeadline()) {
            throw new ValidationException("Data noua invalida!");
        }
        if(saptCurenta >= tm.get().getDeadline()) {
            throw new ValidationException("Prea tarziu pentru a modifica deadline-ul!");
        }
        tm.get().setDeadline(deadLineNou);
        return tm;
    }
}
