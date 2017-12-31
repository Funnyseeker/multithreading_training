package fun.trainings.mthrd.processing.factories.impl;

import fun.trainings.mthrd.processing.Accessor;
import fun.trainings.mthrd.processing.factories.TaskFactory;
import fun.trainings.mthrd.processing.impl.CreateWordInfoTask;

public class CreateWordInfoTaskFactory implements TaskFactory<CreateWordInfoTask> {

    Accessor infoListAccessor;

    CreateWordInfoTaskFactory(Accessor infoListAccessor){
        this.infoListAccessor = infoListAccessor;
    }

    @Override
    public CreateWordInfoTask createNew(Object... params) {
        String input = (String)params[0];
        return new CreateWordInfoTask(input, infoListAccessor);
    }
}
