package cn.bucheng.springboot.annotest.config;

import cn.bucheng.springboot.annotest.bean.Bean;
import cn.bucheng.springboot.annotest.bean.BeanOne;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：yinchong
 * @create ：2019/6/27 8:55
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class ConditionOnMissClassConfig {

    @ConditionalOnMissingClass("cn.intellif.Dispatcher")
    @org.springframework.context.annotation.Bean
    public Bean beanOne(){
        return new BeanOne();
    }
}
