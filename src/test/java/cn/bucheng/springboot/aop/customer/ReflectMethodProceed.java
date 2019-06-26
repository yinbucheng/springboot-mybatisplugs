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
    //被调用目标对象上面方法
    private Method method;
    //目标对象
    private Object target;
    //调用方法上面参数
    private Object[] args;
    //代理的列表
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
        //如果当前不存在拦截器直接调用目标方法
        if(index==advices.size()){
            return method.invoke(target,args);
        }
        //获取上面拦截器并将当前对象传入，递归调用
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
