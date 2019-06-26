package cn.bucheng.springboot.ioc;

import cn.bucheng.springboot.aop.BeanPostProcessorTest;
import cn.bucheng.springboot.config.BeanFactoryUtils;
import cn.bucheng.springboot.config.FactoryBeanTest;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ：yinchong
 * @create ：2019/6/26 15:26
 * @description：beanDefination注册类测试代码
 * @modified By：
 * @version:
 */
public class BeanDefinationRegisterTest implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(FactoryBeanTest.class);
        registry.registerBeanDefinition("word",beanDefinitionBuilder.getBeanDefinition());
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(BeanPostProcessorTest.class);
        registry.registerBeanDefinition("processTest",builder.getBeanDefinition());
        BeanDefinitionBuilder builder2 = BeanDefinitionBuilder.genericBeanDefinition(BeanFactoryUtils.class);
        registry.registerBeanDefinition("beanFacotry",builder2.getBeanDefinition());
    }
}
