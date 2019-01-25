package sample.ObserverDP;

public interface Observer<E> {
    void notifyEvent(ListEvent<E> e);
}
