package cn.bucheng.springboot.aop.customer;

import org.aopalliance.intercept.Joinpoint;

public interface Match {

    boolean match(Joinpoint joinpoint);
}
