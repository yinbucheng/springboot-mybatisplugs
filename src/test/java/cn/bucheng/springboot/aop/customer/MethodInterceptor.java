package cn.bucheng.springboot.aop.customer;

import org.aopalliance.intercept.Joinpoint;

import java.lang.reflect.Method;

/**
 * @author ：yinchong
 * @create ：2019/6/26 17:35
 * @description：
 * @modified By：
 * @version:
 */
public interface MethodInterceptor {
    Object invoke(Joinpoint joinPoint, Method method);
}
