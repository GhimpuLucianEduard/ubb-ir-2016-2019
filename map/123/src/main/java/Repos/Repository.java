package Repos;

import Models.Validators.ValidationException;

import java.util.Optional;


/**
 * @param <E> tipul entitatii care osa contina
 * @param <ID> tipul idiului entitatii
 * Interfata care ofera operatii CRUD
 */
public interface Repository<E, ID> {

    long size();
    Optional<E> save(E entity) throws ValidationException;
    Optional<E> delete(ID id);
    Optional<E> update(E entity) throws ValidationException;
    Optional<E> find(ID id);
    Iterable<E> getAll();
}
