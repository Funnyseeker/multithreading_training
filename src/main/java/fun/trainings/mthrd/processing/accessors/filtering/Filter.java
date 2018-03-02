package fun.trainings.mthrd.processing.accessors.filtering;

import java.util.function.Predicate;

public interface Filter<T> {
    Predicate<T> getPredicate();
}
