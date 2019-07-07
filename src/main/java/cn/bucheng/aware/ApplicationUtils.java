package cn.bucheng.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ：yinchong
 * @create ：2019/7/5 14:17
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class ApplicationUtils implements ApplicationContextAware {
   public  static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationUtils.applicationContext = applicationContext;
    }


}
