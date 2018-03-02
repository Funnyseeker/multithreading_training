package fun.trainings.mthrd.processing.accessors.impl;

import fun.trainings.mthrd.exceptions.MethdoNotSupportedException;
import fun.trainings.mthrd.processing.QueueManager;
import fun.trainings.mthrd.processing.Task;
import fun.trainings.mthrd.processing.accessors.Accessor;

import java.util.List;

public class CompleteListAccessor implements Accessor<Task> {

    private List<Task> completeList;

    @Override
    public Task get() throws MethdoNotSupportedException {
        throw new MethdoNotSupportedException(this.getClass().getName(), "get()");
    }

    @Override
    public synchronized void add(Task object) {
       completeList.add(object);
    }

    public void setCompleteList(List<Task> completeList) {
        this.completeList = completeList;
    }
}