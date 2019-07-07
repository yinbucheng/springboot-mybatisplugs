package cn.bucheng.springboot.annotest.config;

import cn.bucheng.springboot.annotest.bean.Bean;
import cn.bucheng.springboot.annotest.bean.BeanOne;
import cn.bucheng.springboot.annotest.bean.BeanTest;
import cn.bucheng.springboot.annotest.bean.BeanTwo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：yinchong
 * @create ：2019/6/27 8:50
 * @description：
 * @modified By：
 * @version:
 */
//@Configuration
public class ConditionOnBeanConfig {

    //测试发现，@ConditionOnMissingBean 只能在@Bean 注释的方法上使用，不能再@Component 注释的类上使用。
    @org.springframework.context.annotation.Bean
    @ConditionalOnBean(BeanTest.class)
    public Bean beanTwo() {
        return new BeanTwo();
    }

}
