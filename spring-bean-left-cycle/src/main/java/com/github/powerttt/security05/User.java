package com.github.powerttt.security05;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author tongning
 * @Date 2019/10/10 0010
 * function:<
 * <p>
 * >
 */
public class User implements InitializingBean, DisposableBean {

    private String name;
    public User() {
        System.out.println("调用Bean的函数(constructor)");
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        System.out.println("调用Bean的函数(setName/setAttribute)");
        this.name = name;
    }

    /**
     * 构造后
     */
    @PostConstruct
    public void postConstruct(){
        System.out.println("构造后");
    }

    /**
     * MainConfig中的@Bean 的 initMethod
     */
    public void initMethod(){
        System.out.println("调用Bean的函数（initMethod）");
    }
    /**
     * 属性设置后
     * Initializing接口方法中的afterPropertiesSet
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用Bean的函数(afterPropertiesSet)  属性设置后");
    }

    /**
     * 销毁前
     */
    @PreDestroy
    public void preDestroy(){
        System.out.println("销毁前 some thing");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁  some thing");

    }
    /**
     * MainConfig中的@Bean 的 initMethod
     */
    public void destroyMethod(){
        System.out.println("调用Bean的函数(destroyMethod)");
    }

}
