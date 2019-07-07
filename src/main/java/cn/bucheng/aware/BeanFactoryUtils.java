package cn.bucheng.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author ：yinchong
 * @create ：2019/7/5 13:44
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class BeanFactoryUtils implements BeanFactoryAware {
    static DefaultListableBeanFactory factory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        factory = (DefaultListableBeanFactory) beanFactory;
    }

    public static <T> T getBean(Class<T> clazz) {
        return factory.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return (T) factory.getBean(name);
    }
}
