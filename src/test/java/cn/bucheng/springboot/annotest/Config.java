package cn.bucheng.springboot.annotest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：yinchong
 * @create ：2019/6/26 21:19
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class Config {
     //测试发现，@ConditionOnMissingBean 只能在@Bean 注释的方法上使用，不能再@Component 注释的类上使用。
    @org.springframework.context.annotation.Bean
    @ConditionalOnMissingBean(Bean.class)
    public Bean beanTwo() {
        return new BeanTwo();
    }

    @org.springframework.context.annotation.Bean
    @ConditionalOnMissingBean(Bean.class)
    public Bean beanOne() {
        return new BeanOne();
    }


}
