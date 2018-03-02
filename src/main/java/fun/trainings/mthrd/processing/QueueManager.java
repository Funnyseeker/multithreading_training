package fun.trainings.mthrd.processing;

import fun.trainings.mthrd.annotations.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueManager {

    private final static int MAX_EXECUTORS = 5;
    private TaskExecutor[] THREADS = new TaskExecutor[MAX_EXECUTORS];
    private Queue<Task> tasksQueue = new ConcurrentLinkedQueue<>();
    private List<Task> completeList = new ArrayList<>();
    @Autowired
    @Log
    private Logger logger;

    public List<Task> getCompleteList() {
        return completeList;
    }

    public Queue<Task> getTasksQueue() {
        return tasksQueue;
    }

    /**
     * Добавляет TaskExecutor в пул выполняемых потоков, работающих с данной очередью.
     *
     * @param executor который должен быть добавлен в пул
     *
     * @return индекс в пуле.(если -1, то нет свободного места в пуле)
     */
    public int add(TaskExecutor executor) {
        for (int i = 0; i < MAX_EXECUTORS; i++) {
            if (THREADS[i] == null || !THREADS[i].isAlive()) {
                THREADS[i] = executor;
                THREADS[i].start();
                logger.info(String.format("TaskExecutor number {%d} added", i));
                return i;
            }
        }
        logger.info(String.format("TaskExecutor not added. Threads pool is full on current moment."));
        return -1;
    }

    public void waitAll() {
        logger.info("Waiting current executors.");
        boolean hasAlived = true;
        while (hasAlived) {
            if (completeList.size() != 0 && completeList.size() % 10000 == 0) {
                synchronized (completeList) {
                    logger.info(String.format("Tasks completed: %d", completeList.size()));
                }
                synchronized (tasksQueue) {
                    logger.info(String.format("Tasks in queue: %d", tasksQueue.size()));
                }
            }
            hasAlived = false;
            for (int i = 0; i < MAX_EXECUTORS; i++) {
                if (THREADS[i] != null && THREADS[i].isAlive()) {
                    hasAlived = true;
                    Thread.yield();
                    break;
                }
            }
        }
    }

    public void interruptAll() {
        for (int i = 0; i < MAX_EXECUTORS; i++) {
            if (THREADS[i] != null && THREADS[i].isAlive()) {
                THREADS[i].interrupt();
            }
        }
        waitAll();
    }
}
