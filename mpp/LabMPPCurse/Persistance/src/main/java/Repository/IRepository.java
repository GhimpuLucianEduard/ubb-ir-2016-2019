package Repository;

import Models.RepositoryException;
import Models.ValidationException;

/**
 * Created by grigo on 11/14/16.
 */
public interface IRepository<ID, T> {
    int size();
    void save(T entity) throws RepositoryException, ValidationException;
    void delete(ID id);
    void update(ID id, T entity) throws ValidationException, RepositoryException;
    T findOne(ID id) throws RepositoryException;
    Iterable<T> findAll();
}