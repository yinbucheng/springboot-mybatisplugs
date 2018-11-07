package cn.bucheng.springmybatis;

import cn.bucheng.springmybatis.controller.UserController;
import cn.bucheng.springmybatis.scanner.ClassScanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Set;

@SpringBootApplication
@MapperScan(basePackages = {"cn.bucheng.springmybatis.mapper"})
public class SpringMybatisApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringMybatisApplication.class, args);
		ClassScanner classScanner = applicationContext.getBean(ClassScanner.class);
		Set<BeanDefinition> datas =  classScanner.scann("cn.bucheng.springmybatis.mapper");
//		for(BeanDefinition definition:datas){
//			definition.setBeanClassName();
//			definition.setPrimary(true);
//		}
		System.out.println(datas.size());
	}
}
