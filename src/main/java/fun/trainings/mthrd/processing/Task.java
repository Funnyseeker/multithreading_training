package fun.trainings.mthrd.processing;

public interface Task {
    int MAX_RETRIES = 3;

    ExecutationState execute();

    ExecutationState getState();
}
