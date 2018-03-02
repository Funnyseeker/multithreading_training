package fun.trainings.mthrd.processing.accessors;

import fun.trainings.mthrd.exceptions.MethdoNotSupportedException;
import fun.trainings.mthrd.processing.accessors.filtering.Filter;

import java.util.List;

public interface Searcher<T> {

    List<T> find(Filter<T> filter) throws MethdoNotSupportedException;

    T find(String key) throws MethdoNotSupportedException;
}
