package fun.trainings.mthrd.processing;

import fun.trainings.mthrd.annotations.Log;
import fun.trainings.mthrd.exceptions.MethdoNotSupportedException;
import fun.trainings.mthrd.processing.accessors.Accessor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskExecutor extends Thread {

    private Accessor<Task> taskAccessor;
    private Accessor<Task> completeListAccesor;
    @Autowired
    @Log
    private Logger logger;

    public TaskExecutor(Accessor<Task> taskAccessor, Accessor<Task> completeListAccesor) {
        this.taskAccessor = taskAccessor;
        this.completeListAccesor = completeListAccesor;
    }

    @Override
    public void run() {
        try {
            Task task;
            while (!Thread.interrupted()) {
                if ((task = taskAccessor.get()) != null) {
                    ExecutationState state = task.execute();
                    if (state == ExecutationState.RETRY) {
                        taskAccessor.add(task);
                    } else {
                        completeListAccesor.add(task);
                    }
                } else {
                    Thread.yield();
                    break;
                }
            }
        } catch (MethdoNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
