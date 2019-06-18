package cn.bucheng.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author ：yinchong
 * @create ：2019/6/18 19:16
 * @description：redis测试
 * @modified By：
 * @version:
 */
public class RedisTest {

    private Jedis jedis;

    @Before
    public void before() {
        //连接本地的 Redis 服务
        jedis = new Jedis("localhost");
    }

    @Test
    public void testSetString(){
        String set = jedis.set("name", "yinchong");
        System.out.println(set);
    }


    @Test
    public void testGetString(){
        String name = jedis.get("name");
        System.out.println(name);
    }

    @Test
    public void testExpireKey(){
        Long time = jedis.expire("name", 10);
        System.out.println(time);
    }


    @Test
    public void testTtlKey(){
        Long time = jedis.ttl("name");
        System.out.println(time);
    }

    @Test
    public void testSetAge(){
        String row = jedis.set("age", "20");
        System.out.println(row);
    }

    @Test
    public void testDelete(){
        Long row = jedis.del("age");
        System.out.println(row);
    }

    @Test
    public void testIncr(){
        Long number = jedis.incr("age");
        System.out.println(number);
    }

    @Test
    public void testIncrBy(){
        Long number = jedis.incrBy("age", 10);
        System.out.println(number);
    }

    @Test
    public void testCountKey(){

    }
}
