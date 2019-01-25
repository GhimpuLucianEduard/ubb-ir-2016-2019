package Repo;

public interface Repository<E, ID> {
    long size();

    E save(E entity);

    E delete(ID id);

    E find(ID id);

    Iterable<E> getAll();
}