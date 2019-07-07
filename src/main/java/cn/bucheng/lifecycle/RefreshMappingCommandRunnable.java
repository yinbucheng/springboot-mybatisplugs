package cn.bucheng.lifecycle;

import cn.bucheng.aware.ApplicationUtils;
import cn.bucheng.aware.BeanFactoryUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

import java.lang.reflect.Method;

/**
 * @author ：yinchong
 * @create ：2019/7/5 14:32
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class RefreshMappingCommandRunnable implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        DispatcherServlet dispatcherServlet = BeanFactoryUtils.getBean(DispatcherServlet.class);
        try {
            Method onRefresh = DispatcherServlet.class.getDeclaredMethod("onRefresh", ApplicationContext.class);
            onRefresh.setAccessible(true);
            onRefresh.invoke(dispatcherServlet, ApplicationUtils.applicationContext);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
