package fun.trainings.mthrd.processing.accessors;

import fun.trainings.mthrd.exceptions.MethdoNotSupportedException;

public interface Accessor<T> {
    T get() throws MethdoNotSupportedException;

    void add(T object);
}
