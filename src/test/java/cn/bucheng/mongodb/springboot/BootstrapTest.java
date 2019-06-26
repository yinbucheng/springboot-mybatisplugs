package cn.bucheng.mongodb.springboot;

import cn.bucheng.persister.dao.AddressDao;
import cn.bucheng.persister.po.Address;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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

    @Autowired
    private AddressDao addressDao;

    @BeforeClass
    public static void beforeClass(){
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }


    @Test
    public void testSave(){
        Address address = new Address();
        address.setCity("武汉");
        address.setDistrict("重庆");
        address.setId("1");
        address.setProvince("湖北");
        address.setStatus(1);
       addressDao.save(address);
    }


    @Test
    public void testFindOne(){
        Optional<Address> data = addressDao.findById("1");
        System.out.println(data.get());
    }
}
