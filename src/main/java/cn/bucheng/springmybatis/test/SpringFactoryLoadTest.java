package cn.bucheng.springmybatis.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @author buchengyin
 * @Date 2019/2/1 11:54
 **/
public class SpringFactoryLoadTest {
    public static void main(String[] args) {
        List<String> enableAutoConfigurations = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, null);
        System.out.println(enableAutoConfigurations);
    }
}
