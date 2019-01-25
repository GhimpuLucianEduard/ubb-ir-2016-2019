package Repository;

import Utils.HasID;

import java.util.Map;
import java.util.TreeMap;

public class AbstractRepository <E extends HasID<ID>, ID> implements Repository<E, ID>
{

    private Map<ID,E> entities = new TreeMap<>();
    private Validator<E> vali;

    public AbstractRepository(Validator<E> vali)
    {
        this.vali = vali;
    }

    @Override
    public long size()
    {
        return entities.size();
    }

    @Override
    public E save(E entity) throws ValidationException
    {
        vali.validate(entity);
        if(entities.containsKey(entity.getId()))
        {
            return entities.get(entity.getId());
        }
        entities.put(entity.getId(),entity);
        return null;
    }

    @Override
    public E delete(ID id)
    {
        return entities.remove(id);
    }

    @Override
    public E findOne(ID id)
    {
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll()
    {
        return entities.values();
    }
}