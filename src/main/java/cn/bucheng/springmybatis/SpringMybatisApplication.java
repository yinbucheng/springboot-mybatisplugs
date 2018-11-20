package cn.bucheng.springmybatis;

import cn.bucheng.springmybatis.lifecycle.BeanFactoryUtils;
import cn.bucheng.springmybatis.lifecycle.EnviromentUtils;
import cn.bucheng.springmybatis.lifecycle.FactoryBeanTest;
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
		SpringApplication.run(SpringMybatisApplication.class, args);
	}

}
