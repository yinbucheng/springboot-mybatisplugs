package cn.bucheng.mongodb.springboot;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：yinchong
 * @create ：2019/6/25 20:29
 * @description：test
 * @modified By：
 * @version:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootstrapTest {

    @BeforeClass
    public static void beforeClass(){
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }


    @Test
    public void test(){
        System.out.println("test");
    }

}
