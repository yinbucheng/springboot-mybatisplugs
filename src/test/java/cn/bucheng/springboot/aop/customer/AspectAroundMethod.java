package cn.bucheng.springboot.aop.customer;

import org.aopalliance.intercept.Joinpoint;

import java.lang.reflect.Method;

/**
 * @author ：yinchong
 * @create ：2019/6/26 17:37
 * @description：
 * @modified By：
 * @version:
 */
public abstract class AspectAroundMethod implements MethodInterceptor, Match {

    public abstract Object around(Joinpoint joinpoint);

    @Override
    public Object invoke(Joinpoint joinPoint, Method method) {
        try {
            if (match(joinPoint.getThis(),method)) {
                return around(joinPoint);
            }
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            System.err.println(throwable);
            return null;
        }
    }
}
