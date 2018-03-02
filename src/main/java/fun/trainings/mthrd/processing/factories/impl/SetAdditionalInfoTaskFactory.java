package fun.trainings.mthrd.processing.factories.impl;

import fun.trainings.mthrd.model.impl.WordInfo;
import fun.trainings.mthrd.processing.accessors.SearcherAndAccessor;
import fun.trainings.mthrd.processing.impl.SetAdditionalInfoTask;

public class SetAdditionalInfoTaskFactory extends AbstractWordInfoTaskFactory<SetAdditionalInfoTask> {

    SetAdditionalInfoTaskFactory(SearcherAndAccessor<WordInfo> accessor) {
        super(accessor);
    }

    @Override
    protected SetAdditionalInfoTask createTask(String input) {
        return new SetAdditionalInfoTask(input, infoListAccessor);
    }
}
