package cn.bucheng.springboot.web;

import cn.bucheng.aware.ApplicationUtils;
import cn.bucheng.aware.BeanFactoryUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author ：yinchong
 * @create ：2019/7/5 10:31
 * @description：
 * @modified By：
 * @version:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebBootApplicationTest {


    @BeforeClass
    public static void before() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Before
    public void init(){
        DispatcherServlet dispatcherServlet = BeanFactoryUtils.getBean(DispatcherServlet.class);
        try {
            Method onRefresh = DispatcherServlet.class.getDeclaredMethod("onRefresh", ApplicationContext.class);
            onRefresh.setAccessible(true);
            onRefresh.invoke(dispatcherServlet, ApplicationUtils.applicationContext);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void testWeb() {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        ((MockHttpServletRequest) request).setMethod("GET");
        ((MockHttpServletRequest) request).setRequestURI("/test");
        ((MockHttpServletRequest) request).setServletPath("/");
        DispatcherServlet dispatcherServlet = BeanFactoryUtils.getBean(DispatcherServlet.class);
        try {
            Method doDispatch = DispatcherServlet.class.getDeclaredMethod("doDispatch", HttpServletRequest.class, HttpServletResponse.class);
            doDispatch.setAccessible(true);
            doDispatch.invoke(dispatcherServlet, request, response);
            System.out.println(response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
