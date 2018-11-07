package cn.bucheng.springmybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;


@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })//按需配置
public class MybatisInterceptor2 implements Interceptor {
 
	@Override
    public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("mybatis拦截器2======");
		return invocation.proceed();
	}
 
	// 获取代理对象
	@Override
	public Object plugin(Object o) {
		return Plugin.wrap(o, this);
	}
 
	// 设置代理对象的参数
	@Override
	public void setProperties(Properties properties) {
	}
}
