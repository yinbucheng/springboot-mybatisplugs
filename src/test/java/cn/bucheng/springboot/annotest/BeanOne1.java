package cn.bucheng.springboot.annotest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * @author ：yinchong
 * @create ：2019/6/26 21:13
 * @description：
 * @modified By：
 * @version:
 */
//@Component
@ConditionalOnClass(Bean.class)
public class BeanOne1 implements Bean {
    @Override
    public void test() {
        System.out.println("BeanOne1");
    }
}
