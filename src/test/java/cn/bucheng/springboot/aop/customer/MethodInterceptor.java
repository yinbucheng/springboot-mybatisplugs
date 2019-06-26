package cn.bucheng.springboot.aop.customer;

import org.aopalliance.intercept.Joinpoint;

/**
 * @author ：yinchong
 * @create ：2019/6/26 17:35
 * @description：
 * @modified By：
 * @version:
 */
public interface MethodInterceptor {
    Object invoke(Joinpoint joinPoint);
}
