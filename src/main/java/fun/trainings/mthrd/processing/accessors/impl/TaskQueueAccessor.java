package fun.trainings.mthrd.processing.accessors.impl;

import fun.trainings.mthrd.processing.Task;
import fun.trainings.mthrd.processing.accessors.Accessor;

import java.util.Queue;

public class TaskQueueAccessor implements Accessor<Task> {

    private Queue<Task> tasksQueue;

    @Override
    public Task get() {
        synchronized (tasksQueue) {
            Task task;
            if ((task = tasksQueue.poll()) != null) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void add(Task object) {
        synchronized (tasksQueue) {
            tasksQueue.add(object);
        }
    }

    public void setTasksQueue(Queue<Task> tasksQueue) {
        this.tasksQueue = tasksQueue;
    }
}