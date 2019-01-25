package Repository;

public interface Repository<E, ID> {
    E save(E entity);
    Iterable<E> getAll();
}