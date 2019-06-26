package cn.bucheng.springboot.aop.customer;

import org.aopalliance.intercept.Joinpoint;

/**
 * @author ：yinchong
 * @create ：2019/6/26 17:37
 * @description：
 * @modified By：
 * @version:
 */
public abstract class AspectAroundMethod implements MethodInterceptor{

    public abstract Object around(Joinpoint joinpoint);

    @Override
    public Object invoke(Joinpoint joinPoint) {
        try {
            return around(joinPoint);
        }catch (Throwable throwable){
            System.err.println(throwable);
            return null;
        }
    }
}
