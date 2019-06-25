package cn.bucheng.cache.dao;

import cn.bucheng.cache.po.Order;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/6/25 19:12
 * @description：redis测试
 * @modified By：
 * @version:
 */
@Repository
public class OrderCacheDao {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void save(Order order){
        redisTemplate.boundHashOps("order").put(order.getOrderId()+"",  JSON.toJSONString(order));
    }

    public void delete(long id){
        redisTemplate.boundHashOps("order").delete(id+"");
    }

    public Order findOne(long id){
        return JSON.parseObject((String)redisTemplate.boundHashOps("order").get(id+""),Order.class);
    }

    public List<Order> listOrder(){
        List<Order> orders = new LinkedList<>();
        Map<Object, Object> entries = redisTemplate.boundHashOps("order").entries();
        entries.forEach((key,value)->{
            String temp = (String)value;
            orders.add(JSON.parseObject(temp,Order.class));
        });

        return orders;
    }
}
