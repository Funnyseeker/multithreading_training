package fun.trainings.mthrd.processing;

public interface Accessor<T> {
    T next();

    void add(T object);
}
