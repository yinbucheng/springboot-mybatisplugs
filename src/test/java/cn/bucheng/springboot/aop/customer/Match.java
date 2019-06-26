package cn.bucheng.springboot.aop.customer;

import java.lang.reflect.Method;

public interface Match {

    boolean match(Object target, Method method);
}
