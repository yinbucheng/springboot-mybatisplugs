package cn.bucheng.springboot.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author ：yinchong
 * @create ：2019/6/26 15:40
 * @description：Application注册类
 * @modified By：
 * @version:
 */
public class BeanFactoryUtils implements ApplicationContextAware {
    private static  DefaultListableBeanFactory beanFactory;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("----------->invoke BeanFactoryUtils");
      beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }

    public static DefaultListableBeanFactory getBeanFactory(){
        return beanFactory;
    }
}
