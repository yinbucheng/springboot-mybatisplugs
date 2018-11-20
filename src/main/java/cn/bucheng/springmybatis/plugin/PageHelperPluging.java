package cn.bucheng.springmybatis.plugin;

import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * @ClassName PageHelperPluging
 * @Author buchengyin
 * @Date 2018/11/15 19:18
 **/
@Intercepts(@Signature(type = StatementHandler.class,method ="prepare",args = {Connection.class,Integer.class}))
public class PageHelperPluging  implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        invocation.getArgs();
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
