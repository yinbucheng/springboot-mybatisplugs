package cn.bucheng.springmybatis.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @ClassName BeanPostProcessorTest
 * @Author buchengyin
 * @Date 2018/12/6 14:10
 **/
@Component
public class BeanPostProcessorTest implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("---------------->post Before");
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("---------------->post After");
        return bean;
    }
}
