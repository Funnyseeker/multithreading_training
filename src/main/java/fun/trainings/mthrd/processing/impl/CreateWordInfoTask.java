package fun.trainings.mthrd.processing.impl;

import fun.trainings.mthrd.model.impl.WordInfo;
import fun.trainings.mthrd.processing.Accessor;
import fun.trainings.mthrd.processing.Task;
import fun.trainings.mthrd.processing.TaskType;

import java.util.StringTokenizer;

public class CreateWordInfoTask implements Task {
    String input;

    Accessor<WordInfo> accessor;

    public CreateWordInfoTask(String input, Accessor<WordInfo> accessor) {
        this.input = input;
        this.accessor = accessor;
    }


    @Override
    public void execute() {
        StringTokenizer tokenizer = new StringTokenizer(input, "|");
        WordInfo info = new WordInfo();
        info.setWord(tokenizer.nextToken());
        info.setMorphInfo(tokenizer.nextToken());
        info.setFrequency(Long.parseLong(tokenizer.nextToken()));
        accessor.add(info);
    }

    @Override
    public TaskType getType() {
        return TaskType.CreateWordInfo;
    }
}
