package cn.bucheng.springmybatis.lifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @ClassName SpringRunnerListenenerTest
 * @Author buchengyin
 * @Date 2018/11/9 11:41
 **/
public class SpringRunnerListenenerTest implements SpringApplicationRunListener {
    private SpringApplication springApplication;
    private String[] args;

    public SpringRunnerListenenerTest(SpringApplication springApplication,String[] args){
        this.springApplication =springApplication;
        this.args =args;
    }

    @Override
    public void starting() {
        System.out.println("------------------->starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("-------------------------->enviromentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("------------------------>contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("--------------------->contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("------------------->started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("----------------->running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("------------------->failed");
    }
}
