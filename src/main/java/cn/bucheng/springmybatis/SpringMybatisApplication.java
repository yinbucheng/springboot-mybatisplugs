package cn.bucheng.springmybatis;

import cn.bucheng.springmybatis.lifecycle.BeanFactoryUtils;
import cn.bucheng.springmybatis.lifecycle.EnviromentUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
@MapperScan(basePackages = {"cn.bucheng.springmybatis.mapper"})
@ComponentScan(basePackages = "cn.bucheng.springmybatis")
public class SpringMybatisApplication {

	public static void main(String[] args)throws Exception {
		ApplicationContext applicationContext = SpringApplication.run(SpringMybatisApplication.class, args);
//		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) BeanFactoryUtils.getBeanFactory();
//		try {
//			Field field = DefaultListableBeanFactory.class.getDeclaredField("allBeanNamesByType");
//			field.setAccessible(true);
//			Map<Class<?>, String[]> datas = (Map<Class<?>, String[]>) field.get(factory);
//			for(Map.Entry<Class<?>, String[]> entry:datas.entrySet()){
//				System.out.println("----->key:"+entry.getKey().getName()+" value:"+entry.getValue());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		AbstractEnvironment environment = (AbstractEnvironment) EnviromentUtils.getEnvironment();
		Field field = AbstractEnvironment.class.getDeclaredField("propertySources");
		field.setAccessible(true);
		MutablePropertySources propertySources = (MutablePropertySources) field.get(environment);
		System.out.println("----->all:"+propertySources);
		Iterator<PropertySource<?>> iterator = propertySources.iterator();
		while(iterator.hasNext()){
			PropertySource<?> next = iterator.next();
			System.out.println("name:"+next.getName()+" value:"+next.getSource());
		}
	}
}
