package cn.bucheng.springboot.annotest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * @author ：yinchong
 * @create ：2019/6/26 20:48
 * @description：
 * @modified By：
 * @version:
 */
@Component
@ConditionalOnMissingBean(Bean.class)
public class BeanTwo implements Bean {
    @Override
    public void test() {
        System.out.println("BeanTwo");
    }
}
