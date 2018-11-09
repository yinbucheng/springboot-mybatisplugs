package cn.bucheng.springmybatis.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName BeanFactoryUtils
 * @Author buchengyin
 * @Date 2018/11/8 20:17
 **/
@Configuration
public class BeanFactoryUtils implements BeanFactoryAware {
    private static BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanFactoryUtils.beanFactory = beanFactory;
    }

    public static BeanFactory getBeanFactory(){
        return beanFactory;
    }
}
