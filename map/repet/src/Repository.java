public interface Repository<E, ID> {
    long size();

    E save(E entity);

    E update(E entity);

    E delete(ID id);

    E find(ID id);

    Iterable<E> getAll();
}