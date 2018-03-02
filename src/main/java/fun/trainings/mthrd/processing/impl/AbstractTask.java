package fun.trainings.mthrd.processing.impl;

import fun.trainings.mthrd.processing.ExecutationState;
import fun.trainings.mthrd.processing.Task;

public abstract class AbstractTask implements Task {
    ExecutationState state;

    protected ExecutationState returnState(ExecutationState state) {
        this.state = state;
        return state;
    }

    @Override
    public ExecutationState getState() {
        return state;
    }
}
