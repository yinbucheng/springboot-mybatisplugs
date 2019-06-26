package cn.bucheng.springboot.annotest;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ：yinchong
 * @create ：2019/6/26 20:47
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
@ConditionalOnMissingBean(Bean.class)
public class BeanOne implements Bean {
    @Override
    public void test() {
        System.out.println("BeanOne");
    }
}
