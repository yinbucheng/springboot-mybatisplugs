package cn.bucheng.cat;

import cn.bucheng.cat.miaosha.service.MiaoShaService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author buchengyin
 * @create 2019/7/7 22:25
 * @describe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CatBootApplication {

    @Autowired
    private MiaoShaService miaoShaService;
    @Autowired
    private RedisTemplate redisTemplate;

    static int maxCount = 2000;
    private CountDownLatch countDownLatch = new CountDownLatch(maxCount);

    @BeforeClass
    public static void before() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Before
    public void init(){
        for(int i=0;i<100;i++){
            redisTemplate.opsForList().leftPush("miaosha_1","miaosha");
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        for (int i = 0; i < maxCount; i++) {
            run(1, i);
            countDownLatch.countDown();
        }
        System.out.println("---------->开始秒杀<-------------");
    }

    private void run(long itemId, long userId) {
        Thread thread = new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag = miaoShaService.miaoSha(itemId, userId);
            if (flag) {
                System.out.println("用户：" + userId + " 秒杀 " + itemId + " 成功");
            } else {
                System.out.println("用户：" + userId + " 秒杀 " + itemId + " 失败");
            }
        });
        thread.start();
    }
}
