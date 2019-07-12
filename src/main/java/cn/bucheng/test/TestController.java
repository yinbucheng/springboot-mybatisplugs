package cn.bucheng.test;

import cn.bucheng.aware.BeanFactoryUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author ：yinchong
 * @create ：2019/7/8 18:48
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/test2")
    public Object test2(){
//        HttpServletRequest request = new MockHttpServletRequest();
//        HttpServletResponse response = new MockHttpServletResponse();
//        ((MockHttpServletRequest) request).setMethod("GET");
//        ((MockHttpServletRequest) request).setRequestURI("/test/test1");
//        ((MockHttpServletRequest) request).setServletPath("/");
//        DispatcherServlet dispatcherServlet = BeanFactoryUtils.getBean(DispatcherServlet.class);
//        try {
//            Method doDispatch = DispatcherServlet.class.getDeclaredMethod("doDispatch", HttpServletRequest.class, HttpServletResponse.class);
//            doDispatch.setAccessible(true);
//            doDispatch.invoke(dispatcherServlet, request, response);
//            System.out.println(response);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        ((MockHttpServletRequest) request).setMethod("GET");
        ((MockHttpServletRequest) request).setRequestURI("/test/test1");
        ((MockHttpServletRequest) request).setServletPath("/test/test1");
        DispatcherServlet dispatcherServlet = BeanFactoryUtils.getBean(DispatcherServlet.class);
        try {
            Method doDispatch = DispatcherServlet.class.getDeclaredMethod("doDispatch", HttpServletRequest.class, HttpServletResponse.class);
            doDispatch.setAccessible(true);
            doDispatch.invoke(dispatcherServlet, request, response);
            String contentAsString = ((MockHttpServletResponse) response).getContentAsString();
            System.out.println(contentAsString);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "test2";
    }

}
