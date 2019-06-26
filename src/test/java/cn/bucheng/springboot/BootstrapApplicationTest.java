package cn.bucheng.springboot;

import cn.bucheng.springboot.config.BeanFactoryUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：yinchong
 * @create ：2019/6/26 15:28
 * @description：测试启动类
 * @modified By：
 * @version:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootstrapApplicationTest {


    @BeforeClass
    public static void before(){
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }


    @Test
    public void test(){
        String bean = BeanFactoryUtils.getBeanFactory().getBean(String.class);
        System.out.println(bean);
    }
}
