package cn.bucheng.springboot.aop.customer;

import org.aopalliance.intercept.Joinpoint;

/**
 * @author ：yinchong
 * @create ：2019/6/26 17:37
 * @description：
 * @modified By：
 * @version:
 */
public abstract class AspectAfterMethod implements MethodInterceptor, Match {

    public abstract void after();

    @Override
    public Object invoke(Joinpoint joinPoint) {
        try {
            Object value = joinPoint.proceed();
            if (match(joinPoint)) {
                after();
            }
            return value;
        } catch (Throwable t) {
            System.err.println(t);
            return null;
        }
    }
}
