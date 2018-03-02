package fun.trainings.mthrd.spring;

import fun.trainings.mthrd.annotations.Log;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

public class LoggingBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Class clazz = bean.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if(field.getAnnotation(Log.class) != null){
                try {
                    field.setAccessible(true);
                    field.set(bean, LoggerFactory.getLogger(clazz));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        return bean;
    }
}
