package cn.bucheng.springmybatis.lifecycle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @ClassName FactoryBeanTest
 * @Author buchengyin
 * @Date 2018/11/12 13:37
 **/
@Component
public class FactoryBeanTest implements FactoryBean<String> {
    @Nullable
    @Override
    public String getObject() throws Exception {
        return "nice";
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return String.class;
    }
}
