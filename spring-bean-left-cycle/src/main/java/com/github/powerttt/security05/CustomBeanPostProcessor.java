package com.github.powerttt.security05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author tongning
 * @Date 2019/10/10 0010
 * function:<
 * <p>
 * >
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    /**
     * 将此BeanPostProcessor应用于给定的新bean实例<i>之前</ i>任何bean
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass()==User.class){
            System.out.println("调用postProcessBeforeInitialization...");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass()==User.class){
            System.out.println("调用postProcessAfterInitialization...");
        }
        return bean;
    }
}
