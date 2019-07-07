package cn.bucheng.lifecycle;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * @author ：yinchong
 * @create ：2019/7/5 12:33
 * @description： 事件监听器，用于监听事件
 * @modified By：
 * @version:
 */
@Component
public class ContentEventListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            System.out.println("==============>context refresh<=================");
        }else if(event instanceof ContextStartedEvent){
            System.out.println("=============>context start<================");
        }else if(event instanceof ContextStoppedEvent){
            System.out.println("============>context stop<==================");
        }else if(event instanceof ApplicationEnvironmentPreparedEvent){
            System.out.println("===========>enviromentPrepare<==========");
        }else if(event instanceof ApplicationReadyEvent){
            System.out.println("===========>application ready<===============");
        }
    }
}
