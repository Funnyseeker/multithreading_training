package fun.trainings.mthrd;

import fun.trainings.mthrd.processing.ExecutationState;
import fun.trainings.mthrd.processing.QueueManager;
import fun.trainings.mthrd.processing.TaskExecutor;
import fun.trainings.mthrd.processing.factories.impl.CreateWordInfoTaskFactory;
import fun.trainings.mthrd.processing.factories.impl.SetAdditionalInfoTaskFactory;
import fun.trainings.mthrd.processing.impl.ParseFileTask;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DictUtilsLauncher {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/beans.xml");
        if (args != null && args[0].equals("init")) {
            for (int i = 0; i < 10; i++) {
                init(ctx);
            }
        }
    }

    public static void init(ClassPathXmlApplicationContext ctx) {
        QueueManager queueManager = ctx.getBean("queueManager", QueueManager.class);

        QueueManager.TaskQueueAccessor taskQueueAccessor
                = ctx.getBean("taskQueueAccessor", QueueManager.TaskQueueAccessor.class);
        QueueManager.CompleteListAccessor completeListAccessor
                = ctx.getBean("completeListAccessor", QueueManager.CompleteListAccessor.class);

        ParseFileTask parseMaindDictTask
                = ctx.getBean("parseFileTask", ParseFileTask.class);
        parseMaindDictTask.setFileName("maindict.txt");
        parseMaindDictTask.setSubTaskFactory(ctx.getBean("createWordInfoTaskFactory",
                                                         CreateWordInfoTaskFactory.class));
        parseMaindDictTask.setTaskQueryAccessor(taskQueueAccessor);
        ParseFileTask parseAdditionalDictTask
                = ctx.getBean("parseFileTask", ParseFileTask.class);
        parseAdditionalDictTask.setFileName("additional.txt");
        parseAdditionalDictTask.setSubTaskFactory(ctx.getBean("setAdditioanlInfoTaskFactory",
                                                              SetAdditionalInfoTaskFactory.class));
        parseAdditionalDictTask.setTaskQueryAccessor(taskQueueAccessor);
        taskQueueAccessor.add(parseMaindDictTask);
        taskQueueAccessor.add(parseAdditionalDictTask);


        TaskExecutor taskExecutor1 = new TaskExecutor(taskQueueAccessor, completeListAccessor);
        TaskExecutor taskExecutor2 = new TaskExecutor(taskQueueAccessor, completeListAccessor);
        TaskExecutor taskExecutor3 = new TaskExecutor(taskQueueAccessor, completeListAccessor);
        TaskExecutor taskExecutor4 = new TaskExecutor(taskQueueAccessor, completeListAccessor);
        TaskExecutor taskExecutor5 = new TaskExecutor(taskQueueAccessor, completeListAccessor);

        queueManager.add(taskExecutor1);
        queueManager.add(taskExecutor2);
        queueManager.add(taskExecutor3);
        queueManager.add(taskExecutor4);
        queueManager.add(taskExecutor5);

        queueManager.waitAll();

        System.out.println(String.format("Completed tasks: ", queueManager.getCompleteList().stream()
                .filter(task -> task.getState().equals(ExecutationState.COMPLETE)).count()));
        System.out.println(String.format("Failed tasks: ", queueManager.getCompleteList().stream()
                .filter(task -> task.getState().equals(ExecutationState.FAIL)).count()));
    }
}
