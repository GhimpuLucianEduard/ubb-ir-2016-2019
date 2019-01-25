package Repos;

import Models.HasID;
import Models.Validators.ValidationException;
import Models.Validators.Validator;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @param <E> tipul entitatii care osa contina
 * @param <ID> tipul idiului entitatii
 * Clasa care ofera operatii CRUD
 */
public abstract class AbstractRepository<E extends HasID<ID>,ID> implements Repository<E, ID> {

    /**
     * Dictionar folosit pentru a pastra in memorie obiectele de tip E
     * cheia dictionarului o reprezinta id-ul ID al obiectelor
     * si valoare o va reprezenta obiectul in sine
     */
    private Map<ID,E> entities = new TreeMap();

    /**
     * Validatorul specific obectelor de tip E
     */
    private Validator<E> vali;

    /**
     * @param vali validatorul specific obiectului E
     */
    public AbstractRepository(Validator<E> vali) {
        this.vali = vali;
    }

    /**
     * @return numarul de obiecte din repo
     */
    @Override
    public long size() {
        return entities.size();
    }


    /**
     * @param entity obiectul care trebuie adaugat in repo
     * @return optional gol  daca entity a fost adaugat cu succes
     * @return un optional cu obiectul cu id-ul entitatii exista deja in repo
     * @throws ValidationException daca entity nu e valid
     */
    @Override
    public Optional<E> save(E entity) throws ValidationException {
        vali.validate(entity);
        if(entities.containsKey(entity.getId()))
        {
            return Optional.of(entities.get(entity.getId()));
        }
        entities.put(entity.getId(),entity);
        return Optional.empty();

    }

    /**
     * @param id id-ul obiectului care trebuie ster
     * @return optinal cu obectul sers sau optional gol
     * daca obiectul cu id-ul id nu se afla in repo
     */
    @Override
    public Optional<E> delete(ID id) {
        return Optional.ofNullable(entities.remove(id));
    }

    /**
     * @param entity obiectul cu noile atribute dar acelasi id
     * @return optional gol daca obiectul a fost updatat cu succes
     * sau optional cu obiectul nou, daca nu exista cheie cu id-ul respectiv
     * @throws ValidationException daca entitatea noua nu e valida
     */
    @Override
    public Optional<E> update(E entity) throws ValidationException {
        vali.validate(entity);
        if(entities.containsKey(entity.getId()))
        {
            entities.put(entity.getId(),entity);
            return Optional.empty();

        }
        return Optional.of(entity);

    }

    /**
     * @param id id-ul obiectului de gasit
     * @return optional cu obiectul gasit, sau optinal gol
     * daca nu exista obiect cu cheia id in repo
     */
    @Override
    public Optional<E> find(ID id) {
        return Optional.ofNullable(entities.get(id));
    }

    /**
     * @return un iterable cu obiectele din repo
     */
    @Override
    public Iterable<E> getAll() {
        return entities.values();
    }
}
