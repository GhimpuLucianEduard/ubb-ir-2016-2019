package Service;
public class Trio<T,K,E> {
    private T first;
    private K second;
    private E third;

    public E getThird() {
        return third;
    }

    public void setThird(E third) {
        this.third = third;
    }

    public Trio(T first, K second, E third) {
        this.first = first;
        this.second = second;
        this.third = third;

    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(K second) {
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }

    public void setBoth(T first, K second, E third) {
        setFirst(first);
        setSecond(second);
        setThird(third);
    }
}
