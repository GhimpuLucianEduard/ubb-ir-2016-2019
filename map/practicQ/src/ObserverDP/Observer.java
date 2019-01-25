package ObserverDP;

import java.util.TreeMap;

public interface Observer<E> {
    void notifyEvent(ListEvent<E> e);
    void notifyEvent(TreeMap<String,Double> rez);
}
