package fun.trainings.mthrd.processing.factories;

import fun.trainings.mthrd.processing.Task;

public interface TaskFactory<T extends Task> {
    T createNew(Object... params);
}
