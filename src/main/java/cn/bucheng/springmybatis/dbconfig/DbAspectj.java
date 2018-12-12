package cn.bucheng.springmybatis.dbconfig;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.sql.Connection;

/**
 * @ClassName DbAspectj
 * @Author buchengyin
 * @Date 2018/11/24 13:15
 **/
@Aspect
@Configuration
@Order(value = -1)
public class DbAspectj {

    @Around("execution(java.sql.Connection *..getConnection(..))")
    public Connection aopGetConnection(ProceedingJoinPoint point)throws Throwable{
        System.out.println("---------------------> DbAspectj1 invoke");
        String className = point.getTarget().getClass().getName();
        System.out.println("-------------------->getConnection invoke ,ClassName:"+className);
        return (Connection) point.proceed();
    }
}
