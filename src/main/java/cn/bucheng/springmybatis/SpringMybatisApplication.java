package cn.bucheng.springmybatis;

import cn.bucheng.springmybatis.controller.UserController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = {"cn.bucheng.springmybatis.mapper"})
public class SpringMybatisApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringMybatisApplication.class, args);
		UserController controller = applicationContext.getBean(UserController.class);
		System.out.println(controller);
	}
}
