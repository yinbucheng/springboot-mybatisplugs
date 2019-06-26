package cn.bucheng.springboot.aop.customer;

import org.aopalliance.intercept.Joinpoint;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：yinchong
 * @create ：2019/6/26 17:29
 * @description：
 * @modified By：
 * @version:
 */
public class ReflectMethodProceed  implements Joinpoint {
    private Method method;
    private Object target;
    private Object[] args;
    private List<MethodInterceptor> advices = new LinkedList<>();
    private int index =0;

    public ReflectMethodProceed(Object target,Method method,  Object[] args, List<MethodInterceptor> advices) {
        this.method = method;
        this.target = target;
        this.args = args;
        this.advices = advices;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public List<MethodInterceptor> getAdvices() {
        return advices;
    }

    public void setAdvices(List<MethodInterceptor> advices) {
        this.advices = advices;
    }

    @Override
    public Object proceed() throws Throwable {
        if(index==advices.size()){
            return method.invoke(target,args);
        }
        return advices.get(index++).invoke(this);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return null;
    }
}
