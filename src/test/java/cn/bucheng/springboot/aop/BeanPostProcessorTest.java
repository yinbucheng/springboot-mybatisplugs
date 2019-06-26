package cn.bucheng.springboot.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author ：yinchong
 * @create ：2019/6/26 15:25
 * @description：bean前置及后置处理器
 * @modified By：
 * @version:
 */
public class BeanPostProcessorTest implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("-------------->before<----------------");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("-------------->after<-------------");
        return bean;
    }
}
