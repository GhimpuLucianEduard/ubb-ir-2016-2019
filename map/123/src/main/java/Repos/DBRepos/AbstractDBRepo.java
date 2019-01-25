package Repos.DBRepos;

import Models.HasID;
import Models.Validators.ValidationException;
import Models.Validators.Validator;
import Repos.AbstractRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;


public abstract class AbstractDBRepo<E extends HasID<ID>,ID> extends AbstractRepository<E, ID> implements DBRepo<E,ID> {
    /**
     * @param vali validatorul specific obiectului E
     */
    public AbstractDBRepo(Validator<E> vali) {
        super(vali);
    }

    @Override
    public abstract void getData();

    @Override
    public long size() {
        return super.size();
    }

    @Override
    public Optional<E> save(E entity) throws ValidationException {
        return super.save(entity);
    }

    @Override
    public Optional<E> delete(ID id) {
        return super.delete(id);
    }

    @Override
    public Optional<E> update(E entity) throws ValidationException {
        return super.update(entity);
    }

    @Override
    public Optional<E> find(ID id) {
        return super.find(id);
    }

    @Override
    public Iterable<E> getAll() {
        return super.getAll();
    }


}
