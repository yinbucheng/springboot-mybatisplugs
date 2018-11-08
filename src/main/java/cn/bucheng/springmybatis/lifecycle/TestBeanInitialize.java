package cn.bucheng.springmybatis.lifecycle;

import cn.bucheng.springmybatis.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestBeanInitialize
 * @Author buchengyin
 * @Date 2018/11/8 9:48
 **/
@Component
public class TestBeanInitialize implements InitializingBean {
    @Autowired
    private UserService userService;
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(userService);
    }
}
