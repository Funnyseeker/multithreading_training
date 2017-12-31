package fun.trainings.mthrd.processing;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskManager {
    private static int MAX_EXECUTORS = 5;
    private static Thread[] THREADS = new Thread[MAX_EXECUTORS];
    private static Queue<Task> tasksQueue = new ConcurrentLinkedQueue<>();

    /**
     * Добавляет TaskExecutor в пул.
     *
     * @param executor который должен быть добавлен в пул
     *
     * @return индекс в пуле.(если -1, то нет свободного места в пуле)
     */
    public int add(TaskExecutor executor) {
        for (int i = 0; i < MAX_EXECUTORS; i++) {
            if (THREADS[i] == null || !THREADS[i].isAlive()) {
                THREADS[i] = new Thread(executor);
                THREADS[i].start();
                return i;
            }
        }
        return -1;
    }

    public class SyncTaskQueueAccessor implements Accessor<Task> {
        TaskType taskType;

        SyncTaskQueueAccessor(TaskType taskType) {
            this.taskType = taskType;
        }

        @Override
        public Task next() {
            Task task = TaskManager.tasksQueue.poll();
            do {
                if (task.getType().equals(taskType)) {
                    return task;
                } else {
                    TaskManager.tasksQueue.add(task);
                }
            } while ((task = TaskManager.tasksQueue.poll()) != null);
            return null;
        }

        @Override
        public void add(Task object) {
            TaskManager.tasksQueue.add(object);
        }
    }
}
