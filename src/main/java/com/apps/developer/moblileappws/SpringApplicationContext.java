package com.apps.developer.moblileappws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static Object getBean(final String beanName) {
        return CONTEXT.getBean(beanName);
    }


}
