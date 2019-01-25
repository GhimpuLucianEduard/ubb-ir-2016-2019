package sample.Repo;

public interface Repository<E> {

    E save(E entity);
    Iterable<E> getAll();
}