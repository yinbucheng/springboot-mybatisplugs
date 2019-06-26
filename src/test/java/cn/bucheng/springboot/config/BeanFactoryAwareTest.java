package cn.bucheng.springboot.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author ：yinchong
 * @create ：2019/6/26 15:48
 * @description：beanFactory 通知测试
 * @modified By：
 * @version:
 */
public class BeanFactoryAwareTest implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
