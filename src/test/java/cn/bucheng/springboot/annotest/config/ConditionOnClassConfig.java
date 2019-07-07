package cn.bucheng.springboot.annotest.config;

import cn.bucheng.springboot.annotest.bean.Bean;
import cn.bucheng.springboot.annotest.bean.BeanTwo;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：yinchong
 * @create ：2019/6/27 8:58
 * @description：
 * @modified By：
 * @version:
 */
//@Configuration
public class ConditionOnClassConfig {


    @ConditionalOnClass(BeanFactory.class)
    @org.springframework.context.annotation.Bean
    public Bean beanTwo(){
        return new BeanTwo();
    }
}
