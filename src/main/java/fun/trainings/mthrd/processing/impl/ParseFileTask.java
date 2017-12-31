package fun.trainings.mthrd.processing.impl;

import fun.trainings.mthrd.processing.Accessor;
import fun.trainings.mthrd.processing.Task;
import fun.trainings.mthrd.processing.TaskType;
import fun.trainings.mthrd.processing.factories.TaskFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ParseFileTask implements Task {
    private String fileName;
    private TaskFactory factory;
    private Accessor<Task> taskQueryAccessor;

    ParseFileTask(String fileName, TaskFactory factory, Accessor<Task> taskQueryAccessor) {
        this.fileName = fileName;
        this.factory = factory;
        this.taskQueryAccessor = taskQueryAccessor;
    }

    @Override
    public void execute() {
        try {
            URL url = getClass().getClassLoader().getResource(fileName);
            FileInputStream stream = new FileInputStream(url.getFile());
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String nextLine = null;
            while ((nextLine = reader.readLine()) != null) {
                taskQueryAccessor.add(factory.createNew(nextLine));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TaskType getType() {
        return TaskType.ParseWordInfo;
    }
}
