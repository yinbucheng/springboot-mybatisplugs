package cn.bucheng.springboot.config;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author ：yinchong
 * @create ：2019/6/26 15:27
 * @description：factorybean测试代码
 * @modified By：
 * @version:
 */
public class FactoryBeanTest implements FactoryBean<String> {
    @Override
    public String getObject() throws Exception {
        return "你 好 这 是 测 试 代 码";
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }
}
