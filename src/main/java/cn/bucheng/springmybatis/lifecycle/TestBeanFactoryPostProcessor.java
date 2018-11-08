package cn.bucheng.springmybatis.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

/**
 * @ClassName TestBeanFactoryPostProcessor
 * @Author buchengyin
 * @Date 2018/11/8 9:51
 **/
@Configuration
public class TestBeanFactoryPostProcessor implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>:"+this.getClass().getName()+" postProcessBeforeInitialization");
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>:"+this.getClass().getName()+" postProcessAfterInitialization");
        return bean;
    }
}
