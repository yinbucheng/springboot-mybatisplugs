package cn.bucheng.springboot.aop.customer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：yinchong
 * @create ：2019/6/26 17:18
 * @description：proxyFactory测试
 * @modified By：
 * @version:
 */
public class ProxyFactory  implements InvocationHandler {

    List<MethodInterceptor> advices = new LinkedList<>();
    Object target;

    public void addAdvice(MethodInterceptor advice){
        advices.add(advice);
    }

    public void setTarget(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ReflectMethodProceed reflectMethodProceed = new ReflectMethodProceed(target,method,args,advices);
        return reflectMethodProceed.proceed();
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}
