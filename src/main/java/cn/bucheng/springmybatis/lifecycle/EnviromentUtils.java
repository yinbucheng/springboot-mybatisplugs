package cn.bucheng.springmybatis.lifecycle;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @ClassName EnviromentUtils
 * @Author buchengyin
 * @Date 2018/11/9 10:21
 **/
@Configuration
public class EnviromentUtils implements EnvironmentAware {
    private static Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        EnviromentUtils.environment = environment;
    }

    public static Environment getEnvironment(){
        return environment;
    }
}
