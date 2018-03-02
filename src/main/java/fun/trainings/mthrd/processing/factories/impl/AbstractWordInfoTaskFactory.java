package fun.trainings.mthrd.processing.factories.impl;

import fun.trainings.mthrd.exceptions.InvalidNumberOfParams;
import fun.trainings.mthrd.model.impl.WordInfo;
import fun.trainings.mthrd.processing.accessors.SearcherAndAccessor;
import fun.trainings.mthrd.processing.Task;
import fun.trainings.mthrd.processing.factories.TaskFactory;

public abstract class AbstractWordInfoTaskFactory<T extends Task> implements TaskFactory<T> {

    SearcherAndAccessor<WordInfo> infoListAccessor;

    AbstractWordInfoTaskFactory(SearcherAndAccessor<WordInfo> accessor) {
        this.infoListAccessor = accessor;
    }

    @Override
    public T createNew(Object... params) throws InvalidNumberOfParams {
        String input = null;
        if (params != null) {
            input = (String) params[0];
        }else{
            throw new InvalidNumberOfParams(this.getClass().getName(), "createNew(...)");
        }
        return createTask(input);
    }

    protected abstract T createTask(String input);
}
