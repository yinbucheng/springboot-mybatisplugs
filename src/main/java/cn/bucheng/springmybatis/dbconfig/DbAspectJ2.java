package cn.bucheng.springmybatis.dbconfig;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.sql.Connection;

/**
 * @ClassName DbAspectJ2
 * @Author buchengyinD
 * @Date 2018/11/27 11:12
 **/
@Aspect
@Configuration
@Order(1)
public class DbAspectJ2 {

    @Around("execution(java.sql.Connection *..getConnection(..))")
    public Connection aopGetConnection(ProceedingJoinPoint point)throws Throwable {
        System.out.println("-----------------------> bAspectj 2 invoke");
        return (Connection) point.proceed();
    }
}
