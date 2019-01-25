package Observer;

public interface Observable {
    void addObserver(Observer o);
    void notifyAll(Object arg);
}
