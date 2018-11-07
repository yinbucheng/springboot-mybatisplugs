package cn.bucheng.springmybatis.config;

import cn.bucheng.springmybatis.plugin.MybatisInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Bean
	public Interceptor getInterceptor() {
		return new MybatisInterceptor();
	}
}
