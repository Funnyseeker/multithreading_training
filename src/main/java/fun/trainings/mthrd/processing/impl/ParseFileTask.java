package fun.trainings.mthrd.processing.impl;

import fun.trainings.mthrd.annotations.Log;
import fun.trainings.mthrd.exceptions.InvalidNumberOfParams;
import fun.trainings.mthrd.processing.ExecutationState;
import fun.trainings.mthrd.processing.Task;
import fun.trainings.mthrd.processing.accessors.Accessor;
import fun.trainings.mthrd.processing.factories.TaskFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ParseFileTask extends AbstractTask {
    @Autowired
    @Log
    private Logger logger;
    private String fileName;
    private TaskFactory subTaskFactory;
    private Accessor taskQueryAccessor;

    public ParseFileTask() {
    }

    public <T extends Task> ParseFileTask(String fileName, TaskFactory subTaskFactory,
                                          Accessor<T> taskQueryAccessor) {
        this.fileName = fileName;
        this.subTaskFactory = subTaskFactory;
        this.taskQueryAccessor = taskQueryAccessor;
    }

    @Override
    public ExecutationState execute() {
        try {
            URL url = getClass().getClassLoader().getResource(fileName);
            FileInputStream stream = new FileInputStream(url.getFile());
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String nextLine = null;
            while ((nextLine = reader.readLine()) != null) {
                if (nextLine.trim().equals("")) {
                    continue;
                }
                taskQueryAccessor.add(subTaskFactory.createNew(nextLine));
            }
            logger.info(String.format("Parsing complete for file %s", fileName));
            return returnState(ExecutationState.COMPLETE);
        } catch (IOException | InvalidNumberOfParams exception) {
            exception.printStackTrace();
            return returnState(ExecutationState.FAIL);
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSubTaskFactory(TaskFactory subTaskFactory) {
        this.subTaskFactory = subTaskFactory;
    }

    public void setTaskQueryAccessor(Accessor taskQueryAccessor) {
        this.taskQueryAccessor = taskQueryAccessor;
    }
}
