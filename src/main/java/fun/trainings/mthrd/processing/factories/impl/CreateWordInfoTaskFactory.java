package fun.trainings.mthrd.processing.factories.impl;

import fun.trainings.mthrd.model.impl.WordInfo;
import fun.trainings.mthrd.processing.accessors.SearcherAndAccessor;
import fun.trainings.mthrd.processing.impl.CreateWordInfoTask;

public class CreateWordInfoTaskFactory extends AbstractWordInfoTaskFactory<CreateWordInfoTask> {

    CreateWordInfoTaskFactory(SearcherAndAccessor<WordInfo> accessor) {
        super(accessor);
    }

    @Override
    protected CreateWordInfoTask createTask(String input) {
        return new CreateWordInfoTask(input, infoListAccessor);
    }
}
