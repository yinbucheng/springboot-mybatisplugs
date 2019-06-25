package cn.bucheng.redis.springboot;

import cn.bucheng.cache.dao.OrderCacheDao;
import cn.bucheng.cache.po.Order;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ：yinchong
 * @create ：2019/6/25 19:20
 * @description：测试类
 * @modified By：
 * @version:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootstrapTest {
    @Autowired
    private OrderCacheDao cacheDao;

    @BeforeClass
    public static void before() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Test
    public void testSave() {
        for (int i = 0; i < 100; i++) {
            Order order = new Order();
            order.setOrderId((long) i);
            order.setBuyerMessage("这是一条测试" + i);
            order.setBuyerNick("尹冲" + i);
            order.setBuyerRate((i + 1) + "折");
            order.setCloseTime(new Date());
            order.setExpire(new Date());
            order.setPayment(new BigDecimal(23.23));
            order.setCreateTime(new Date());
            order.setPostFee(i + "");
            order.setShippingName("测试");
            order.setStatus("完成");
            order.setPaymentType("支付宝支付");
            cacheDao.save(order);
        }
    }

    @Test
    public void testFindOne() {
        Order one = cacheDao.findOne(1);
        System.out.println(one);
    }

    @Test
    public void testListAll() {
        List<Order> orders = cacheDao.listOrder();
        System.out.println(orders);
    }
}
