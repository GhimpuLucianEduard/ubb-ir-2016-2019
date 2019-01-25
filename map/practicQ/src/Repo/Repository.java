package Repo;

public interface Repository<E, ID> {

    E save(E entity);

    E delete(ID id);

    Iterable<E> getAll();
}