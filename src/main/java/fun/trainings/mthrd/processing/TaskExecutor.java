package fun.trainings.mthrd.processing;

public class TaskExecutor<T extends Task> implements Runnable {

    Accessor<T> taskAccessor;

    TaskExecutor(Accessor taskAccessor) {
        this.taskAccessor = taskAccessor;
    }

    @Override
    public void run() {
        taskAccessor.next().execute();
    }
}
