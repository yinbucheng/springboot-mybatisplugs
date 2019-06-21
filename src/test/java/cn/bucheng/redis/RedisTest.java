package cn.bucheng.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
        jedis.select(1);
    }

    @Test
    public void testSetString() {
        String set = jedis.set("name", "yinchong");
        System.out.println(set);
    }

    @Test
    public void testBatchSave() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            jedis.set("test_name_test" + i, i + "test");
        }
    }

    @Test
    public void testKeys(){
        Set<String> keys = jedis.keys("test_name*");
        System.out.println(keys.size());
    }


    @Test
    public void testGetString() {
        String name = jedis.get("name");
        System.out.println(name);
    }

    @Test
    public void testExpireKey() {
        Long time = jedis.expire("name", 10);
        System.out.println(time);
    }


    @Test
    public void testTtlKey() {
        Long time = jedis.ttl("name");
        System.out.println(time);
    }

    @Test
    public void testSetAge() {
        String row = jedis.set("age", "20");
        System.out.println(row);
    }

    @Test
    public void testDelete() {
        Long row = jedis.del("age");
        System.out.println(row);
    }

    @Test
    public void testIncr() {
        Long number = jedis.incr("age");
        System.out.println(number);
    }

    @Test
    public void testIncrBy() {
        Long number = jedis.incrBy("age", 10);
        System.out.println(number);
    }

    @Test
    public void testScanGetKey() {
        String key = "name*";
        String cursor = ScanParams.SCAN_POINTER_START;
        ScanParams scanParams = new ScanParams();
        scanParams.match(key);
        scanParams.count(10);
        int count = 0;
        for (; ; ) {
            ScanResult<String> result = jedis.scan(cursor, scanParams);
            cursor = result.getCursor();
            List<String> datas = result.getResult();
            Iterator<String> iterator = datas.iterator();
            while(iterator.hasNext()){
                String data = iterator.next();
                System.out.println(data);
                count++;
            }
            if ("0".equals(cursor)) {
                break;
            }
        }
        System.out.println("total:"+count);
    }

    @Test
    public void testScanDelete() {
        String key = "name*";
        String cursor = ScanParams.SCAN_POINTER_START;
        ScanParams params = new ScanParams();
        params.match(key);
        params.count(100);
        for (; ; ) {
            ScanResult<String> scan = jedis.scan(cursor, params);
            cursor = scan.getCursor();
            List<String> datas = scan.getResult();
            datas.forEach((param) -> jedis.del(key)
            );
            if ("0".equals(cursor)) {
                break;
            }
        }
    }

    @Test
    public void testSaveBit(){
        jedis.setbit("login_user",3,true);
    }

    @Test
    public void cancelBit(){
        jedis.setbit("login_user",3,false);
    }

    @Test
    public void testGetBit(){
        Boolean affect = jedis.getbit("login_user", 3);
        System.out.println(affect);
    }

    @Test
    public void testBgSave(){
        jedis.bgsave();
    }

    @Test
    public void testSave(){
        jedis.save();
    }

    @Test
    public void testChangeDb(){
        jedis.select(2);
    }


    @Test
    public void testAndKey(){
       jedis.bitop(BitOP.AND,"testAnd","test1","test2");
    }

    @Test
    public void testOrKey(){
        jedis.bitop(BitOP.OR,"testOr","test1","test2");
    }

    @Test
    public void testGetAndBitMap(){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<100;i++) {
            Boolean testAnd = jedis.getbit("testAnd", i);
            if(testAnd){
                builder.append("1");
                continue;
            }
            builder.append("0");
        }
        System.out.println(builder);
    }

    @Test
    public void testGetOrBitMap(){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<100;i++) {
            Boolean testAnd = jedis.getbit("testOr", i);
            if(testAnd){
                builder.append("1");
                continue;
            }
            builder.append("0");
        }
        System.out.println(builder);
    }

    @Test
    public void testSaveBitTest1(){
        for(int i=0;i<100;i++){
            jedis.setbit("test1",i, new Random().nextBoolean());
        }
    }

    @Test
    public void testSaveBitTest2(){
        for(int i=0;i<100;i++){
            jedis.setbit("test2",i, new Random().nextBoolean());
        }
    }


    @Test
    public void testAllBit(){
        testBitmap("test1");
        testBitmap("test2");
        testBitmap("testAnd");
        testBitmap("testOr");
    }


    private void testBitmap(String key){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<100;i++) {
            Boolean testAnd = jedis.getbit(key, i);
            if(testAnd){
                builder.append("1");
                continue;
            }
            builder.append("0");
        }
        System.out.println(builder);
    }

}
